package com.masai.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.BankAccount;
import com.masai.model.Beneficiary;
import com.masai.model.BillPayment;
import com.masai.model.Customer;
import com.masai.model.LogDetails;
import com.masai.model.Transaction;
import com.masai.services.AccountServicesIntr;
//import com.masai.services.AccountServicesIntr;
import com.masai.services.BeneficiaryIntr;
import com.masai.services.BillPaymentImpl;
import com.masai.services.CustomerServiceIntr;
import com.masai.services.TransactionSevices;
//import com.masai.services.WalletServices;
import com.masai.services.WalletServices;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class GetDetailsController {
	
	@Autowired
	private CustomerServiceIntr customerServiceImpl;

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
	
	@Autowired
	private com.masai.services.LogDetails logDetails;
	
	// to get all log details
	
	@GetMapping("/logdetails")
	public ResponseEntity<List<LogDetails> > getListOfLogDetails(){
	  List<LogDetails>	list = logDetails.logDetailsList();
	  
	  return new ResponseEntity<>(list,HttpStatus.ACCEPTED);
	}
	
	// To get details of current user by passing its login key
	@GetMapping(value = "/customer")
	public ResponseEntity<Customer> getCustomerDetails(@RequestParam(required = false) String key) {
		Customer fetched_customer = customerServiceImpl.getCustomerDetails(key);

		return new ResponseEntity<Customer>(fetched_customer, HttpStatus.ACCEPTED);
	}
	
	// list of banks
	
	@GetMapping("/accounts")
	public ResponseEntity<List<BankAccount>> listAccountHandler(@RequestParam String key){
		List<BankAccount> b = accountServicesIntr.getAllBankAccounts(key);
		return new ResponseEntity<List<BankAccount>>(b,HttpStatus.ACCEPTED);
	}
	
	
	// list of bills
	@GetMapping("/billlist")
	public ResponseEntity<List<BillPayment>> getallbills(@RequestParam String key){
		List<BillPayment> list= billservice.billList(key);
		return new ResponseEntity<List<BillPayment>>(list, HttpStatus.ACCEPTED);
	}
	

	//list of transaction
	@GetMapping("/transactionlist")
	public List<Transaction> getTransactionList(@RequestParam String key) {
		
		return trServices.viewAllTransaction(key);
	}
	
	
	// customer list
	@GetMapping(value = "/customers")
	public ResponseEntity<List<Customer>> getAllCustomersDetailsHandler() {
		List<Customer> list = customerServiceImpl.getCustomerList();
		
		return new ResponseEntity<List<Customer>>(list, HttpStatus.ACCEPTED);
	}
	

	
	//beneficiary list
	
	@GetMapping("/getBenificiaries")
	public ResponseEntity< List<Beneficiary>> beneficiaryListHandler(@RequestParam String key) {
		
		 List<Beneficiary> list = bService.viewAllBenificiary(key);
		 return new ResponseEntity< List<Beneficiary>>(list, HttpStatus.ACCEPTED);
	}
	
	// show wallet balance
	@GetMapping("/walletbalance")
	public ResponseEntity<Double> showWalletBalanceHandler(@RequestParam String key) {
		Double d= wService.showWalletBalance(key);
		return new ResponseEntity<Double>(d,HttpStatus.ACCEPTED);
	}

}
