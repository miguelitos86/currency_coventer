package com.currency.converter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.currency.converter.dao.ExchangeQueryDao;
import com.currency.converter.model.ExchangeQuery;

@Service( "exchangeQueryService" )
public class ExchangeQueryServiceImpl implements ExchangeQueryService {

	ExchangeQueryDao exchangeQueryDao;

	@Autowired
	public void setExchangeQueryDao( ExchangeQueryDao exchangeQueryDao ) {
		this.exchangeQueryDao = exchangeQueryDao;
	}

	@Override
	public List< ExchangeQuery > findAll() {
		return exchangeQueryDao.findAll();
	}

	@Override
	public List< ExchangeQuery > findAll( Integer userId ) {
		return exchangeQueryDao.findAll( userId );
	}

	@Override
	public void save( ExchangeQuery exchangeQuery ) {
		exchangeQueryDao.save( exchangeQuery );
	}

	@Override
	public void delete( Integer id ) {
		exchangeQueryDao.delete( id );
		
	}

	@Override
	public ExchangeQuery findById( Integer id ) {
		return exchangeQueryDao.findById( id );
	}
	


}