package com.ashokit.dataloader;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.ashokit.entity.Courses;
import com.ashokit.entity.EnquiryStatus;
import com.ashokit.repo.CoursesRepository;
import com.ashokit.repo.EnquiryStatusRepository;

@Component
public class DataLoader implements ApplicationRunner {
	@Autowired
	private CoursesRepository courserepo;
	@Autowired
	private EnquiryStatusRepository statusrepo;

	@Override
	public void run(ApplicationArguments args) throws Exception {


		
		courserepo.deleteAll();
		Courses c1 = new Courses();
		c1.setCourseName("java");

		Courses c2 = new Courses();
		c2.setCourseName("python");

		Courses c3 = new Courses();
		c3.setCourseName("devops");

		// List<Courses> entity=
				 courserepo.saveAll(Arrays.asList(c1,c2,c3));
		
		statusrepo.deleteAll();
		
		EnquiryStatus e1 = new EnquiryStatus();
		e1.setStatusName("new");

		EnquiryStatus e2 = new EnquiryStatus();
		e2.setStatusName("enrolled");


		EnquiryStatus e3 = new EnquiryStatus();
		e3.setStatusName("lost");
		
		// List<EnquiryStatus> list =
				 statusrepo.saveAll(Arrays.asList(e1,e2,e3));

		//courserepo.saveAll(entity);
		//statusrepo.saveAll(list);
		System.out.println("data inserted...");
	}

}



