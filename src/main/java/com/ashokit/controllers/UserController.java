package com.ashokit.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ashokit.entity.Register;
import com.ashokit.formbinding.LoginForm;
import com.ashokit.formbinding.RegisterForm;
import com.ashokit.formbinding.UnlockForm;
import com.ashokit.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("/loadlogin")
	public String loadLogin(Model model) {
		model.addAttribute("login", new LoginForm());
		return "login";
	}

	@PostMapping("/login")
	public String loadLogin(@Validated @ModelAttribute("login") LoginForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "login";
		}
		String status = service.login(form);
		if (status.contains("Success")) {
			return "redirect:/dashboard";
		}
		model.addAttribute("errmsg", status);
		return "login";
	}

	@GetMapping("/loadsignup")
	public String showRegisterForm(Model model) {
		model.addAttribute("signup", new RegisterForm());
		return "signup";
	}

	@PostMapping("/signup")
	public String showRegisterForm(@Validated @ModelAttribute("signup") RegisterForm form, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return "signup";
		}
		boolean users = service.register(form);
		if (users) {
			model.addAttribute("smsg", "Check your email");
		} else {
			model.addAttribute("emsg", "email already exist");
		}
		return "signup";
	}

	@GetMapping("/unlock")
	public String loadUnlock(@RequestParam String email, Model model) {
		UnlockForm unlockFormObj = new UnlockForm();
		unlockFormObj.setEmail(email);
		model.addAttribute("unlock", unlockFormObj);

		return "unlock";
	}

	@PostMapping("/unlock")
	public String Unlock(@ModelAttribute("unlock") UnlockForm unlock, Model model) {
		System.out.println(unlock);
		if (unlock.getNewPassword().equalsIgnoreCase(unlock.getConfirmPassword())) {
			boolean status = service.unlockAccount(unlock);
			if (status) {
				model.addAttribute("succmsg", "your account unlocked successfully");
			} else {
				model.addAttribute("errmsg", "Given temporary password is incorrect,check your email");
			}
		} else {
			model.addAttribute("errmsg", "new password and confirm password should be same");
		}
		return "unlock";
	}

	@GetMapping("/forgot")
	public String resetPassword(Model model) {

		return "forgotpwd";
	}

	@PostMapping("/forgot")
	public String reset(@RequestParam("email") String email, Model model) {
		System.out.println(email);
		boolean status = service.resetPassword(email);
		if (status) {
			model.addAttribute("succmsg", "forgot password sent your email");
		} else {
			model.addAttribute("errmsg", "Invalid Credentials");
		}
		return "forgotpwd";
	}
}