package com.masai.services;


import java.util.List;

import org.springframework.stereotype.Service;

import com.masai.model.BillPayment;
@Service
public interface BillPaymentIntr {

	public BillPayment addBill(String key, BillPayment bill);
	
	public List<BillPayment> billList(String Key);
}
