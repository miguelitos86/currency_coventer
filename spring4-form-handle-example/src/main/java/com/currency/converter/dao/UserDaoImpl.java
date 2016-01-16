package com.currency.converter.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import com.currency.converter.model.User;

public class UserDaoImpl implements UserDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings( "unchecked" )
	@Override
	public User findById( Integer id ) {
		Query query = entityManager.createQuery( "FROM User WHERE UserID = :id" )
				.setParameter( "id", id );
		List< User > list = query.getResultList();

		if ( !list.isEmpty() ) {
			return ( User ) list.get( 0 );
		} else {
			return null;
		}
	}
	
	@SuppressWarnings( "unchecked" )
	@Override
	public User findByUserName( String name ) {
		Query query = entityManager.createQuery( "FROM User WHERE Name = :name" )
				.setParameter( "name", name );
		List< User > list = query.getResultList();

		if ( !list.isEmpty() ) {
			return ( User ) list.get( 0 );
		} else {
			return null;
		}
	}

	@SuppressWarnings( "unchecked" )
	@Override
	@Cacheable("empcache") 
	public List< User > findAll() {
		return entityManager.createQuery( "SELECT u FROM User u" )
				.getResultList();
	}

	@Override
	@Transactional
	public void save( User user ) {
		entityManager.persist( user );

	}

	@Override
	@Transactional
	public void update( User user ) {
		entityManager.merge( user );
	}

	@Override
	@Transactional
	public void delete( Integer id ) {
		entityManager.remove( findById( id ) );

	}

	@SuppressWarnings( "unchecked" )
	@Override
	public User findByEmail( String email ) {
		Query query = entityManager.createQuery( "FROM User WHERE EMail = :email" )
				.setParameter( "email", email );
		List< User > list = query.getResultList();

		if ( !list.isEmpty() ) {
			return ( User ) list.get( 0 );
		} else {
			return null;
		}
	}

}