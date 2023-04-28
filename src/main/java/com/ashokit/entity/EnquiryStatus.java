package com.ashokit.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "statusDtls")
@Data
public class EnquiryStatus {
	@Id	
	@GeneratedValue
	private Integer statusId;
	private String statusName;
	

}
