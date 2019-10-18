
package com.soft.stock.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soft.stock.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

	Category getByName(String name);

//	Category findOne(Integer id);

}
