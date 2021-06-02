package com.ecordi.alkemy.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecordi.alkemy.entities.Role;




@Repository
public interface RoleRepository extends CrudRepository<Role, Long>{

}
