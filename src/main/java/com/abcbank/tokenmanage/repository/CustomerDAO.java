package com.abcbank.tokenmanage.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.abcbank.tokenmanage.model.Customer;

@Repository
public class CustomerDAO implements CustomerDAOInterface {

	@Autowired
	JdbcTemplate jdbcTempl;

	@Override
	public Customer saveCustomer(Customer customer) {
		final String sql = "insert into customer(name,phonenumber,address,customertype) values(?,?,?,?) ";
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTempl.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {

				PreparedStatement ps = con.prepareStatement(sql.toString(),new String[] {"customerid"});
				ps.setString(1, customer.getCustomerName());
				ps.setString(2, customer.getPhoneNumber());
				ps.setString(3, customer.getAddress());
				ps.setString(4, customer.getCustomerType().toString());
				return ps;
			}
		}, holder);
		Long id = (long) holder.getKey().intValue();
		customer.setCustomerId(id);
		return customer;
	}

}
