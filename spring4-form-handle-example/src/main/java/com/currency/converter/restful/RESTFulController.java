/**
 * 
 */
package com.currency.converter.restful;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.currency.converter.model.User;
import com.currency.converter.service.UserService;

/**
 * @author Miguel del Prado Aranda
 * @email m.delpradoaranda@gmail.com
 * @date 7/1/2016
 */

@RestController
public class RESTFulController {

	@Autowired
	UserService userService; // Service which will do all data
								// retrieval/manipulation work

	// -------------------Retrieve All
	// Users--------------------------------------------------------

	@RequestMapping( value = "/user_restful/", method = RequestMethod.GET )
	public ResponseEntity< List< User >> listAllUsers() {
		List< User > users = userService.findAll();
		if ( users.isEmpty() ) {
			return new ResponseEntity< List< User >>( HttpStatus.NO_CONTENT );
		}
		return new ResponseEntity< List< User >>( users, HttpStatus.OK );
	}

	// -------------------Retrieve Single
	// User--------------------------------------------------------

	@RequestMapping( value = "/user_restful/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity< User > getUser( @PathVariable( "id" ) Integer id ) {
		System.out.println( "Fetching User with id " + id );
		User user = userService.findById( id );
		if ( user == null ) {
			System.out.println( "User with id " + id + " not found" );
			return new ResponseEntity< User >( HttpStatus.NOT_FOUND );
		}
		return new ResponseEntity< User >( user, HttpStatus.OK );
	}

	// -------------------Create a
	// User--------------------------------------------------------

	@RequestMapping( value = "/user_restful/", method = RequestMethod.POST )
	public ResponseEntity< Void > createUser( @RequestBody User user,
			UriComponentsBuilder ucBuilder ) {
		System.out.println( "Creating User " + user.getName() );

		if ( userService.findById( 0 ) != null ) {
			System.out.println( "A User with name " + user.getName()
					+ " already exist" );
			return new ResponseEntity< Void >( HttpStatus.CONFLICT );
		}

		userService.saveOrUpdate( user );

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation( ucBuilder.path( "/user/{id}" )
				.buildAndExpand( user.getId() ).toUri() );
		return new ResponseEntity< Void >( headers, HttpStatus.CREATED );
	}

	// ------------------- Update a User
	// --------------------------------------------------------

	@RequestMapping( value = "/user_restful/{id}", method = RequestMethod.PUT )
	public ResponseEntity< User > updateUser( @PathVariable( "id" ) Integer id,
			@RequestBody User user ) {
		System.out.println( "Updating User " + id );

		User currentUser = userService.findById( id );

		if ( currentUser == null ) {
			System.out.println( "User with id " + id + " not found" );
			return new ResponseEntity< User >( HttpStatus.NOT_FOUND );
		}

		currentUser.setName( user.getName() );


		userService.saveOrUpdate( currentUser );
		return new ResponseEntity< User >( currentUser, HttpStatus.OK );
	}

	// ------------------- Delete a User
	// --------------------------------------------------------

	@RequestMapping( value = "/user_restful/{id}", method = RequestMethod.DELETE )
	public ResponseEntity< User > deleteUser( @PathVariable( "id" ) Integer id ) {
		System.out.println( "Fetching & Deleting User with id " + id );

		User user = userService.findById( id );
		if ( user == null ) {
			System.out.println( "Unable to delete. User with id " + id
					+ " not found" );
			return new ResponseEntity< User >( HttpStatus.NOT_FOUND );
		}

		userService.delete( id );
		return new ResponseEntity< User >( HttpStatus.NO_CONTENT );
	}

}
