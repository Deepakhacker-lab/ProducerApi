package com.producer.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.producer.api.entity.ExternalJson;
import com.producer.api.entity.ResponseJsonInt;
import com.producer.api.service.ApiProducerService;

@RestController
public class ProducerApiController {
	
	private ApiProducerService apiProducerService; 
	
	@Autowired
	public ProducerApiController(ApiProducerService apiProducerService) {
		this.apiProducerService=apiProducerService;
	}
	
	@GetMapping("/Users")
	public List<ResponseJsonInt> findAll(){
		
		List<ResponseJsonInt> theEmployees =apiProducerService.findAll();
		
			return theEmployees;
	}
	
	@GetMapping("/User/{id}")
	public ResponseJsonInt findOne(@PathVariable long id) {
		
		ResponseJsonInt user = apiProducerService.findOneUser(id);
		
		
		return user;
		
		
	}
	
	@PutMapping("/User/{id}/{AddressIp}")
	@PreAuthorize("hasAuthority('Developer')")
	public ResponseJsonInt UpdateUser (@PathVariable int id, @PathVariable String AddressIp){
		
		String obj = AddressIp;
		String url = "http://api.ipstack.com/"+obj+"?access_key=626053333c5203dcd83c391d63485afb";

	    ResponseEntity<ExternalJson> ResponseEntity = new RestTemplate().getForEntity(url, ExternalJson.class);
	    
	    ExternalJson response= new ExternalJson(ResponseEntity.getBody().getCity(),ResponseEntity.getBody().getRegion_name(),
	    		ResponseEntity.getBody().getCountry_name(),
	    		ResponseEntity.getBody().getZip());
		
	    ResponseJsonInt updateUser = apiProducerService.UpdateOneUser(id, response);
				
				return updateUser;
	}

}
