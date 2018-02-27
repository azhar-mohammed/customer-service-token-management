package com.abcbank.tokenmanage.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abcbank.tokenmanage.consumer.Consumer;
import com.abcbank.tokenmanage.consumer.ConsumerBuilder;
import com.abcbank.tokenmanage.model.Counter;
import com.abcbank.tokenmanage.model.Token;
import com.abcbank.tokenmanage.service.CounterService;
import com.abcbank.tokenmanage.service.CounterServiceInt;
import com.abcbank.tokenmanage.service.TokenService;

/**
 * 
 * @author azharm
 *
 */
@RestController
public class CounterController {

	@Autowired
	CounterServiceInt counterService;

	@Autowired
	TokenService tokenService;

	@Autowired
	private ConsumerBuilder consumerBuilder;

	private HashSet<String> consumerPoolSet = new HashSet<String>();

	@GetMapping("/api/counter")
	public List<Counter> getCounters() {
		return counterService.getAllCounters();
	}

	@PostMapping("api/counter")
	public String registerCounter(@RequestBody Counter counter) {
		if(consumerPoolSet.add(counter.getCounterName())) 
		{
			Counter savedCounter = counterService.saveCounter(counter);
		   consumerBuilder.build(savedCounter,tokenService);
		    return "Successfully registered counter with counter name"+counter.getCounterName();
		}
		else
		{
			return "Counter "+counter.getCounterName()+" is already present ";
		}
		

	}

}
