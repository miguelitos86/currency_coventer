package com.currency.converter.dao;

import java.util.List;

import com.currency.converter.model.CurrencyExchangeQuery;

public interface CurrencyExchangeQueryDao {

	List< CurrencyExchangeQuery > findAll( Integer userId );

	void save( CurrencyExchangeQuery currencyExchangeQuery );

	void delete( Integer id );
	
	CurrencyExchangeQuery findById( Integer id );
}