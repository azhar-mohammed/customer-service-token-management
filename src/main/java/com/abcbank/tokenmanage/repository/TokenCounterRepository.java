/**
 * 
 */
package com.abcbank.tokenmanage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abcbank.tokenmanage.model.TokenCounterMapping;

/**
 * @author azharm
 *
 */
public interface TokenCounterRepository extends JpaRepository<TokenCounterMapping,Integer> {

	List<TokenCounterMapping> findAllByCounterId(int counterId);

	
}
