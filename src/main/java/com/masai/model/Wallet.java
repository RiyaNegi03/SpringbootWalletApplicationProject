package com.masai.model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Wallet {
	
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer walletid;
  private double balance;
  
 @OneToOne(cascade = CascadeType.ALL)
// @JsonIgnore
// @JoinColumn(name = "customer_id",referencedColumnName = "cid")
 private Customer customer;

 @OneToMany(cascade = CascadeType.ALL)
 private List<BankAccount> bankaccounts = new ArrayList<BankAccount>();
 
 @OneToMany(cascade = CascadeType.ALL,mappedBy = "wallet")
 private List<Beneficiary> benificiaryList = new ArrayList<>();
 
 @OneToMany(cascade = CascadeType.ALL,mappedBy = "wallet")
 private List<Transaction> transactions = new ArrayList<Transaction>();
 
 @OneToMany(cascade = CascadeType.ALL,mappedBy = "wallet")
 private List<BillPayment> billlist = new ArrayList<BillPayment>();
 
}