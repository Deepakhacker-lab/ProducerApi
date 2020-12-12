package com.producer.api.service;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.producer.api.Exception.UserAlreadyExistException;
import com.producer.api.dao.AddressRepo;
import com.producer.api.dao.DepartmentRepo;
import com.producer.api.dao.ProducerDao;
import com.producer.api.dao.RoleRepository;
import com.producer.api.entity.Address;
import com.producer.api.entity.Department;
import com.producer.api.entity.ExternalAddress;
import com.producer.api.entity.JsonUser;
import com.producer.api.entity.Role;
import com.producer.api.entity.User;
import com.producer.api.entity.roles;


@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private ProducerDao userDao;

	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Autowired
	private RoleRepository rolerepo;
	
	@Autowired
	private DepartmentRepo departmentRepo;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private AddressRepo addressRepo;


	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public void authenticate(String email, String password) throws Exception {
		try {
			User user = userDao.findByEmail(email);
			if(user!=null) {
				Role role =  rolerepo.findById(user.getRole()).orElse(new Role(roles.Employee));
		        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		            authorities.add(new SimpleGrantedAuthority(role.getname().toString()));
		            authorities.stream().filter(x->x!=null).forEach(x->System.err.println(x));
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), password,authorities));
			}
			else {
				
				logger.error("User not found in database");
				throw new UsernameNotFoundException("User not found");
			}
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		User user = userDao.findByUsername(username);
		System.err.println("Check"+user.getEmail());
		
			Role role =  rolerepo.findById(user.getRole()).orElse(new Role(roles.Employee));
	        

	        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
	            authorities.add(new SimpleGrantedAuthority(role.getname().toString()));
	            logger.info("User  found in database");
			return new org.springframework.security.core.userdetails.User(user.getUsername(),
					user.getPassword(),
					true,
					true,
					true,
					true,
					authorities
					);
}

	public User save(JsonUser user) {
		
		String email= user.getEmail();
		User users=userDao.findByEmail(email);
		if(users!=null) {
			 throw new UserAlreadyExistException("Email already exist: " + email);
		}else {
		
		User newUser = new User();
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setEmail(user.getEmail());
		Role role= rolerepo.findByName(user.getRole());
		newUser.setRole(role.getId());
		newUser.setUsername();
		Department dept = departmentRepo.findByName(user.getDepartment());
		newUser.setDepartment(dept.getId());
		
		for(int i=0; i<user.getAddress().size();i++) {
				ExternalAddress oldEa = user.getAddress().get(i).getExternalAddress();
				ExternalAddress newEa = new ExternalAddress(oldEa.getCity(),oldEa.getState(),oldEa.getCountry(),oldEa.getZipCode());
				
				Address add= new Address(user.getAddress().get(i).getAddressLine1(),
						user.getAddress().get(i).getAddressLine2(),user.getAddress().get(i).getAddressType(), newEa, newUser);
				
				addressRepo.save(add);
			}
			User saved =userDao.save(newUser);
		return saved;
		}
	
	}

	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}
}