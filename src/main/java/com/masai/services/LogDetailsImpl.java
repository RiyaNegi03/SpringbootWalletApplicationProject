package com.masai.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.NotFoundException;
import com.masai.model.Customer;
import com.masai.repository.LogDetailsDao;
import com.masai.util.GetCurrentLoginUserSessionDetailsIntr;

@Service
public class LogDetailsImpl implements LogDetails{
   
	@Autowired
	private GetCurrentLoginUserSessionDetailsIntr getCurrentUserSessionDetails;
	
	@Autowired
   private	LogDetailsDao logDetails;

	
	@Override
	public List<com.masai.model.LogDetails> logDetailsList() {
		
	List<com.masai.model.LogDetails> list = logDetails.findAll();
	
	System.out.println(list.size());
    
	if(list.size()==0 ) {
		throw new NotFoundException("Log details not found..");
	}
	
	  return list;
	
	}

}