package com.abcbank.tokenmanage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.abcbank.tokenmanage.model.Counter;
import com.abcbank.tokenmanage.repository.CounterDAO;

@Service
public class CounterService implements CounterServiceInt {

	@Autowired
	CounterDAO counterDAO;

	@Override
	public List<Counter> getAllCounters() {

		return counterDAO.getAllCounter();
	}

	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
	    System.out.println("hello world, I have just started up");
	}
}
