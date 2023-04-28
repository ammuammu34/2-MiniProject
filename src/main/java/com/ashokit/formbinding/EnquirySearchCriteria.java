package com.ashokit.formbinding;

import java.time.LocalDate;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
public class EnquirySearchCriteria {
	
	
	private Integer studentId;
	private String studentName;
	private String course;
	private String classMode;
	private String enquiryStatus;
	private Long phoneNumber;
	@CreationTimestamp
	private LocalDate enquiryDate;
	@UpdateTimestamp
	private LocalDate updateDate;

}
