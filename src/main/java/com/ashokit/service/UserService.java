package com.ashokit.service;


import com.ashokit.formbinding.LoginForm;
import com.ashokit.formbinding.RegisterForm;
import com.ashokit.formbinding.UnlockForm;


public interface UserService {
	public boolean  register(RegisterForm form);
	public boolean unlockAccount(UnlockForm unlockform);
	public boolean resetPassword(String email);
	public String login(LoginForm loginform);



}
