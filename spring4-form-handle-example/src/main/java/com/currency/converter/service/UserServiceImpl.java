package com.currency.converter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.currency.converter.dao.UserDao;
import com.currency.converter.model.User;

@Service( "userService" )
public class UserServiceImpl implements UserService {

	UserDao userDao;

	@Autowired
	public void setUserDao( UserDao userDao ) {
		this.userDao = userDao;
	}

	@Override
	public User findById( Integer id ) {
		return userDao.findById( id );
	}

	@Override
	public List< User > findAll() {
		return userDao.findAll();
	}

	@Override
	public void saveOrUpdate( User user ) {

		if ( findById( user.getId() ) == null ) {
			userDao.save( user );
		} else {
			userDao.update( user );
		}

	}

	@Override
	public void delete( Integer id ) {
		userDao.delete( id );
	}


	@Override
	public User findByEmail( String email ) {
		return userDao.findByEmail( email );
	}

}