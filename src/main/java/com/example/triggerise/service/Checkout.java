package com.example.triggerise.service;

import com.example.triggerise.entity.Index;
import com.example.triggerise.repository.IndexRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.plaf.IconUIResource;
import java.math.BigDecimal;
import java.util.*;

@Service
@AllArgsConstructor
public class Checkout {

    private final IndexRepository indexRepository;

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

        System.out.println("Items on cart as scanned: "+cart);
        Collections.sort(cart);
        System.out.println("Items on cart after sorting: "+cart);
        return cart;
    }

    public BigDecimal total(){

        int size_of_items = 0;
        BigDecimal sum = BigDecimal.ZERO;


        size_of_items = cart.size();

        System.out.println("size_of_items: "+size_of_items);

        // Add items in cart

        for(int i = 0; i < size_of_items; i++) {

            sum = sum.add(cart.get(i));

        }

        System.out.println(sum);

        return sum;
    }


}
