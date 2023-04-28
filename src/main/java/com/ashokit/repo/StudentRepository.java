package com.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ashokit.entity.Student;
import com.ashokit.formbinding.EnquirySearchCriteria;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	@Modifying
	@Query("select count(s) from Student where studentId = :studentId and enquiryStatus = :status")
	Integer getNoOfEnquiriesByStatus(@Param("status") String status,@Param("studentId") Integer studentId);

}
