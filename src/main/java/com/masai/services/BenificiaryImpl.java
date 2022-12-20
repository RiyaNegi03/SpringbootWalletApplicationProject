package com.masai.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.masai.exceptions.NotFoundException;
import com.masai.exceptions.UserAlreadyExistWithMobileNumber;
import com.masai.model.Beneficiary;
import com.masai.model.Wallet;
import com.masai.repository.BankAccountDao;
import com.masai.repository.BeneficiaryDao;
import com.masai.repository.CustomerDao;
import com.masai.repository.WalletDao;
import com.masai.util.GetCurrentLoginUserSessionDetailsIntr;

@Component
public class BenificiaryImpl implements BeneficiaryIntr {

	
	@Autowired
	WalletDao wDao;
	@Autowired
	CustomerDao cDao;
	@Autowired
	BankAccountDao bDao;
	
	@Autowired
	BeneficiaryDao bfDao;

	
	@Autowired
    private GetCurrentLoginUserSessionDetailsIntr getCurrentLoginUser;
  
	@Override
	public Beneficiary addBenificiary(Beneficiary benificary, String key) {
		
		Optional<Beneficiary> optBenificiary = bfDao.findByMobileNumber(benificary.getMobileNumber());
		if(optBenificiary.isPresent()) {
			throw new UserAlreadyExistWithMobileNumber("Benificiary already added..");
		}
		
		Wallet wallet = getCurrentLoginUser.getCurrentUserWallet(key);
		
		wallet.getBenificiaryList().add(benificary);
		benificary.setWallet(wallet);
		bfDao.save(benificary);
		
		
		// TODO Auto-generated method stub
		return benificary;
	}

	@Override
	public List<Beneficiary> viewAllBenificiary(String key) {
		
		Wallet wallet = getCurrentLoginUser.getCurrentUserWallet(key);
		
		List<Beneficiary> bfList = wallet.getBenificiaryList();
		if( bfList.size()<=0 ) 
			throw new NotFoundException("Benificiaries details not found..");
		       return bfList;
	}
}