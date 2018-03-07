package com.abcbank.tokenmanage.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abcbank.tokenmanage.consumer.RabbitMQConsumerBuilder;
import com.abcbank.tokenmanage.dto.CounterDTO;
import com.abcbank.tokenmanage.service.CounterService;
import com.abcbank.tokenmanage.service.TokenServiceImplementation;

/**
 * 
 * @author azharm
 *
 */
@RestController
public class CounterController {

	@Autowired
	CounterService counterService;

	@Autowired
	TokenServiceImplementation tokenService;

	@Autowired
	private RabbitMQConsumerBuilder consumerBuilder;

	/**
	 * Returns a list of counters and corresponding to it the tokens which have been processed and are currently being processed 
	 * @return
	 */

	@GetMapping("/api/counter")
	public ResponseEntity<List<CounterDTO>> getCounters() {
		
		return new ResponseEntity<List<CounterDTO>>(counterService.getAllCounters(),HttpStatus.OK);
	}
	
	/**
	 * Creates a counter which is a rabbitMq consumer based on the services and the type of counter provided.
	 * @param counterDTO
	 * @return
	 */

	@PostMapping("api/counter")
	public ResponseEntity<CounterDTO> registerCounter(@RequestBody CounterDTO counterDTO) {

		CounterDTO savedCounterDTO = counterService.saveCounter(counterDTO); 
		
		System.out.println("counterDTO is "+savedCounterDTO);
		
		consumerBuilder.build(savedCounterDTO,tokenService);
				
		return new ResponseEntity<CounterDTO>(savedCounterDTO,HttpStatus.OK);
		

	}

}
