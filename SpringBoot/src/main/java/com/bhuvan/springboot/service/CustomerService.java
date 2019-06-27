package com.bhuvan.springboot.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.bhuvan.springboot.controller.CustomerController;
import com.bhuvan.springboot.model.Customers;


@Repository
public class CustomerService {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Customers> findAll(){
		List<Customers> customerList = new ArrayList();
		String sql ="SELECT id,name,passport_number FROM customers";
		customerList = jdbcTemplate.query(sql, new ResultSetExtractor<List<Customers>>() {
            public List<Customers> extractData(ResultSet resultSet) throws SQLException,
              DataAccessException {
            	List<Customers> localCustomerList = new ArrayList();
                while (resultSet.next()) {
                	//localCustomerList.add(new Customers(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3)));
                	Customers localCustomer = new Customers(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3));
                	Link link = ControllerLinkBuilder
                             .linkTo(CustomerController.class)
                             .slash(resultSet.getInt(1))
                             .withSelfRel();
                	localCustomer.add(link);
                	localCustomerList.add(localCustomer);
                }
                return localCustomerList;
            }
          }
      );
		
		return customerList;
	}
	public List<Customers> findByName(String name){
		String sql = "SELECT ID,NAME,PASSPORT_NUMBER AS PASSPORTNUMBER FROM CUSTOMERS WHERE NAME LIKE '%?%'";
		List<Customers> customerList = jdbcTemplate.query(sql, new PreparedStatementSetter(){
			public void setValues(PreparedStatement preparedStatement) throws SQLException{
				preparedStatement.setString(1, name);
			}
		},new ResultSetExtractor<List<Customers>>(){
			public List<Customers> extractData(ResultSet resultSet) throws SQLException,DataAccessException{
				List<Customers> localCustomerList = new ArrayList();
				while(resultSet.next()){
					Customers localCustomer = new Customers(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3));
					localCustomerList.add(localCustomer);
				}
				return localCustomerList;
			}
		});
		return customerList;
	}
	public Customers findById(int id){
		String sql ="SELECT id,name,passport_number as passportNumber FROM customers where id = ?";
		Customers customer = jdbcTemplate.query(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement preparedStatement) throws
            SQLException {
              preparedStatement.setInt(1, id);
          }
        }, new ResultSetExtractor<Customers>() {
            public Customers extractData(ResultSet resultSet) throws SQLException,
              DataAccessException {
                if (resultSet.next()) {
                	Customers localCustomer = new Customers(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3));
                	//Link link = ControllerLinkBuilder.linkTo(CustomerController.class).slash(id).withRel("LinkToSelf");
                	Link link = ControllerLinkBuilder.linkTo(CustomerController.class).slash(id).withSelfRel();
                	localCustomer.add(link);
                	return localCustomer;
                }
                return null;
            }
          }
      );
		return customer;
		
	}
	
	public void deleteById(int id){
		String sql ="delete from customers where id = ?";
		jdbcTemplate.execute(sql);		
	}
	
	public Customers save(Customers customers){
		String sql ="insert into customers (name,passport_number) values(?,?)";
		//jdbcTemplate.update(sql, customers.getName(), customers.getPassportNumber());		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(
		    new PreparedStatementCreator() {
		    	@Override
		        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
		            PreparedStatement ps =
		                connection.prepareStatement(sql, new String[] {"id"});
		            ps.setString(1, customers.getName());
		            ps.setString(2, customers.getPassportNumber());
		            
		            return ps;
		        }
		    },
		    keyHolder);
		int generatedKey = keyHolder.getKey().intValue();
		System.out.println(generatedKey);
		Customers customer = findById(keyHolder.getKey().intValue());
		/*Link link = ControllerLinkBuilder
				.linkTo(CustomerController.class)
				.slash(customer.getCustomerId())
				.withSelfRel();
		customer.add(link);*/
		return customer;
			
	}
	public Customers update(Customers customers,int id){
		String sql ="update customers set name=?,passport_number=? where id = ?";
		jdbcTemplate.update(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement preparedStatement) throws
            SQLException {
              preparedStatement.setString(1, customers.getName());
              preparedStatement.setString(2, customers.getPassportNumber());
              preparedStatement.setInt(3, id);
          }
        });
		return findById(id);		
	}

}

