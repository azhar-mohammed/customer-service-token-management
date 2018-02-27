/**
 * 
 */
package com.abcbank.tokenmanage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abcbank.tokenmanage.model.TokenCounter;

/**
 * @author azharm
 *
 */
public interface TokenCounterRepository extends JpaRepository<TokenCounter,Integer> {

	TokenCounter findByCounterId(int counterId);

	
}
