package com.ashokit.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ashokit.entity.Student;
import com.ashokit.formbinding.DashboardResponse;
import com.ashokit.formbinding.EnquiryForm;
import com.ashokit.formbinding.EnquirySearchCriteria;
import com.ashokit.repo.StudentRepository;
import com.ashokit.service.EnquiryService;

import ch.qos.logback.core.net.SyslogOutputStream;

@Controller
public class EnquiryController {

	@Autowired
	private EnquiryService enquiryservice;
	@Autowired
	private StudentRepository studentrepo;

	@Autowired
	private HttpSession session;

	@GetMapping("/viewenquiries")
	public String ViewEnquiryStatus(EnquirySearchCriteria search,Model model) {
		initForm(model);
		model.addAttribute("search", new EnquirySearchCriteria());
		List<Student> enquiries = enquiryservice.getEnquiries();
		model.addAttribute("enquiries", enquiries);
		
		return "view-enquiries";
	}
	
	
	@GetMapping("/filter-enquiries")
	public String getFilterEnquiries(@RequestParam String courses,@RequestParam  String status,@RequestParam  String classMode,Model model) {
		EnquirySearchCriteria criteria = new EnquirySearchCriteria();
		criteria.setCourse(courses);
		criteria.setEnquiryStatus(status);
		criteria.setClassMode(classMode);
		
		System.out.println(criteria);
		Integer userId = (Integer)session.getAttribute("registerId");
		List<Student> filterenquiry = enquiryservice.getFilterEnquiries(criteria,userId);
		
		model.addAttribute("enquiries", filterenquiry);
		//System.out.println(criteria);
		return "filter-enquiries";
		
	}
	
	@GetMapping("/addenquiry")
	public String addEnquiry(Model model) {
		initForm(model);
		return "add-enquiry";
	}
	private void initForm(Model model) {
		List<String> courses = enquiryservice.getCourses();
		List<String> status = enquiryservice.getEnquiryStatus();
		EnquiryForm formobj = new EnquiryForm();
		model.addAttribute("courseNames",courses);
		model.addAttribute("statusNames",status);
		model.addAttribute("formobj",formobj);
	}
	
	
	
	@PostMapping("/addenquiry")
	public String Enquiry(@ModelAttribute("formobj") EnquiryForm formobj ,Model model) {
		System.out.println(formobj);
	boolean status =	enquiryservice.save(formobj);
	if(status) {
		model.addAttribute("succmsg", "enquiry added");
	}else {
		model.addAttribute("errmsg","problem occured");
	}

	return "add-enquiry";
	}
	
	
	
	
	@GetMapping("/dashboard")
	public String loadDashboard(Model model) {
		System.out.println("dashboard method called...");
		Integer registerId = (Integer)session.getAttribute("registerId");
		DashboardResponse response = enquiryservice.getDashboardData(registerId);
		model.addAttribute("response",response);
		return "dashboard";
	} 
	
	@GetMapping("/edit")
	public String edit(@RequestParam("id") Integer id,Model model) {
		model.addAttribute("enquiries",studentrepo.findById(id));
		return "add-enquiry";
	}
	
	
	
	

	/*@GetMapping("/error")
	public String handleError( HttpRequestHandler request,Model model) {
		 Object status = model.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

	        if (status != null) {
	            Integer statusCode = Integer.valueOf(status.toString());

	            if (statusCode == HttpStatus.NOT_FOUND.value()) {
	            	status = "error/404";

	            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
	            	status = "error/403";

	            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
	            	status = "error/500";

	            }
	        }

		return "errorpage";
	}

	 */
}
