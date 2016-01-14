package com.currency.converter.dao;

import java.util.List;

import com.currency.converter.model.User;

public interface UserDao {

	User findById( Integer id );

	List< User > findAll();

	void save( User user );

	void update( User user );

	void delete( Integer id );

	User findByUserName( String name );

	User findByEmail( String email );
}