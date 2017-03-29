package org.template.com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String save(ModelMap model, User user) {
		CredentialsSalt.encrypt(user);
		user = userService.save(user);
		return "redirect:/user/list.html";
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public String edit(ModelMap model, @PathVariable("id") Long id) {
		User user = userService.findByUserId(id);
		model.put("pojo", user);
		return "user_add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String add(ModelMap model) {
		User user = new User();
		model.put("pojo", user);
		return "user_add";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(User user) {
		boolean isSuc = userService.update(user);
		return "redirect:/user/list.html";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> delete( Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(userService.delete(id)) {
			map.put("isSuc", true);
		}
		return ResponseEntity.ok(map);
	}
}
