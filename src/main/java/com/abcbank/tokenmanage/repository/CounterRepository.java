package com.abcbank.tokenmanage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abcbank.tokenmanage.model.Counter;

/**
 * @author azharm
 *
 */
public interface CounterRepository extends JpaRepository<Counter,Integer> {

}
