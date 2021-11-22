package com.example.triggerise.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Objects;

@Service
public class Calculate {


    public BigDecimal TwoforOneDiscount(ArrayList<BigDecimal> cart, BigDecimal priceOfItemOnDiscount) {

        int size_of_items = 0;
        int occurenceCounter = 0;
        BigDecimal numberOfPair = BigDecimal.ZERO;
        int divider = 2;
        BigDecimal sum = BigDecimal.ZERO;
        BigDecimal promotionTotal = BigDecimal.ZERO;
        BigDecimal ret = BigDecimal.ZERO;

        size_of_items = cart.size();

        // loop to see occurrence

        for (int i = 0; i < size_of_items; i++) {

            if (Objects.equals(cart.get(i), priceOfItemOnDiscount)) {

                occurenceCounter = occurenceCounter + 1;

            }
        }

        // Get the number of pair(2 items) from the total products

        numberOfPair = BigDecimal.valueOf(occurenceCounter / divider);

        // Calculate promotion based on rules

        for (int i = 0; i < size_of_items; i++) {

            sum = sum.add(cart.get(i));

        }

        ret = numberOfPair.multiply(priceOfItemOnDiscount);

        promotionTotal = sum.subtract(ret);

        System.out.println(cart);

        System.out.println("2 for 1 promo included in Total: " + promotionTotal.setScale(2, RoundingMode.HALF_EVEN));

        return promotionTotal;
    }

    public BigDecimal FixedDiscount(ArrayList<BigDecimal> cart, BigDecimal rate, BigDecimal regularPrice, int DiscountEntry) {

        int size_of_items = 0;
        int occurenceCounter = 0;
        BigDecimal ret = BigDecimal.ZERO;
        BigDecimal disountedPrice = BigDecimal.ZERO;
        BigDecimal sum = BigDecimal.ZERO;

        size_of_items = cart.size();

        // loop to see occurence

        for (int i = 0; i < size_of_items; i++) {

            if (Objects.equals(cart.get(i), regularPrice)) {
                occurenceCounter = occurenceCounter + 1;
            }
        }

        // Calculate Discount based on rules

        if (occurenceCounter >= DiscountEntry) {

            ret = regularPrice.multiply(rate);
            System.out.println(ret);
            disountedPrice = regularPrice.subtract(ret);

        }

        // Replace regular price with discounted price in the cart

        for (int i = 0; i < size_of_items; i++) {

            if (Objects.equals(cart.get(i), regularPrice)) {
                cart.set(i, disountedPrice);
            }
        }

        System.out.println(cart);// printing to see new arraylist

        // Calculate the total with the discounted price

        for (int i = 0; i < size_of_items; i++) {
            sum = sum.add(cart.get(i));
        }

        System.out.println("Sum of products with discount included: " + sum.setScale(2, RoundingMode.HALF_EVEN));

        return sum.setScale(2,RoundingMode.HALF_EVEN);
    }

    public BigDecimal UndiscountedTotal(ArrayList<BigDecimal> cart) {

        int size_of_items = 0;
        BigDecimal sum = BigDecimal.ZERO;

        size_of_items = cart.size();

        // Add items in cart

        for (int i = 0; i < size_of_items; i++) {

            sum = sum.add(cart.get(i));

        }

        System.out.println(cart);

        System.out.println("UnDiscounted total: " + sum.setScale(2, RoundingMode.HALF_EVEN));

        return sum;
    }

    public BigDecimal DiscountedPromotionTotal(ArrayList<BigDecimal> cart, BigDecimal priceToDiscount,
                                               BigDecimal discountRate, int discountThreshold,
                                               BigDecimal promoPrice) {

        int size_of_items = 0;
        int occurenceCounter = 0;
        int occurenceCounter2 = 0;
        BigDecimal numberOfPair = BigDecimal.ZERO;
        int divider = 2;
        BigDecimal sum = BigDecimal.ZERO;
        BigDecimal ret = BigDecimal.ZERO;
        BigDecimal priceOf_2_in_1 = BigDecimal.ZERO;
        BigDecimal discoutedPrice = BigDecimal.ZERO;

        size_of_items = cart.size();

        // loop to see occurrence of mug items

        for (int i = 0; i < size_of_items; i++) {

            if (Objects.equals(cart.get(i), promoPrice)) {

                occurenceCounter = occurenceCounter + 1;
            }
        }

        // loop to see occurrence of shirt items

        for (int i = 0; i < size_of_items; i++) {

            if (Objects.equals(cart.get(i),priceToDiscount)) {

                occurenceCounter2 = occurenceCounter2 + 1;
            }
        }

        if (occurenceCounter2 >= discountThreshold) {

           ret = priceToDiscount.multiply(discountRate);
           discoutedPrice = priceToDiscount.subtract(ret);
        }

        // Replace regular price with discounted price

        for (int i = 0; i < size_of_items; i++) {

            if (Objects.equals(cart.get(i),priceToDiscount)){

                cart.set(i,discoutedPrice);
            }
        }

        System.out.println("discounted price check: "+cart);


        // Get the number of pair(2 items) from the total products

        numberOfPair = BigDecimal.valueOf(occurenceCounter / divider);

        priceOf_2_in_1 = numberOfPair.multiply(promoPrice);

        // Calculate the total with discounted price and promo price

        for (int i = 0; i < size_of_items; i++) {

            sum = sum.add(cart.get(i)); // sum of all item in cart

        }

        // subtract the extra price from total sum because of the 2 for 1 discount (remove the price of 1)

        return sum.subtract(priceOf_2_in_1).setScale(2,RoundingMode.HALF_EVEN);

    }
}
