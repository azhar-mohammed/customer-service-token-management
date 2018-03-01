package com.abcbank.tokenmanage.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abcbank.tokenmanage.consumer.Consumer;
import com.abcbank.tokenmanage.consumer.ConsumerBuilder;
import com.abcbank.tokenmanage.dto.CounterDTO;
import com.abcbank.tokenmanage.model.Counter;
import com.abcbank.tokenmanage.model.Token;
import com.abcbank.tokenmanage.service.CounterService;
import com.abcbank.tokenmanage.service.CounterServiceImplementation;
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
	private ConsumerBuilder consumerBuilder;


	@GetMapping("/api/counter")
	public ResponseEntity<List<CounterDTO>> getCounters() {
		
		return new ResponseEntity<List<CounterDTO>>(counterService.getAllCounters(),HttpStatus.OK);
	}

	@PostMapping("api/counter")
	public ResponseEntity<CounterDTO> registerCounter(@RequestBody CounterDTO counterDTO) {

		CounterDTO savedCounterDTO = counterService.saveCounter(counterDTO);
		
		consumerBuilder.build(savedCounterDTO,tokenService);
				
		return new ResponseEntity<CounterDTO>(savedCounterDTO,HttpStatus.OK);
		

	}

}
