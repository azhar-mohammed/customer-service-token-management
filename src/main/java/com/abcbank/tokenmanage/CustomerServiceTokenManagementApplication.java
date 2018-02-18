package com.abcbank.tokenmanage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;



@SpringBootApplication
public class CustomerServiceTokenManagementApplication  {

   public static void main(String[] args) {
		SpringApplication.run(CustomerServiceTokenManagementApplication.class, args);
	}
}
