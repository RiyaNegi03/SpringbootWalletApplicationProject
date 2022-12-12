package com.masai.util;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;
import com.masai.repository.CustomerDao;
import com.masai.repository.SessionDao;

@Service
public class getCurrentLoginUserSessionDetails {

	
	@Autowired
	private SessionDao sessionDao;

	@Autowired
	private CustomerDao customerDao;
	
	public CurrentUserSession getCurrentUserSession(String key) {
		Optional<CurrentUserSession> optional = sessionDao.findByUuid(key);
		
		return optional.get();
	}
	
	public Integer getCurrentUserSessionId(String key) {
		Optional<CurrentUserSession> optional = sessionDao.findByUuid(key);
		
		return optional.get().getId();
	}
	
	public Customer getCurrentCustomer(String key) {
		
		Optional<CurrentUserSession> optional = sessionDao.findByUuid(key);
		
		Integer customerId = optional.get().getCustomerId();
		
		return  customerDao.getById(customerId);
	}
}