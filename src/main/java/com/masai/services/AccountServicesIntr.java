package com.masai.services;

import java.util.List;

import com.masai.model.BankAccount;

public interface AccountServicesIntr {
	
	
	public BankAccount addAccount(BankAccount bank, String key);
	
	public List<BankAccount> getAllBankAccounts(String key);
	
	public BankAccount removeBankAccount(String key,long accountNo) ;
	
	public BankAccount updateBankAccount(String key,BankAccount bank) ;
	
	
}