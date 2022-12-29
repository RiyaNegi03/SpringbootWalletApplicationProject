package com.masai.controllers;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.BankAccount;
import com.masai.model.BillPayment;
import com.masai.services.AccountServicesIntr;
import com.masai.services.BeneficiaryIntr;

import com.masai.services.BillPaymentImpl;
import com.masai.services.TransactionSevices;
import com.masai.services.WalletServices;



@RestController
public class TransactionsController {
	
	
	    @Autowired
	   AccountServicesIntr accountServicesIntr;
		
		@Autowired
		private WalletServices wService;
		
		@Autowired
		private BeneficiaryIntr bService;
		
		@Autowired
		private TransactionSevices trServices;
		
		@Autowired
		private BillPaymentImpl billservice;
		
		
		// deposit
		@PutMapping("/deposit/{ac}/{a}")
		public ResponseEntity<BankAccount> depositInBankHandler(@PathVariable("ac") long accountNumber, @PathVariable("a") double amount, @RequestParam String key) {
			BankAccount b= wService.depositAmount(accountNumber, amount, key);
			return new ResponseEntity<BankAccount>(b,HttpStatus.ACCEPTED);
		}
		
		// add money to wallet
		@PostMapping("/addmoney/{accountNumber}/{amount}")
		public ResponseEntity<String> addMoneyToWallet(@PathVariable long accountNumber, @PathVariable double amount, @RequestParam String key) {
			String s = wService.addMoneyToWallet( accountNumber, amount,key);
			return new  ResponseEntity<String>(s,HttpStatus.ACCEPTED);
			
		}
		
		// fundTransfer after adding beneficiary
		@PutMapping("/fundtransfer")
		public ResponseEntity<String> fundTransferHandler(@RequestParam String key, @RequestParam String mobileNumber, @RequestParam double amount) {
			
			String s= wService.FundTransfer(key, mobileNumber, amount);
			return new  ResponseEntity<String>(s,HttpStatus.ACCEPTED);
		}
		
		//transfer amount one customer to another customer
		
		@PutMapping("/transfertocustomer/{m}/{a}")
		public  ResponseEntity<String> customerToCustomerHandler(@PathVariable("m") String mobileNumber , @PathVariable("a") double amount ,@RequestParam String key ) {
			
			String s= wService.transferToCustomerWallet(mobileNumber, amount, key);
			return new  ResponseEntity<String>(s,HttpStatus.ACCEPTED);
		}
		
		
		
		//bill payment
		
		@PostMapping("/billpay")
		public ResponseEntity<BillPayment> billpayment(@Valid @RequestParam String key ,@RequestBody BillPayment bill) {
			BillPayment b =  billservice.addBill(key, bill);
			return new ResponseEntity<BillPayment>(b,HttpStatus.ACCEPTED);
		}
		
		
		
		
		

}