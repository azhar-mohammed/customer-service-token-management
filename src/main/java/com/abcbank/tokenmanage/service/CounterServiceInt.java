package com.abcbank.tokenmanage.service;

import java.util.List;

import com.abcbank.tokenmanage.model.Counter;

public interface CounterServiceInt {

	public List<Counter> getAllCounters();
	public Counter     saveCounter(Counter counter);

}
