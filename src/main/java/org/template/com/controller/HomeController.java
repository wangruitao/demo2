package org.template.com.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;

@Controller
public class HomeController extends BaseController {

	@RequiresPermissions("user:update")
	public void test() {
		
	}
}
