package com.abcbank.tokenmanage.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.abcbank.tokenmanage.model.Counter;

@Repository
public class CounterDAO implements CounterDAOInterface {

	@Autowired
	JdbcTemplate jdbcTempl;

	@Override
	public List<Counter> getAllCounter() {
		String query = "select * from counter";
		List<Counter> counterList = jdbcTempl.query(query, new RowMapper<Counter>() {

			@Override
			public Counter mapRow(ResultSet rs, int rowNum) throws SQLException {
				Counter counter = new Counter();
				counter.setCounterId(rs.getLong(1));
				counter.setCounterServiceType(rs.getString(2));
				counter.setCounterType(rs.getString(3));
				return counter;
			}

		});
		return counterList;

	}

}
