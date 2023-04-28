package com.ashokit.entity;

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

import lombok.Data;

@Entity
@Table(name = "registerDtls")
@Data
public class Register {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer registerId;
	private String registerName;
	private String email;
	private Long phoneNumber;
	private String password;
	private String accountStatus;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "register", fetch = FetchType.EAGER)
	private List<Student> student;



}
