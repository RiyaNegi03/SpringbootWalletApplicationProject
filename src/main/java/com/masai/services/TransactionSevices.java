package com.masai.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.masai.model.Transaction;
import com.masai.model.Wallet;
import com.masai.repository.BankAccountDao;
import com.masai.repository.CustomerDao;
import com.masai.repository.TransactionDao;
import com.masai.repository.WalletDao;
import com.masai.util.GetCurrentLoginUserSessionDetailsIntr;

@Repository
public interface TransactionSevices {
	
	
	public List<Transaction> viewAllTransaction(String key);
	

	
	
}
