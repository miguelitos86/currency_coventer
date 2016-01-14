package com.currency.converter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.currency.converter.dao.ExchangeQueryDao;
import com.currency.converter.model.CurrencyExchangeQuery;

@Service( "exchangeQueryService" )
public class ExchangeQueryServiceImpl implements ExchangeQueryService {

	ExchangeQueryDao exchangeQueryDao;

	@Autowired
	public void setExchangeQueryDao( ExchangeQueryDao exchangeQueryDao ) {
		this.exchangeQueryDao = exchangeQueryDao;
	}

	@Override
	public List< CurrencyExchangeQuery > findAll() {
		return exchangeQueryDao.findAll();
	}

	@Override
	public List< CurrencyExchangeQuery > findAll( Integer userId ) {
		return exchangeQueryDao.findAll( userId );
	}

	@Override
	public void save( CurrencyExchangeQuery currencyExchangeQuery ) {
		exchangeQueryDao.save( currencyExchangeQuery );
	}

	@Override
	public void delete( Integer id ) {
		exchangeQueryDao.delete( id );

	}

	@Override
	public CurrencyExchangeQuery findById( Integer id ) {
		return exchangeQueryDao.findById( id );
	}

}