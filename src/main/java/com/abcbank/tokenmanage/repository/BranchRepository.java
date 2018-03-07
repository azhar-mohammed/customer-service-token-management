/**
 * 
 */
package com.abcbank.tokenmanage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abcbank.tokenmanage.model.Branch;

/**
 * @author azharm
 *
 */
public interface BranchRepository extends JpaRepository<Branch,Integer> {

}
