package org.template.com.controller;

import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.template.com.common.util.StringUtil;
import org.template.com.model.User;

@Controller
public class LoginController extends BaseController{

	@RequestMapping(path="/login")
	public String login(@RequestParam(value="name", required=false) String name, @RequestParam(value="password", required=false) String password) {
		Subject subject = subject();
		if(StringUtil.isNotEmpty(name) && StringUtil.isNotEmpty(password)) {
			
			UsernamePasswordToken token = new UsernamePasswordToken(name, password);
			try {
				subject.login(token);
			} catch (IncorrectCredentialsException e) {
				throw new RuntimeException("登录密码错误. Password for account " + token.getPrincipal() + " was incorrect.");
				
			} catch (ExcessiveAttemptsException e) {
				throw new RuntimeException("登录失败次数过多");
				
			} catch (LockedAccountException e) {
				throw new RuntimeException("帐号已被锁定. The account for username " + token.getPrincipal() + " was locked.");
				
			} catch (DisabledAccountException e) {
				throw new RuntimeException("帐号已被禁用. The account for username " + token.getPrincipal() + " was disabled.");
				
			} catch (ExpiredCredentialsException e) {
				throw new RuntimeException("帐号已过期. the account for username " + token.getPrincipal() + "  was expired.");
				
			} catch (UnknownAccountException e) {
				throw new RuntimeException("帐号不存在. There is no user with username of " + token.getPrincipal());
				
			} catch (UnauthorizedException e) {
				throw new RuntimeException("您没有得到相应的授权！" + e.getMessage());
				
			}
		}
		if(subject.isAuthenticated()) {
			return "/index";
		} 
		return "login";
	}
	
	@RequestMapping(path="/logout")
	public String logout() {
		subject().logout();
		return "redirect:/login.html";
	}
}
