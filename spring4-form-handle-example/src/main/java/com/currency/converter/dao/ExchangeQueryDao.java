package com.currency.converter.dao;

import java.util.List;

import com.currency.converter.model.ExchangeQuery;

public interface ExchangeQueryDao {

	List< ExchangeQuery > findAll();

	List< ExchangeQuery > findAll( Integer userId );

	void save( ExchangeQuery exchangeQuery );

	void delete( Integer id );
	
	ExchangeQuery findById( Integer id );
}