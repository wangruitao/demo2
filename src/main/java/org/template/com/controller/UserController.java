package org.template.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.template.com.common.util.CredentialsSalt;
import org.template.com.model.User;
import org.template.com.service.UserService;

@RequestMapping("/user")
@Controller
public class UserController extends BaseController {

	@Autowired
	private UserService userService;

	@RequestMapping("/list")
	public String list(ModelMap model) {
		List<User> list = userService.findAll();
		model.put("users", list);
		return "user_list";
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public String save(ModelMap model, User user) {
		CredentialsSalt.encrypt(user);
		user = userService.save(user);
		return "redirect:/list";
	}
}
