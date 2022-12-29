package com.masai.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Beneficiary {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer bid;
    
    @NotNull(message = "Name is required")
	private String name;
    @NotNull
	@Pattern(regexp = "[789]{1}[0-9]{9}")
	private String mobileNumber;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	private Wallet wallet;
}
