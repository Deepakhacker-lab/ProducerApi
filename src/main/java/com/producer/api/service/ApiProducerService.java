package com.producer.api.service;
import java.util.List;

import com.producer.api.entity.ExternalJson;
import com.producer.api.entity.ResponseJsonInt;

public interface ApiProducerService {
	
	public List<ResponseJsonInt> findAll();
	
	public ResponseJsonInt findOneUser(long id);

	public ResponseJsonInt UpdateOneUser(int id, ExternalJson user);

}
