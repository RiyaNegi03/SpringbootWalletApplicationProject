package com.masai.services;

import com.masai.model.LoginDTO;

public interface CustomerLogIntr {
	
	public String logIntoAccount(LoginDTO customerDTO);
	
	public String logOutFromAccount(String key);

}