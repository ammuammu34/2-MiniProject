package com.ashokit.impl;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashokit.entity.Register;
import com.ashokit.formbinding.LoginForm;
import com.ashokit.formbinding.RegisterForm;
import com.ashokit.formbinding.UnlockForm;
import com.ashokit.repo.RegisterRepository;
import com.ashokit.service.UserService;
import com.ashokit.utils.EmailUtils;
import com.ashokit.utils.UserPwdUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private RegisterRepository repo;
	@Autowired
	private EmailUtils emailUtils;
	@Autowired
	private HttpSession session;
	@Override
	public boolean register(RegisterForm form) {
		Register users = repo.findByEmail(form.getEmail());
		if(users!= null){
			return false;
		}
		Register user = new Register();
		BeanUtils.copyProperties(form, user);
		String tempPwd = UserPwdUtils.generateRandomPwd();
		user.setPassword(tempPwd);
		user.setAccountStatus("Locked");
		repo.save(user);
		String to = form.getEmail();
		String subject = "unlock your account";
		StringBuffer body = new StringBuffer("");
		body.append("<h1>use below temporary password to unlock your accout</h1>");
		body.append("Temporary password : "+tempPwd);
		body.append("<br/>");
		body.append("<a href=\"http://localhost:8080/unlock?email=" +to +"\"> Click here to unlock your Acount></a>");

		emailUtils.sendEmail(to,subject,body.toString());
		return true;
	}


	@Override
	public boolean resetPassword(String email) {
		Register entity = repo.findByEmail(email);
		if(entity == null){
			return false;
		} 
		String  subject = "recover password";
		String body = "your password:: "+ entity.getPassword();
		emailUtils.sendEmail(email,subject,body);
		return true;
	}


	@Override
	public boolean unlockAccount(UnlockForm form) {
		Register entity = repo.findByEmail(form.getEmail());
		if(entity.getPassword().equalsIgnoreCase(form.getTemporaryPwd())) {
			entity.setPassword(form.getNewPassword());
			entity.setAccountStatus("unlocked");
			repo.save(entity);
			return true;
		}else {

			return false;
		}
	}

	@Override
	public String login(LoginForm loginform){

		Register entity = repo.findByEmailAndPassword(loginform.getEmail(),loginform.getPassword());
		if(entity ==null) {
			return "invalid credentials";
		}
		if(entity.getAccountStatus().equalsIgnoreCase("LOCKED")) {
			return "Your Account Locked";
		}
		session.setAttribute("registerId",entity.getRegisterId());
		return "Success";
	}

}
