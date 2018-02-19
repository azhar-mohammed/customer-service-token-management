package com.abcbank.tokenmanage.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.abcbank.tokenmanage.model.Counter;
import com.abcbank.tokenmanage.model.CustomerType;
import com.abcbank.tokenmanage.model.ServiceType;
import com.abcbank.tokenmanage.repository.CounterRepository;

@Service
public class CounterService implements CounterServiceInt {

	@Autowired
	CounterRepository counterRepo;
	
	@Override
	public List<Counter> getAllCounters() {

		return counterRepo.findAll();
	}

	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
	    List<Counter> counterList = new ArrayList<Counter>();
	    Counter counter = new Counter();
	    counter.setCounterServiceType(ServiceType.DEPOSIT);
	    counter.setCounterType(CustomerType.REGULAR.toString());
	    counterRepo.saveAndFlush(counter);
	    counter.setCounterId(0);
	    counter.setCounterType(CustomerType.PREMIUM.toString());
	    counterRepo.saveAndFlush(counter);
	    counter.setCounterId(0);
	    counter.setCounterServiceType(ServiceType.WITHDRAW);
	    counterRepo.saveAndFlush(counter);
	    counter.setCounterId(0);
	    counterRepo.saveAndFlush(counter);
	    counter.setCounterId(0);
	    counter.setCounterType(CustomerType.REGULAR.toString());
	    counterRepo.saveAndFlush(counter);
	 
	    
	}
}
