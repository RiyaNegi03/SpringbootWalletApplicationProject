package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.BillPayment;

@Repository
public interface BillPayementDao extends JpaRepository<BillPayment, Integer> {

}
