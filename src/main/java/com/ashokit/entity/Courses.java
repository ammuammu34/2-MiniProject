package com.ashokit.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "courseDtls")
@Data
public class Courses {
	@Id
	@GeneratedValue
	private Integer courseId;
	private String courseName;
	
}
