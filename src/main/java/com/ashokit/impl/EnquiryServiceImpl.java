package com.ashokit.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.ashokit.entity.Courses;
import com.ashokit.entity.EnquiryStatus;
import com.ashokit.entity.Register;
import com.ashokit.entity.Student;
import com.ashokit.formbinding.DashboardResponse;
import com.ashokit.formbinding.EnquiryForm;
import com.ashokit.formbinding.EnquirySearchCriteria;
import com.ashokit.repo.CoursesRepository;
import com.ashokit.repo.EnquiryStatusRepository;
import com.ashokit.repo.RegisterRepository;
import com.ashokit.repo.StudentRepository;
import com.ashokit.service.EnquiryService;

@Service
public class EnquiryServiceImpl implements EnquiryService {
	
	@Autowired
	private RegisterRepository registerrepo;
	@Autowired
	private CoursesRepository courseRepo;
	@Autowired
	private EnquiryStatusRepository statusrepo;
	@Autowired
	private StudentRepository studentrepo;
	@Autowired
	private HttpSession session;

	@Override
	public DashboardResponse getDashboardData(Integer registerId) {
		
		DashboardResponse response = new DashboardResponse();
		
	Optional<Register> findById	=registerrepo.findById(registerId);
	if(findById.isPresent()) {
		Register register = findById.get();
		List<Student>enquiries = register.getStudent();
		Integer total=enquiries.size();
		
		List<Student> enrolledcnt = enquiries.stream().filter(e->e.getEnquiryStatus().equalsIgnoreCase("Enrolled"))
		.collect(Collectors.toList());
		Integer total1 = enrolledcnt .size();
		
		List<Student> lostcnt = enquiries.stream().filter(e->e.getEnquiryStatus().equalsIgnoreCase("Lost"))
				.collect(Collectors.toList());
		Integer total2 = lostcnt .size();
		
		response.setTotalEnquiryCnt(total);
		response.setEnrolledcnt(total1);
		response.setLostCnt(total2);
		
				
	}
		
		
		return response;
	}

	@Override
		public List<String> getCourses() {
		List<Courses> findAll = courseRepo.findAll();
		List<String> names = new ArrayList<>();
		
		for(Courses entity: findAll){
			names.add(entity.getCourseName());
		}

		return names;
		}
	@Override
		public List<String> getEnquiryStatus() {
		List<EnquiryStatus> findAll=statusrepo.findAll();
		List<String> status = new ArrayList<>();
		for(EnquiryStatus entity : findAll) {
			status.add(entity.getStatusName());
			
		}

		return status;
		}
	@Override
		public boolean save(EnquiryForm form) {
		Student entity = new Student();
		BeanUtils.copyProperties(form,entity);
		Integer registerId = (Integer)session.getAttribute("registerId");
		Register findById	=registerrepo.findById(registerId).get();
		entity.setRegister(findById);
		studentrepo.save(entity);
		return true;
		}

	@Override
	public List<Student> getEnquiries() {
		Integer registerId = (Integer)session.getAttribute("registerId");
		Optional<Register> findById	=registerrepo.findById(registerId);
		if(findById.isPresent()) {
			Register user = findById.get();
			List<Student> enquiries = user.getStudent();
			return enquiries;
			
		}
		return null;
	}

	@Override
	public  List<Student> getFilterEnquiries(EnquirySearchCriteria criteria,Integer registerId) {
		Optional<Register> findById	=registerrepo.findById(registerId);
		if(findById.isPresent()) {
			Register user = findById.get();
			List<Student> enquiries = user.getStudent();
			if(null!=criteria.getCourse()&&!"".equals(criteria.getCourse())) {
				enquiries=	enquiries.stream()
				         .filter(e->e.getCourse().equalsIgnoreCase(criteria.getCourse()))
				         .collect(Collectors.toList());

			}
			
			if(null!=criteria.getEnquiryStatus()&&!"".equals(criteria.getEnquiryStatus())) {
				enquiries=	enquiries.stream()
				         .filter(e->e.getEnquiryStatus().equalsIgnoreCase(criteria.getEnquiryStatus()))
				         .collect(Collectors.toList());

			}
			
			if(null!=criteria.getClassMode()&&!"".equals(criteria.getClassMode())) {
				enquiries=	enquiries.stream()
				         .filter(e->e.getClassMode().equalsIgnoreCase(criteria.getClassMode()))
				         .collect(Collectors.toList());

			}
			return enquiries;
			
		}

		return null;
	}

}