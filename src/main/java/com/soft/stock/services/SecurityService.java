package com.soft.stock.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.soft.stock.entities.Permission;
import com.soft.stock.entities.Role;
import com.soft.stock.entities.User;

@Service
public interface SecurityService {

	public User findUserByEmail(String email);
	public String resetPassword(String email);
	public void updatePassword(String email, String token, String password);
	public boolean verifyPasswordResetToken(String email, String token);
	public List<Permission> getAllPermissions();
	public List<Role> getAllRoles();
	public Role getRoleByName(String roleName);
	public Role createRole(Role role);
	public Role updateRole(Role role);
	public Role getRoleById(Integer id);
	public User getUserById(Integer id);
	public List<User> getAllUsers();
	public User createUser(User user);
	public User updateUser(User user);
	
	
	
}
