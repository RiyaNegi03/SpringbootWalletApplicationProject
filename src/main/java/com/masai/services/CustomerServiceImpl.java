package com.masai.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.NotFoundException;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;
import com.masai.model.Wallet;
import com.masai.repository.CustomerDao;
import com.masai.util.GetCurrentLoginUserSessionDetailsIntr;

@Service
public class CustomerServiceImpl implements CustomerServiceIntr {

	@Autowired
	private CustomerDao customerDao;

	
	@Autowired
	private GetCurrentLoginUserSessionDetailsIntr getCurrentLoginUser;

	

	@Override
	public Customer createCustomer(Customer customer) {

		Optional<Customer> opt = customerDao.findByMobileNumber(customer.getMobileNumber());

		if (opt.isPresent()) {
			throw new NotFoundException("User already exist with this mobile number..");
		}

		Wallet wallet = new Wallet();
		
		wallet.setBalance(0);
		wallet.setCustomer(customer);

		customer.setWallet(wallet);

		return customerDao.save(customer);

	}

	@Override
	public Customer updateCustomer(Customer customer, String key) {

		Customer customer2 = getCurrentLoginUser.getCurrentCustomer(key);

		if (customer2 == null) {
			throw new NotFoundException("User not found need to login..");
		}

		customer2.setMobileNumber(customer.getMobileNumber());
		customer2.setName(customer.getName());
		customer2.setPassword(customer.getPassword());
		return customerDao.save(customer2);

	}

	@Override
	public Customer deleteCustomer(String key) {

		Customer customer = getCurrentLoginUser.getCurrentCustomer(key);
		CurrentUserSession currentUserSession = getCurrentLoginUser.getCurrentUserSession(key);
		customerDao.delete(customer);
		return customer;
	}

	@Override
	public Customer getCustomerDetails(String key) {

		Customer customer = getCurrentLoginUser.getCurrentCustomer(key);
//		System.out.println(customer);
		return customer;
	}

	@Override
	public List<Customer> getCustomerList() throws NotFoundException {
		// TODO Auto-generated method stub

		List<Customer> customers = customerDao.findAll();

		if (customers.size() <= 0) {
			throw new NotFoundException("Customer not found..");
		}
		return customerDao.findAll();

	}

}