package com.abcbank.tokenmanage.service;

import java.util.List;

import com.abcbank.tokenmanage.dto.CounterDTO;

/**
 * 
 * @author azharm
 *
 */
public interface CounterService {

	public List<CounterDTO> getAllCounters();

	public CounterDTO saveCounter(CounterDTO counter);

}
