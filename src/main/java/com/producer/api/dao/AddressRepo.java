package com.producer.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.producer.api.entity.Address;
import com.producer.api.entity.User;

public interface AddressRepo extends JpaRepository<Address, Integer>{

	void save(User newUser);


}
