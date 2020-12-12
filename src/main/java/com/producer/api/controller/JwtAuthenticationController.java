package com.producer.api.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.producer.api.dao.ProducerDao;
import com.producer.api.entity.JsonUser;
import com.producer.api.entity.User;
import com.producer.api.jwt.JwtTokenUtil;
import com.producer.api.jwt.resources.JwtTokenRequest;
import com.producer.api.jwt.resources.JwtTokenResponse;
import com.producer.api.service.JwtUserDetailsService;




@RestController
public class JwtAuthenticationController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass()); 

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@Autowired
	private ProducerDao producerDao;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtTokenRequest authenticationRequest) throws Exception {

		userDetailsService.authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
		
		User username = producerDao.findByEmail(authenticationRequest.getEmail());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(username.getUsername());
		
		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtTokenResponse(token, userDetails.getAuthorities()));
		
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<String> saveUser( @RequestBody JsonUser user) {
		
		User Created =userDetailsService.save(user);
				
		logger.debug(Created.getId()+"User created successfully");
		return ResponseEntity.ok().body("Successfully Registered");
		
//		return ResponseEntity.ok(userDetailsService.save(user));
	}

	
	@DeleteMapping(("/login/{id}"))
	public ResponseEntity<?>  Delete(@PathVariable Long id){
		
		if(userDetailsService.deleteById(id)) {
			
			logger.info("USer deleted "+ id);
			return new ResponseEntity<>("Successfully deleted ", HttpStatus.ACCEPTED);
		}
		else {
			return new ResponseEntity<>("Failed to delete ", HttpStatus.NOT_ACCEPTABLE);
		}
		
	}
	
}

		
