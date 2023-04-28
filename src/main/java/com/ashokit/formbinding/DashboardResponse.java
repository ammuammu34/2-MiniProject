package com.ashokit.formbinding;

import java.time.LocalDate;

import lombok.Data;

@Data
public class DashboardResponse {
	//private Integer studentId;
	//private String studentName;
	//private String course;
	//private String classMode;
	//private String enquiryStatus;
	//private LocalDate enquiryDate;
	//private LocalDate updateDate;
	private Integer totalEnquiryCnt;
	
	private Integer enrolledcnt;
	private Integer lostCnt;

}
