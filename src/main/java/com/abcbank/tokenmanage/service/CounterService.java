package com.abcbank.tokenmanage.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

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
	
	ExecutorService executor;
	
	@Override
	public List<Counter> getAllCounters() {

		return counterRepo.findAll();
	}

	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
	//    List<Counter> counterList = new ArrayList<Counter>();
	    /*Counter counter = new Counter();
	    counter.setCounterServiceType(ServiceType.DEPOSIT);
	    counter.setCounterType(CustomerType.REGULAR.toString());
	    counter.setCounterName("regularDepositCounter");
	    counterRepo.saveAndFlush(counter);
	    counter.setCounterId(0);
	    counter.setCounterType(CustomerType.PREMIUM.toString());
	    counter.setCounterName("premiumDepositCounter");
	    counterRepo.saveAndFlush(counter);
	    counter.setCounterId(0);
	    counter.setCounterServiceType(ServiceType.WITHDRAW);
	    counter.setCounterName("premiumWithdrawCounter");
	    counterRepo.saveAndFlush(counter);
	    counter.setCounterId(0);
	    counter.setCounterType(CustomerType.REGULAR.toString());
	    counter.setCounterName("regularWithdrawCounter");
	    counterRepo.saveAndFlush(counter);*/
	  //  List<Counter> counterList=counterRepo.findAll();
	  //  executor = Executors.newFixedThreadPool(counterList.size());
	    
//	    counterList.stream().filter(counter->counter.getCounterServiceType().)	    
	}

	@Override
	public Counter saveCounter(Counter counter) {
		
		return	counterRepo.saveAndFlush(counter);
	}
}
