package com.example.microservices.currencyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	//without feign
	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrency(@PathVariable String from , @PathVariable String to , 
			                                      @PathVariable BigDecimal quantity ) {
		
		//starts (making request and getting response)
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from); // to map in {from} and {to} inside getForEntity method
		uriVariables.put("to", to);
		
		ResponseEntity<CurrencyConversionBean> responseEntity=  new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversionBean.class, uriVariables);
		//ends
		CurrencyConversionBean response = responseEntity.getBody();
		
		System.out.println("{}"+response);
		
		return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(), quantity , quantity.multiply(response.getConversionMultiple()), response.getPort());
	}
	
	
	@Autowired
	CurrencyExchangeServiceProxy proxy;
	
	
	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from , @PathVariable String to , 
			                                      @PathVariable BigDecimal quantity ) {
		
		
		CurrencyConversionBean response = proxy.retrieveExchangeValue(from, to);
		
		logger.info("{}",response);
		System.out.println("{rqstt}"+response);
		
		
		return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(), quantity , quantity.multiply(response.getConversionMultiple()), response.getPort());
	}


}
