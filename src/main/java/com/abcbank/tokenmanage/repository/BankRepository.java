/**
 * 
 */
package com.abcbank.tokenmanage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abcbank.tokenmanage.model.Bank;

/**
 * @author azharm
 *
 */
public interface BankRepository extends JpaRepository<Bank, Integer> {

}
