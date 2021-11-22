package com.example.triggerise.service;

import com.example.triggerise.entity.Index;
import com.example.triggerise.repository.IndexRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@AllArgsConstructor
public class Checkout {

    private final IndexRepository indexRepository;
    private final Calculate calculate;

    private static ArrayList<BigDecimal> cart = new ArrayList<>();

    public List<Index> showAll(){

        System.out.println(Arrays.toString(indexRepository.findAll().toArray()));
        return indexRepository.findAll();
    }

    public void Scan(String code){

        Optional<Index> item = indexRepository.findByCode(code);

        if (item.isEmpty()){

            throw new IllegalArgumentException("Item not found");
        }

        System.out.println("Last scanned item price: "+item.get().getPrice());
        cart.add(item.get().getPrice());

    }

    public ArrayList<BigDecimal> ItemsOnCart() {
        int size_of_items = 0;

        Collections.sort(cart);
        System.out.println("Items on cart: "+(size_of_items = cart.size()));
        return cart;
    }

    public BigDecimal total(PricingRules pricingRules){

        // check for null values

        if (pricingRules.getPriceOnPromo() != null && pricingRules.getPriceToDiscount() != null && pricingRules.getDiscountRate() != null){


            if (pricingRules.isPromoOn() && !pricingRules.isDiscountOn()) {

                // Calculate 2 for 1 promotion

               return calculate.TwoforOneDiscount(cart, new BigDecimal(pricingRules.getPriceOnPromo()));

            } else if (pricingRules.isDiscountOn() && !pricingRules.isPromoOn()) {


                // Calculate fixed discount

                return calculate.FixedDiscount(cart, new BigDecimal(pricingRules.getDiscountRate()),
                        new BigDecimal(pricingRules.getPriceToDiscount()), pricingRules.getDiscountOnThreshold());

            } else if (pricingRules.isPromoOn() && pricingRules.isDiscountOn()) {



                return calculate.DiscountedPromotionTotal(cart,new BigDecimal(pricingRules.getPriceToDiscount()),
                        new BigDecimal(pricingRules.getDiscountRate()),pricingRules.getDiscountOnThreshold(),
                        new BigDecimal(pricingRules.getPriceOnPromo()));

            }
            else {

                // Calculate total without discount

               return calculate.UndiscountedTotal(cart);
            }
        }

        throw new IllegalArgumentException("Items have not been initialized for processing");

    }

    public void clear() {
        // clear cart
        cart.clear();
    }
}
