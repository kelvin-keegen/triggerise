package com.example.triggerise.config;

import com.example.triggerise.entity.Index;
import com.example.triggerise.repository.IndexRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Configuration
@AllArgsConstructor
public class DatabasePopulate {


    @Bean
   CommandLineRunner commandLineRunner(IndexRepository repository) {
        return args -> {

            Index mug = new Index(

                    "MUG",
                    "Triggerise Mug",
                    new BigDecimal("4.00")
            );

            Index tshirt = new Index(

                    "TSHIRT",
                    "Triggerise Tshirt",
                    new BigDecimal("21.00")
            );

            Index usbkey = new Index(

                    "USBKEY",
                    "Triggerise USB Key",
                    new BigDecimal("10.00")
            );

            repository.saveAll(List.of(mug,tshirt,usbkey));

        };
    }

}
