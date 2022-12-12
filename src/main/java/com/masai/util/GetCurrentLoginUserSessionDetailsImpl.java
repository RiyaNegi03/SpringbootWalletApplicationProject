package com.masai.util;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.NotFoundException;

import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;
import com.masai.model.Wallet;
import com.masai.repository.CustomerDao;
import com.masai.repository.SessionDao;

@Service
public class GetCurrentLoginUserSessionDetailsImpl implements GetCurrentLoginUserSessionDetailsIntr{
   
	@Autowired
	private SessionDao sessionDao;
	
	@Autowired
	private CustomerDao customerDao;

	public CurrentUserSession getCurrentUserSession(String key) {
		Optional<CurrentUserSession> optional = sessionDao.findByUuid(key);
		
		if(!optional.isPresent()) {
			throw new NotFoundException("Unauthorized Key");
		}
		
		return optional.get();
	}
	

	
	public Customer getCurrentCustomer(String key) {
		Optional<CurrentUserSession> optional = sessionDao.findByUuid(key);
		
		if(!optional.isPresent()) {
			throw new NotFoundException("Unauthorized Key");
		}
		
		Integer customerId = optional.get().getCustomerId();
		
		return  customerDao.getById(customerId);
	}
	
	public Wallet getCurrentUserWallet(String key) {
		Optional<CurrentUserSession> optional = sessionDao.findByUuid(key);
		
		if(!optional.isPresent()) {
			throw new NotFoundException("Unauthorized Key");
		}
		
		Integer customerId = optional.get().getCustomerId();
		Customer customer = customerDao.getById(customerId);
		
		Wallet wallet = customer.getWallet();
		
		return wallet;
	}
	

	
	  
	
	

}
