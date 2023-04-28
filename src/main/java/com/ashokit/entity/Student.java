package com.ashokit.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Table(name = "studentDtls")
@Data
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "registerId")
	private Register register;

}
