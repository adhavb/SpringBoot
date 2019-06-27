package com.bhuvan.springboot.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
import com.bhuvan.springboot.model.FoodCategory;
import com.bhuvan.springboot.model.FoodOrder;
import com.bhuvan.springboot.model.FoodProduct;
import com.bhuvan.springboot.model.FoodUser;

@Repository
public class FoodService {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<FoodCategory> getCategories(){
		List<FoodCategory> categoryList = new ArrayList();
		String sql ="SELECT ctgry_id,ctgry_name FROM ang_categories";
		categoryList = jdbcTemplate.query(sql, new ResultSetExtractor<List<FoodCategory>>() {
            public List<FoodCategory> extractData(ResultSet resultSet) throws SQLException,
              DataAccessException {
            	List<FoodCategory> localCategoryList = new ArrayList();
                while (resultSet.next()) {
                	localCategoryList.add(new FoodCategory(resultSet.getInt(1),resultSet.getString(2)));
                	
                }
                return localCategoryList;
            }
          }
      );		
		return categoryList;
	}
	
	public List<FoodProduct> getProducts(){
		List<FoodProduct> productList = new ArrayList();
		String sql ="SELECT a.* FROM ang_products a";
		productList = jdbcTemplate.query(sql, new ResultSetExtractor<List<FoodProduct>>() {
            public List<FoodProduct> extractData(ResultSet resultSet) throws SQLException,
              DataAccessException {
            	List<FoodProduct> localProductList = new ArrayList();
                while (resultSet.next()) {
                	localProductList.add(new FoodProduct(resultSet.getInt(1), resultSet.getString(2),resultSet.getFloat(3),resultSet.getInt(4),resultSet.getString(5)));
                	
                }
                return localProductList;
            }
          }
      );		
		return productList;
	}
	
	/*public List<FoodProduct> getProducts(){
		String sql ="SELECT * FROM ang_products";
		List<FoodProduct> productList = jdbcTemplate.queryForList(sql, FoodProduct.class);
		return productList;
	}*/
	
