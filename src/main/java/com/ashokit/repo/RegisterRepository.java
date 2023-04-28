package com.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ashokit.entity.Register;

public interface RegisterRepository extends JpaRepository<Register, Integer> {

	public Register findByEmail(String email);
	//@Modifying
	//@Query("select r from Register r where email = :email and password = :password")
	 public Register findByEmailAndPassword(@Param("email") String email,@Param("password") String password);
}
