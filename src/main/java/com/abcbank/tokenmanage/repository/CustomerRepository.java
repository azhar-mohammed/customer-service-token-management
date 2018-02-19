/**
 * 
 */
package com.abcbank.tokenmanage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abcbank.tokenmanage.model.Customer;

/**
 * @author azharm
 *
 */
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

}
