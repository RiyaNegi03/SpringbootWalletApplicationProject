package com.masai.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.InsufficientAmountException;
import com.masai.exceptions.NotFoundException;
import com.masai.model.BankAccount;
import com.masai.model.Beneficiary;
import com.masai.model.Customer;
import com.masai.model.Transaction;
import com.masai.model.Wallet;
import com.masai.repository.BankAccountDao;
import com.masai.repository.CustomerDao;
import com.masai.repository.TransactionDao;
import com.masai.repository.WalletDao;
import com.masai.util.GetCurrentLoginUserSessionDetailsIntr;



@Service
public class WalletServiceImpl implements WalletServices{
     
	@Autowired
	WalletDao wDao;
	@Autowired
	CustomerDao cDao;
	@Autowired
	BankAccountDao bDao;
	@Autowired
	TransactionDao trDao;
	@Autowired
    private GetCurrentLoginUserSessionDetailsIntr getCurrentLoginUser;

	
	@Transactional
	@Override
	public String FundTransfer(String key, String targetMobileNo, double amount)
	 {
		
		Wallet wallet = getCurrentLoginUser.getCurrentUserWallet(key);
		
	List<Beneficiary> bList = wallet.getBenificiaryList();
	
	Beneficiary bf = null;
	
   for(Beneficiary b:bList) {
	   if(b.getMobileNumber().equals(targetMobileNo)) {
		   bf = b;
	   }
    }
   
   if(bf == null)
	 throw new NotFoundException("Benificiary not found with this mobile number");
   
   if(wallet.getBalance() <= amount) {
	   throw new InsufficientAmountException("Insufficient balance in your wallet");
   }
   wallet.setBalance(wallet.getBalance() - amount); // deducting money from wallet source 
   
   Transaction tr = new Transaction();
   tr.setTransactionDate(LocalDate.now());
   tr.setAmount(amount);
   tr.setDescription("Transfer to " + targetMobileNo);
   tr.setTransactionType("Fund Transfer");
   wallet.getTransactions().add(tr);
   tr.setWallet(wallet);
   trDao.save(tr);
    wDao.save(wallet);
    
   return "Fund transfer to " + bf.getName() +"\n"+"Your remaining wallet balance is : "+wallet.getBalance();
	
	}

	@Override
	public double showWalletBalance(String key) throws NotFoundException {
		// TODO Auto-generated method stub
		
	 double balance =	getCurrentLoginUser.getCurrentUserWallet(key).getBalance();
		
		
		return balance;
	}

	
	
//	wallet to bank
	@Transactional
	@Override
	public BankAccount depositAmount(long accountNumber,double amount, String key) throws InsufficientAmountException {
		
		
		Wallet wallet = getCurrentLoginUser.getCurrentUserWallet(key);
		
	  List<BankAccount> banks = wallet.getBankaccounts(); 
	  
	  if(banks.size() <= 0) {
		  throw new NotFoundException("Bank not found..");
	  }
	  
	  
	  BankAccount b=null;
	  
	  for(BankAccount bank:banks) {
		  if(bank.getAccountNo() == accountNumber) {
			 b=bank;
			  
		  }
	  
	  }
	 
	  if(b== null) {
		  throw new NotFoundException("Bank account not found..");
	  }
	  
	  Transaction tr = new Transaction();
	   tr.setTransactionDate(LocalDate.now());
	   tr.setAmount(amount);
	   tr.setDescription("Money added to bank account..");
	   tr.setTransactionType("Cash deposit");
	   wallet.getTransactions().add(tr);
	   tr.setWallet(wallet);
	   trDao.save(tr);
	  
	  b.setBalance(b.getBalance() + amount);
	   bDao.save(b);
	   return b;
		
		
	}

	//add money with the help of bank.
	
	@Override
	@Transactional
	public String addMoneyToWallet(long bankAccountNumber,double amount, String key) throws InsufficientAmountException {
		
	   Wallet wallet = getCurrentLoginUser.getCurrentUserWallet(key);
	   
	  List<BankAccount> banks = wallet.getBankaccounts();
	 
	  for(BankAccount bank:banks) {
		  if(bank.getAccountNo() == bankAccountNumber) {
			  if(bank.getBalance() >= amount) {
				  //tr
				  Transaction tr = new Transaction();
				   tr.setTransactionDate(LocalDate.now());
				   tr.setAmount(amount);
				   tr.setDescription("Money added in wallet..");
				   tr.setTransactionType("Add Money");
				   wallet.getTransactions().add(tr);
				   tr.setWallet(wallet);
				   trDao.save(tr);
				  //tr
				  bank.setBalance(bank.getBalance() - amount);
				  wallet.setBalance(wallet.getBalance() + amount);
				  bDao.save(bank);
				  wDao.save(wallet);
				  return "Amount : " + amount +" added to wallet";
			  }else {
				  throw new InsufficientAmountException("Insufficient amount in bank account..");
			  }
			
		  }
	  }
	  throw new NotFoundException("Bank not found..");
	  
	
		
	}

	@Transactional
	@Override
	public String transferToCustomerWallet(String targetMobileNumber,double amount, String key) {
		
		
	 Customer targetCustomer =	cDao.findByMobileNumber(targetMobileNumber).get();
	 
	 if(targetCustomer == null) {
		 throw new NotFoundException("Customer not found..");
	 }
	 
	 Wallet tWallet = targetCustomer.getWallet();
	 
	  Wallet cWallet = getCurrentLoginUser.getCurrentUserWallet(key);
		
	  if(cWallet.getBalance() < amount) {
		  throw new InsufficientAmountException("Insufficient amount in wallet");
	  }
	    cWallet.setBalance(cWallet.getBalance() - amount);
		tWallet.setBalance(tWallet.getBalance() + amount);
		 //tr
		  Transaction tr = new Transaction();
		   tr.setTransactionDate(LocalDate.now());
		   tr.setAmount(amount);
		   tr.setDescription("Amount transfer to customer..");
		   tr.setTransactionType("Fund Transfer");
		   cWallet.getTransactions().add(tr);
		   tr.setWallet(cWallet);
		   trDao.save(tr);
		   
		wDao.save(cWallet);
		wDao.save(tWallet);
		
	
		
		return amount + "transfered to " + tWallet.getCustomer().getName();
	}

	

	

	
}