package com.abcbank.tokenmanage.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.abcbank.tokenmanage.model.Customer;
import com.abcbank.tokenmanage.model.CustomerType;
import com.abcbank.tokenmanage.model.ServiceType;
import com.abcbank.tokenmanage.model.Token;
import com.abcbank.tokenmanage.model.TokenStatus;

@Repository
public class TokenDAO implements TokenDAOInterface {

	@Autowired
	JdbcTemplate jdbcTempl;

	@Override
	public Token createToken(Token token) {
		KeyHolder holder = new GeneratedKeyHolder();
		final String sql = "insert into token(tokentype,customerid,servicetype,comments,status) values(?,?,?,?,?)";
		jdbcTempl.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {

				PreparedStatement ps = con.prepareStatement(sql.toString(),new String[] {"tokenid"});
				ps.setString(1, token.getTokenType().toString());
				ps.setInt(2, token.getCustomerId().intValue());
				ps.setString(3, token.getServiceType().toString());
				ps.setString(4, "intial comment");
				ps.setString(5, TokenStatus.CREATED.toString());
				return ps;
			}
		}, holder);
		Long id = (long) holder.getKey().intValue();
		token.setTokenId(id);
		token.setTokenStatus(TokenStatus.CREATED);
		token.setComments("intial comment");
		return token;
	}

	public List<Token> getTokens() {
		// TODO Auto-generated method stub
		List<Token> tokenList = new ArrayList<>();
		Token token = new Token();
		token.setServiceType(ServiceType.DEPOSIT);
		token.setTokenId((long) 1);
		token.setTokenStatus(TokenStatus.CREATED);
		Customer cust = new Customer();
		cust.setAddress("SantoshNagar");
		cust.setCustomerId(1);
		cust.setCustomerName("azhar");
		cust.setCustomerType(CustomerType.PREMIUM);
		cust.setPhoneNumber("9494940808");
		token.setCustomer(cust);
		tokenList.add(token);

		return tokenList;
	}

}
