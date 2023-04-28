package com.ashokit.formbinding;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Data;

@Data
public class RegisterForm {
	
	
	@NotBlank(message = "Name should be mandatory")
	private String registerName;
	@NotBlank(message = "Email should be mandatory")
	private String email;
	@NotNull(message = "PhoneNumber should be mandatory")
	@Positive(message = "invalid phonenumber")
	private Long phoneNumber;
}
