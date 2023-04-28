package com.ashokit.formbinding;

import javax.validation.constraints.NotBlank;

import lombok.Data;
@Data
public class LoginForm {
	
	@NotBlank(message = "mail should be mandatory")
	private String email;
	@NotBlank(message = "password should be mandatory")
	private String password;
	

}
