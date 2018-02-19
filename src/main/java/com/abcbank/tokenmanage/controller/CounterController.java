package com.abcbank.tokenmanage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abcbank.tokenmanage.model.Counter;
import com.abcbank.tokenmanage.model.Token;
import com.abcbank.tokenmanage.service.CounterService;

@RestController
public class CounterController {

	@Autowired
	CounterService counterService;
	
	@GetMapping("/api/counter")
	public List<Counter>  getCounters( ) {
		return counterService.getAllCounters();
	}
}
