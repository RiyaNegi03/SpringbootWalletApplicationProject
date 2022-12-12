package com.masai.model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
 


 

}