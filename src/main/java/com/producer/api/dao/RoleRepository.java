package com.producer.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.producer.api.entity.Role;
import com.producer.api.entity.roles;


@Repository
public interface RoleRepository  extends JpaRepository<Role, Integer>{

		Role findByName(roles name);

	}



