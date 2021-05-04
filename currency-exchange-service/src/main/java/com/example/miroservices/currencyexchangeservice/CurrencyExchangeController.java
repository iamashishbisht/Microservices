package com.example.miroservices.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ExchangeValueRepository repository;
	
	@Value("${server.port}")
	private int port;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue(@PathVariable String from , @PathVariable String to) {
	
		//ExchangeValue exchangeValue = new ExchangeValue(100L, from, to, new BigDecimal(65)); // hardCoded
		ExchangeValue exchangeValue = repository.findByFromAndTo(from, to);
		exchangeValue.setPort(port); // only to know which instance is running.
		logger.info("{}",exchangeValue);
		return ( exchangeValue );
	}

}
