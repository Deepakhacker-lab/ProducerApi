package com.producer.api.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.producer.api.entity.ResponseJsonInt;
import com.producer.api.entity.User;

public interface ProducerDao extends JpaRepository<User, Integer> {

	User findByUsername(String username);

	User findByEmail(String email);
	
	
	@Query(
			  value = "SELECT \r\n" + 
			  		"    u.id,\r\n" + 
			  		"    CONCAT(u.first_name, ' ', u.last_name) as name,\r\n" + 
			  		"    ad.address_type as address,\r\n" + 
			  		"    ad.city,\r\n" + 
			  		"    ad.state,\r\n" + 
			  		"    ad.country,\r\n" + 
			  		"    r.name as role,\r\n" + 
			  		"    d.department_name as department\r\n" + 
			  		"FROM\r\n" + 
			  		"    producerapi.user u,\r\n" + 
			  		"    producerapi.address ad,\r\n" + 
			  		"    producerapi.role r,\r\n" + 
			  		"    producerapi.department d\r\n" + 
			  		"WHERE\r\n" + 
			  		"    u.id = ad.user_id\r\n" + 
			  		"        AND u.department_id = d.id\r\n" + 
			  		"        AND u.role_id = r.id;", 
			  nativeQuery = true)
	List<ResponseJsonInt> findAllfromQuery();
	
	
	@Query(
			  value = "SELECT \r\n" + 
			  		"    u.id,\r\n" + 
			  		"    CONCAT(u.first_name, ' ', u.last_name) as name,\r\n" + 
			  		"    ad.address_type as address,\r\n" + 
			  		"    ad.city,\r\n" + 
			  		"    ad.state,\r\n" + 
			  		"    ad.country,\r\n" + 
			  		"    r.name as role,\r\n" + 
			  		"    d.department_name as department\r\n" + 
			  		"FROM\r\n" + 
			  		"    producerapi.user u,\r\n" + 
			  		"    producerapi.address ad,\r\n" + 
			  		"    producerapi.role r,\r\n" + 
			  		"    producerapi.department d\r\n" + 
			  		"WHERE\r\n" + 
			  		"    u.id = ad.user_id\r\n" + 
			  		"        AND u.department_id = d.id\r\n" + 
			  		"        AND u.role_id = r.id\r\n" + 
			  		"		and ad.address_type='Home'\r\n"+
			  		"		and u.id=:id", 
			  nativeQuery = true)
	ResponseJsonInt findbyIdfromQuery(long id);

}
