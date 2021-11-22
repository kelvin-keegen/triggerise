package com.example.triggerise.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Getter
@Setter
public class PricingRules {

    private String discountRate;
    private String priceToDiscount;
    private String priceOnPromo;
    private boolean isPromoOn;
    private boolean isDiscountOn;
    private int discountOnThreshold;


}
