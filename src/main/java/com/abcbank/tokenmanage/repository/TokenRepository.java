package com.abcbank.tokenmanage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.abcbank.tokenmanage.model.*;

/**
 * @author azharm
 *
 */
public interface TokenRepository extends JpaRepository<Token,Integer>{

}
