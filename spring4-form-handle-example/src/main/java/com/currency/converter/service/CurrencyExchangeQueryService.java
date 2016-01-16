package com.currency.converter.service;

import java.util.List;

import com.currency.converter.model.CurrencyExchangeQuery;

public interface CurrencyExchangeQueryService {

	List< CurrencyExchangeQuery > findAll();

	List< CurrencyExchangeQuery > findAll( Integer userId );

	void save( CurrencyExchangeQuery currencyExchangeQuery );

	void delete( Integer id );

	CurrencyExchangeQuery findById( Integer id );
}