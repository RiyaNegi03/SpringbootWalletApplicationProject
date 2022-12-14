package com.masai.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class LogDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer log_Id;
	
	private Integer cid;
	
	private LocalDateTime localDateTime;
	
	private String uuid;
	
	private String logtype;
	

}