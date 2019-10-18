
package com.soft.stock.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.soft.stock.common.StockException;
import com.soft.stock.entities.Permission;
import com.soft.stock.entities.Role;
import com.soft.stock.entities.User;
import com.soft.stock.repositories.PermissionRepository;
import com.soft.stock.repositories.RoleRepository;
import com.soft.stock.repositories.UserRepository;

@Service
@Transactional
public class SecurityServiceImpl implements SecurityService{
	@Autowired
	UserRepository userRepository;
	@Autowired
	PermissionRepository permissionRepository;
	@Autowired
	RoleRepository roleRepository;

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public String resetPassword(String email) {
		User user = findUserByEmail(email);
		if (user == null) {
			throw new StockException("Invalid email address");
		}
		String uuid = UUID.randomUUID().toString();
		user.setPasswordResetToken(uuid);
		return uuid;
	}

	@Override
	public void updatePassword(String email, String token, String password) {
		User user = findUserByEmail(email);
		if (user == null) {
			throw new StockException("Invalid email address");
		}
		if (!StringUtils.hasText(token) || !token.equals(user.getPasswordResetToken())) {
			throw new StockException("Invalid password reset token");
		}
		user.setPassword(password);
		user.setPasswordResetToken(null);
	}

	@Override
	public boolean verifyPasswordResetToken(String email, String token) {
		User user = findUserByEmail(email);
		if (user == null) {
			throw new StockException("Invalid email address");
		}
		if (!StringUtils.hasText(token) || !token.equals(user.getPasswordResetToken())) {
			return false;
		}
		return true;
	}

	@Override
	public List<Permission> getAllPermissions() {
		return permissionRepository.findAll();
	}

	@Override
	public List<Role> getAllRoles() {
		return roleRepository.findAll();
	}

	@Override
	public Role getRoleByName(String roleName) {
		return roleRepository.findByName(roleName);
	}

	@Override
	public Role createRole(Role role) {
		Role roleByName = getRoleByName(role.getName());
		if (roleByName != null) {
			throw new StockException("Role " + role.getName() + " already exist");
		}
		List<Permission> persistedPermissions = new ArrayList<>();
		List<Permission> permissions = role.getPermissions();
		if (permissions != null) {
			for (Permission permission : permissions) {
				if (permission.getId() != null) {
					persistedPermissions.add(permissionRepository.getOne(permission.getId()));
				}
			}
		}

		role.setPermissions(persistedPermissions);
		return roleRepository.save(role);
	}

	@Override
	public Role updateRole(Role role) {
		Role persistedRole = getRoleById(role.getId());
		if (persistedRole == null) {
			throw new StockException("Role " + role.getId() + " doesn't exist");
		}
		persistedRole.setDescription(role.getDescription());
		List<Permission> updatedPermissions = new ArrayList<>();
		List<Permission> permissions = role.getPermissions();
		if (permissions != null) {
			for (Permission permission : permissions) {
				if (permission.getId() != null) {
					updatedPermissions.add(permissionRepository.getOne(permission.getId()));
				}
			}
		}
		persistedRole.setPermissions(updatedPermissions);
		return roleRepository.save(persistedRole);
	}

	@Override
	public Role getRoleById(Integer id) {
		return roleRepository.getOne(id);
	}

	@Override
	public User getUserById(Integer id) {
		return userRepository.getOne(id);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User createUser(User user) {
		User userByEmail = findUserByEmail(user.getEmail());
		if (userByEmail != null) {
			throw new StockException("Email " + user.getEmail() + " already in use");
		}
		List<Role> persistedRoles = new ArrayList<>();
		List<Role> roles = user.getRoles();
		if (roles != null) {
			for (Role role : roles) {
				if (role.getId() != null) {
					persistedRoles.add(roleRepository.getOne(role.getId()));
				}
			}
		}
		user.setRoles(persistedRoles);

		return userRepository.save(user);
	}

	@Override
	public User updateUser(User user) {
		User persistedUser = getUserById(user.getId());
		if (persistedUser == null) {
			throw new StockException("User " + user.getId() + " doesn't exist");
		}

		List<Role> updatedRoles = new ArrayList<>();
		List<Role> roles = user.getRoles();
		if (roles != null) {
			for (Role role : roles) {
				if (role.getId() != null) {
					updatedRoles.add(roleRepository.getOne(role.getId()));
				}
			}
		}
		persistedUser.setRoles(updatedRoles);
		return userRepository.save(persistedUser);
	}

}
