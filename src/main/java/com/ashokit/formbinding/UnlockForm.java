package com.ashokit.formbinding;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UnlockForm {
	
	
	@NotBlank(message = "This temporary password field is required ")
	private String temporaryPwd;
	@NotBlank(message = "This new password field is required")
	private String newPassword;
	@NotBlank(message = "This confirm password field is required")
	private String confirmPassword;
	
	private String email;
	
	

}
