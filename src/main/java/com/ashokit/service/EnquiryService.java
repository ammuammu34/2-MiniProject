package com.ashokit.service;


import java.util.List;

import com.ashokit.entity.Student;
import com.ashokit.formbinding.DashboardResponse;
import com.ashokit.formbinding.EnquiryForm;
import com.ashokit.formbinding.EnquirySearchCriteria;

public interface EnquiryService {
	public DashboardResponse getDashboardData(Integer registerId);
	public List<String> getCourses();

	public boolean save(EnquiryForm form);
	public List<Student> getEnquiries();
	public List<String> getEnquiryStatus();

   public List<Student> getFilterEnquiries(EnquirySearchCriteria criteria,Integer registerId);



}
