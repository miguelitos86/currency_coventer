package com.currency.converter.service;

import java.util.List;

import com.currency.converter.model.User;

public interface UserService {

	User findById( Integer id );

	List< User > findAll();

	void saveOrUpdate( User user );

	void delete( Integer id );

	User findByEmail( String email );
}