	public FoodProduct saveProduct(FoodProduct product){
		String sql ="insert into ang_products (title,price,category,url) values(?,?,?,?)";			
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(
		    new PreparedStatementCreator() {
		    	@Override
		        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
		            PreparedStatement ps =
		                connection.prepareStatement(sql, new String[] {"id"});
		            ps.setString(1, product.getTitle());
		            ps.setFloat(2, product.getPrice());
		            ps.setInt(3, product.getCategory());
		            ps.setString(4, product.getImageUrl());
		            return ps;
		        }
		    },
		    keyHolder);
		int generatedKey = keyHolder.getKey().intValue();
		System.out.println(generatedKey);
		FoodProduct foodProduct = getProduct(keyHolder.getKey().intValue());		
		return foodProduct;
	}
	public FoodProduct getProduct(int id){
		String sql ="SELECT id,title,price,category,url FROM ang_products where id = ?";
		FoodProduct product = jdbcTemplate.query(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement preparedStatement) throws
            SQLException {
              preparedStatement.setInt(1, id);
          }
        }, new ResultSetExtractor<FoodProduct>() {
            public FoodProduct extractData(ResultSet resultSet) throws SQLException,
              DataAccessException {
                if (resultSet.next()) {
                	FoodProduct product = new FoodProduct(resultSet.getInt(1), resultSet.getString(2),resultSet.getFloat(3),resultSet.getInt(4),resultSet.getString(5));                	
                	return product;
                }
                return null;
            }
          }
      );
		return product;
	}

	public boolean deleteProduct(int id){
		String sql ="DELETE FROM ang_products where id = ?";
		int count = jdbcTemplate.update(sql, id);
		if(count > 0)
			return true;
		else
			return false;
	}
	
	public boolean updateProduct(FoodProduct foodProduct, int id){
		String sql ="update ang_products set title = ?,price = ?,category=?,url=? where id = ?";
		int count = jdbcTemplate.update(sql, foodProduct.getTitle(),foodProduct.getPrice(),foodProduct.getCategory(),foodProduct.getImageUrl(),id);
		if(count > 0)
			return true;
		else
			return false;
	}
	
	public FoodUser saveUser(FoodUser user){
		FoodUser tempUser = getUser(user);
		if(tempUser!= null && !tempUser.equals(""))
			return tempUser;
		String sql ="insert into ang_users (email) values(?)";			
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(
		    new PreparedStatementCreator() {
		    	@Override
		        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
		            PreparedStatement ps =
		                connection.prepareStatement(sql, new String[] {"id"});
		            ps.setString(1, user.getEmail());		            
		            return ps;
		        }
		    },
		    keyHolder);
		int generatedKey = keyHolder.getKey().intValue();
		System.out.println(generatedKey);
		FoodUser foodUser = getUser(user);		
		return foodUser;		
	}
	public FoodUser getUser(FoodUser user){
		/*String sql = "select * from ang_users where email = ?";
		FoodUser foodUser = jdbcTemplate.queryForObject(sql, new Object[] { user.getEmail() }, new BeanPropertyRowMapper(FoodUser.class));
		System.out.println(foodUser.getEmail());
		return foodUser;*/
		
		String sql ="SELECT * FROM ang_users where email = ?";
		FoodUser foodUser = jdbcTemplate.query(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement preparedStatement) throws
            SQLException {
              preparedStatement.setString(1, user.getEmail());
          }
        }, new ResultSetExtractor<FoodUser>() {
            public FoodUser extractData(ResultSet resultSet) throws SQLException,
              DataAccessException {
                if (resultSet.next()) {
                	FoodUser tmpUser = new FoodUser(resultSet.getInt(1), resultSet.getString(2));                	
                	return tmpUser;
                }
                return null;
            }
          }
      );
		return foodUser;
	}
	public boolean saveCart(FoodOrder order){
		String sql ="insert into ang_user_orders (userid,quantity,productid,price) values(?,?,?,?)";			
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(
		    new PreparedStatementCreator() {
		    	@Override
		        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
		            PreparedStatement ps =
		                connection.prepareStatement(sql, new String[] {"id"});
		            ps.setString(1, order.getUserid());		            
		            ps.setInt(2, order.getQuantity());
		            ps.setInt(3, order.getProductid());		            
		            ps.setDouble(4, order.getPrice());
		            return ps;
		        }
		    },
		    keyHolder);
		int generatedKey = keyHolder.getKey().intValue();
		System.out.println(generatedKey);
		if(generatedKey > 0) return true;
		return false;
		/*List<FoodOrder> foodOrderList = getOrder(order.getUserid());		
		return foodOrderList;*/		
	}
	
	public boolean deleteCart(int id){
		String sql ="DELETE FROM ang_user_orders where id = ?";
		int count = jdbcTemplate.update(sql, id);
		if(count > 0)
			return true;
		else
			return false;
	}
	
	public boolean clearCart(String userId){
		String sql ="DELETE FROM ang_user_orders where userid = ?";
		int count = jdbcTemplate.update(sql, userId);
		if(count > 0)
			return true;
		else
			return false;
	}
	
	public boolean updateCart(String quantity, String id){
		String sql ="update ang_user_orders set quantity = ? where id = ?";
		int count = jdbcTemplate.update(sql, quantity,id);
		if(count > 0)
			return true;
		else
			return false;
	}
	public List<FoodOrder> getOrder(String userId){
		/*String sql ="select * from ang_user_orders where userid = ?";
		return jdbcTemplate.queryForList(sql, new Object[] { userId },FoodOrder.class);	*/
		List<FoodOrder> orders = new ArrayList<FoodOrder>();
		String sql ="select * from ang_user_orders where userid = ?";
		List<FoodOrder> foodOrderList = jdbcTemplate.query(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement preparedStatement) throws
            SQLException {
              preparedStatement.setString(1, userId);
          }
        }, new ResultSetExtractor<List<FoodOrder>>() {
            public List<FoodOrder> extractData(ResultSet resultSet) throws SQLException,
              DataAccessException {
            	List<FoodOrder> localOrderList = new ArrayList();
                while (resultSet.next()) {
                	FoodOrder tmpOrder = new FoodOrder(resultSet.getString(2),resultSet.getInt(3),resultSet.getInt(4),resultSet.getDouble(5));    
                	tmpOrder.setId(resultSet.getInt(1));
                	localOrderList.add(tmpOrder);                	
                }
                return localOrderList;
            }
          }
      );	
		return foodOrderList;		
	}
}

