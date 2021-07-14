package com.learning.microservices.currencyconversionservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name="currency-exchange", url="localhost:8000")  // Explicitly connecting to a node without naming service
@FeignClient(name="currency-exchange")  // Feign will check in Eureka Naming server and connect the service 
public interface CurrencyExchangeProxy {

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversion retrieveExchangeValue(@PathVariable String from, 
			@PathVariable String to );
}
