package com.abcbank.tokenmanage;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


/**
 * 
 * @author azharm
 *
 */

@SpringBootApplication
public class CustomerServiceTokenManagementApplication  {

   public static void main(String[] args) {
		SpringApplication.run(CustomerServiceTokenManagementApplication.class, args);
	}
   
   @Bean
   public ModelMapper modelMapper() {
       return new ModelMapper();
   }
}
