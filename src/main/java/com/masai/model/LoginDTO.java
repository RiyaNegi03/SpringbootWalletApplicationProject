package com.masai.model;



import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class LoginDTO {
  
	
	@Pattern(regexp = "[789]{1}[0-9]{9}")
	private String mobileNumber;
	@Size(min=4,max=10,message ="Password size min 4 and max 10 ")
	private String password;
	
	
}