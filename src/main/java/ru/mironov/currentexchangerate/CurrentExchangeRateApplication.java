package ru.mironov.currentexchangerate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CurrentExchangeRateApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrentExchangeRateApplication.class, args);
	}

}
