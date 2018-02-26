/**
 * 
 */
package com.abcbank.tokenmanage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abcbank.tokenmanage.model.TokenCounter;

/**
 * @author azharm
 *
 */
public interface TokenCounterRepository extends JpaRepository<TokenCounter,Integer> {

}
