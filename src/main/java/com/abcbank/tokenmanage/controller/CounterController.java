package com.abcbank.tokenmanage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abcbank.tokenmanage.consumer.Consumer;
import com.abcbank.tokenmanage.consumer.ConsumerBuilder;
import com.abcbank.tokenmanage.model.Counter;
import com.abcbank.tokenmanage.model.Token;
import com.abcbank.tokenmanage.service.CounterService;


@RestController
public class CounterController {

	@Autowired
	CounterService counterService;
	
	@Autowired
    private ConsumerBuilder consumerBuilder;
	
	private Map<String, Consumer> consumerPool = new HashMap<String, Consumer>();
	
	@GetMapping("/api/counter")
	public List<Counter>  getCounters( ) {
		return counterService.getAllCounters();
	}
	
	@PostMapping("api/counter/{counterName}/{counterOperation}/{counterType}")
	 public String registerCounter(
	            @PathVariable String counterName,
	            @PathVariable String counterOperation,
	            @PathVariable String counterType
	            
	    ) {
	
		consumerPool.put(counterName,consumerBuilder.build(counterName, counterOperation, counterType));
	       
	        return "Registered new counter: " + counterName;
	    }

}
