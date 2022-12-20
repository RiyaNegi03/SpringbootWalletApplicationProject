package com.masai.repository;

import java.util.Optional;

//import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Beneficiary;

public interface BeneficiaryDao extends JpaRepository<Beneficiary, Integer> {
	
	

	public Optional<Beneficiary> findByMobileNumber(String mobileNumber);

}
