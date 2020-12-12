package com.producer.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.producer.api.Exception.UnAuthorizedException;
import com.producer.api.Exception.UserNotFoundException;
import com.producer.api.dao.ProducerDao;
import com.producer.api.dao.RoleRepository;
import com.producer.api.entity.Address;
import com.producer.api.entity.ExternalAddress;
import com.producer.api.entity.ExternalJson;
import com.producer.api.entity.ResponseJsonInt;
import com.producer.api.entity.Role;
import com.producer.api.entity.User;
import com.producer.api.entity.roles;

@Service
public class ApiProducerServiceImpl implements ApiProducerService{
	
	@Autowired
	private ProducerDao producerDao;
	
	@Autowired
	private RoleRepository roleRepo;

	@Override
	public List<ResponseJsonInt> findAll() {
		
		List<ResponseJsonInt> users= producerDao.findAllfromQuery();
		
		if(users.isEmpty())
			throw new UserNotFoundException("No Data");
		else 
			return users;
		
	}

	@Override
	public ResponseJsonInt findOneUser(long id) {
		
		ResponseJsonInt user= producerDao.findbyIdfromQuery(id);
		
		if(user==null)
			throw new UserNotFoundException("User not found");
		else	
		return user;
		
	}

	@Override
	public ResponseJsonInt UpdateOneUser(int id, ExternalJson address) {
		
		User updateUser= producerDao.findById(id).orElseThrow(()->new UserNotFoundException("User not found"));
		
		Role role =roleRepo.findById(updateUser.getRole()).orElse(new Role(roles.Employee));
		
		if(role.getname().toString().equalsIgnoreCase("Developer")) {
		List<Address> address1= updateUser.getAddress();
		for(Address add:address1 ) {
			if(add.getAddressType().equals("Home")) {
				ExternalAddress Ext = new ExternalAddress(address.getCity(), address.getRegion_name(),address.getCountry_name(),address.getZip());
			add.setExternalAddress(Ext);
			}
		}
		producerDao.save(updateUser);
		ResponseJsonInt user= producerDao.findbyIdfromQuery(updateUser.getId());
		return user;
		}
		else
			throw new UnAuthorizedException("Unauthorized");
	}
		
	}
	
	
	
	


