package com.masai.services;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import com.masai.exceptions.NotFoundException;
import com.masai.model.Transaction;
import com.masai.model.Wallet; 
import com.masai.repository.TransactionDao;
import com.masai.repository.WalletDao;
import com.masai.util.GetCurrentLoginUserSessionDetailsIntr;


@Service
public class TransactionServicesImpl implements TransactionSevices {

	@Autowired
	WalletDao wDao;
	@Autowired
	TransactionDao transactionDao;
	@Autowired
    private GetCurrentLoginUserSessionDetailsIntr getCurrentLoginUser;
	
	@Override
	public List<Transaction> viewAllTransaction(String key) {
		Wallet wallet = getCurrentLoginUser.getCurrentUserWallet(key);
		
		List <Transaction>  transactionList = wallet.getTransactions();
		
		if(transactionList.size() == 0) {
			throw new NotFoundException("No Transaction found");
		}
		
		return transactionList;
	}

	
	

	

	
	

}
