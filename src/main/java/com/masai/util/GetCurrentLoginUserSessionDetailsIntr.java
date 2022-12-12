package com.masai.util;



import java.util.List;

import com.masai.exceptions.NotFoundException;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;
import com.masai.model.Wallet;

public interface GetCurrentLoginUserSessionDetailsIntr {
	
   public CurrentUserSession getCurrentUserSession(String key) throws NotFoundException;
	

	
	public Customer getCurrentCustomer(String key) throws NotFoundException;
	
	public Wallet getCurrentUserWallet(String key) throws NotFoundException;
	
	
}