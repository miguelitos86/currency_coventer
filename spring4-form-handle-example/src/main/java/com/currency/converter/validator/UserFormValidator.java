package com.currency.converter.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.currency.converter.model.User;
import com.currency.converter.service.UserService;

@Component
public class UserFormValidator implements Validator {

	@Autowired
	@Qualifier( "emailValidator" )
	EmailValidator emailValidator;

	@Autowired
	UserService userService;

	@Override
	public boolean supports( Class< ? > clazz ) {
		return User.class.equals( clazz );
	}

	@Override
	public void validate( Object target, Errors errors ) {

		User user = ( User ) target;

		ValidationUtils.rejectIfEmptyOrWhitespace( errors, "name",
				"NotEmpty.userForm.name" );
		ValidationUtils.rejectIfEmptyOrWhitespace( errors, "email",
				"NotEmpty.userForm.email" );
		ValidationUtils.rejectIfEmptyOrWhitespace( errors, "password",
				"NotEmpty.userForm.password" );

		 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword",
		 "NotEmpty.userForm.confirmPassword");
		 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "country",
		 "NotEmpty.userForm.country");
		 

		if ( !emailValidator.valid( user.getEmail() ) ) {
			errors.rejectValue( "email", "Pattern.userForm.email" );
		}
		if ( user.getCountry().equalsIgnoreCase( "none" ) ) {
			errors.rejectValue( "country", "NotEmpty.userForm.country" );
		}

		if ( !user.getPassword().equals( user.getConfirmPassword() ) ) {
			errors.rejectValue( "confirmPassword",
					"Diff.userform.confirmPassword" );
		}

	}

}