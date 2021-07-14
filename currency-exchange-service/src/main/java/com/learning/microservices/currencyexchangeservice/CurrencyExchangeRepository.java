package com.learning.microservices.currencyexchangeservice;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, BigDecimal> {
	
	CurrencyExchange findByFromAndTo(String from, String to);

}
