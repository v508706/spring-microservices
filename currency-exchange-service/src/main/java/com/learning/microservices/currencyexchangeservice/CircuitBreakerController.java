package com.learning.microservices.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBreakerController {
	
	private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
	
	@GetMapping("sample-api")
	@Retry(name = "sample-api", fallbackMethod = "fallbackMethod")
	public String sampleAPI() {
		logger.info("sampleAPI invoked");
		new RestTemplate().getForEntity("http://localhost:8080/some-dummy", String.class);
		return "Sample API";
	}
	
	@GetMapping("cb-sample-api")
	@CircuitBreaker(name = "default", fallbackMethod = "fallbackMethod")
	public String sampleAPI4CB() {
		logger.info("sampleAPI4CB invoked");
		new RestTemplate().getForEntity("http://localhost:8080/some-dummy", String.class);
		return "Sample API";
	}
	
	@GetMapping("rl-sample-api")
	@RateLimiter(name="default")
	public String sampleAPI4RateLimiter() {
		logger.info("sampleAPI4RateLimiter invoked"); 
		return "Sample API";
	}
	
	
	@GetMapping("bh-sample-api")
	@Bulkhead(name="default")
	public String sampleAPI4Bulkhead() {
		logger.info("sampleAPI4Bulkhead invoked"); 
		return "Sample API";
	}
	
	public String fallbackMethod(Exception ex) {
		return "Fallback Response";
	}
}
