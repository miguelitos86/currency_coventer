package com.currency.converter.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.sf.ehcache.CacheManager;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import com.currency.converter.model.CurrencyExchangeQuery;

public class CurrencyExchangeQueryDaoImpl implements CurrencyExchangeQueryDao {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings( "unchecked" )
	@Override
	public List< CurrencyExchangeQuery > findAll() {
		return entityManager.createQuery( "SELECT eq FROM CurrencyExchangeQuery eq" )
				.getResultList();
	}

	@SuppressWarnings( "unchecked" )
	@Override
	@Cacheable("findAll") 
	public List< CurrencyExchangeQuery > findAll( Integer userId ) {
		 Query query = entityManager.createQuery( "FROM CurrencyExchangeQuery WHERE UserId = :userId ORDER BY CreatedDate DESC LIMIT 10" )
				.setParameter( "userId", userId );
		 return query.getResultList();
	}

	@Override
	@Transactional
	public void save( CurrencyExchangeQuery currencyExchangeQuery ) {
		currencyExchangeQuery.setCreatedDate( new Date() );
		entityManager.persist( currencyExchangeQuery );
		CacheManager.getInstance().getEhcache("findAll").removeAll();
	}

	@Override
	@Transactional
	public void delete( Integer id ) {
		entityManager.remove( findById( id ) );
		CacheManager.getInstance().getEhcache("findAll").removeAll();

	}
	
	@SuppressWarnings( "unchecked" )
	@Override
	public CurrencyExchangeQuery findById( Integer id ) {
		Query query = entityManager.createQuery( "FROM CurrencyExchangeQuery WHERE ID = :id" )
				.setParameter( "id", id );
		List< CurrencyExchangeQuery > list = query.getResultList();

		if ( !list.isEmpty() ) {
			return ( CurrencyExchangeQuery ) list.get( 0 );
		} else {
			return null;
		}
	}
}