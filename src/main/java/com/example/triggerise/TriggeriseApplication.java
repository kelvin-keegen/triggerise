package com.example.triggerise;

import com.example.triggerise.repository.IndexRepository;
import com.example.triggerise.service.Checkout;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@AllArgsConstructor
public class TriggeriseApplication {

	private final Checkout c;

	public static void main(String[] args) {
		SpringApplication.run(TriggeriseApplication.class, args);

	}

}
