package org.template.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController extends BaseController {

	@RequestMapping(path={"/","/index"})
	public String index(ModelMap model) {
		model.put("userName", subject().getPrincipal().toString());
		return "index";
	}
}
