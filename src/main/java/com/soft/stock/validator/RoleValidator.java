/**
 * 
 */
package com.soft.stock.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.soft.stock.entities.Role;
import com.soft.stock.services.SecurityService;


@Component
public class RoleValidator implements Validator
{
	@Autowired protected MessageSource messageSource;
	@Autowired protected SecurityService securityService;
	
	@Override
	public boolean supports(Class<?> clazz)
	{
		return Role.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors)
	{
		Role role = (Role) target;
		String name = role.getName();
		Role RoleByName = securityService.getRoleByName(name);
		if(RoleByName != null){
			errors.rejectValue("name", "error.exists",new Object[]{name}, "Role "+name+" already exists");
		}
	}
	
	
}
