
package com.soft.stock.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soft.stock.entities.Permission;


@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer>
{

	// Permission findOne(Integer id);

}
