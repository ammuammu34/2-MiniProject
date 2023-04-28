package com.ashokit.formbinding;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class EnquiryForm {
	
	@NotBlank(message = "name should be mandatory")
	private String studentName;
	@NotBlank(message = "this field is mandatory")
	private String course;
	@NotBlank(message = "classMode field is mandatory")
	private String classMode;
	@NotBlank(message = "enquiryStatus field is mandatory")
	private String enquiryStatus;
	@NotBlank(message = "this field is mandatory")
	private String enquiryDate;
	private Long phoneNumber;
	

}
