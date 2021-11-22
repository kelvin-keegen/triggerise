package com.example.triggerise.sandbox;

import com.example.triggerise.service.Checkout;
import com.example.triggerise.service.PricingRules;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class Examples {

    BigDecimal total = BigDecimal.ZERO;

    List<BigDecimal> answers = new ArrayList<>();


    @Bean
    CommandLineRunner lineRunner (Checkout checkout) {

        return args -> {

            System.out.println("\n");

            PricingRules pricingRules = new PricingRules(
                    "0.00",
                    "0.00",
                    "4.00",
                    false,
                    false,
                    0
            );

            checkout.Scan("MUG");
            checkout.Scan("TSHIRT");
            checkout.Scan("USBKEY");
            checkout.ItemsOnCart();
            total = checkout.total(pricingRules);
            checkout.clear();

            System.out.println(total);

            answers.add(total);

            System.out.println("\n\n");


            PricingRules pricingRules2 = new PricingRules(
                    "0.00",
                    "0.00",
                    "4.00",
                    true,
                    false,
                    0
            );

            checkout.Scan("MUG");
            checkout.Scan("TSHIRT");
            checkout.Scan("MUG");
            checkout.ItemsOnCart();
            total = checkout.total(pricingRules2);
            checkout.clear();

            System.out.println(total);

            System.out.println("\n\n");

            answers.add(total);

            PricingRules pricingRules3 = new PricingRules(
                    "0.30",
                    "21.00",
                    "4.00",
                    false,
                    true,
                    3
            );

            checkout.Scan("TSHIRT");
            checkout.Scan("TSHIRT");
            checkout.Scan("TSHIRT");
            checkout.Scan("MUG");
            checkout.Scan("TSHIRT");
            checkout.ItemsOnCart();
            total = checkout.total(pricingRules3);
            checkout.clear();

            System.out.println(total);


            System.out.println("\n\n");

            answers.add(total);

            PricingRules pricingRules4 = new PricingRules(
                    "0.30",
                    "21.00",
                    "4.00",
                    true,
                    true,
                    3
            );

            checkout.Scan("MUG");
            checkout.Scan("TSHIRT");
            checkout.Scan("MUG");
            checkout.Scan("MUG");
            checkout.Scan("USBKEY");
            checkout.Scan("TSHIRT");
            checkout.Scan("TSHIRT");
            checkout.ItemsOnCart();
            total = checkout.total(pricingRules4);
            checkout.clear();

            System.out.println(total);

            System.out.println("\n\n");

            answers.add(total);

            System.out.println(answers);


        };
    }

}
