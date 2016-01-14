package com.currency.converter.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.sf.ehcache.CacheManager;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import com.currency.converter.model.ExchangeQuery;

public class ExchangeQueryDaoImpl implements ExchangeQueryDao {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings( "unchecked" )
	@Override
	public List< ExchangeQuery > findAll() {
		return entityManager.createQuery( "SELECT eq FROM ExchangeQuery eq" )
				.getResultList();
	}

	@SuppressWarnings( "unchecked" )
	@Override
	@Cacheable("findAll") 
	public List< ExchangeQuery > findAll( Integer userId ) {
		 Query query = entityManager.createQuery( "FROM ExchangeQuery WHERE UserId = :userId ORDER BY CreatedDate DESC LIMIT 10" )
				.setParameter( "userId", userId );
		 return query.getResultList();
	}

	@Override
	@Transactional
	public void save( ExchangeQuery exchangeQuery ) {
		exchangeQuery.setCreatedDate( new Date() );
		entityManager.persist( exchangeQuery );
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
	public ExchangeQuery findById( Integer id ) {
		Query query = entityManager.createQuery( "FROM ExchangeQuery WHERE ID = :id" )
				.setParameter( "id", id );
		List< ExchangeQuery > list = query.getResultList();

		if ( !list.isEmpty() ) {
			return ( ExchangeQuery ) list.get( 0 );
		} else {
			return null;
		}
	}
}