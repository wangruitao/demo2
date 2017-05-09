package org.template.com.common.util;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.template.com.model.User;

public class CredentialsSalt {

	private static final String ALGORITHM_NAME = "MD5"; 
	private static final int hashIterations = 2;  
	
	public static void encrypt(User user) {
		String salt1 = user.getLoginName();  
		String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();  
		
		String salt = salt1 + salt2;
		SimpleHash hash = new SimpleHash(ALGORITHM_NAME, user.getPassword(), salt, hashIterations);  
		String encodedPassword = hash.toHex();   
		user.setPassword(encodedPassword);
		user.setSalt(salt);
	}
	
/*	public static void main(String[] args) {
		String salt1 = "tom"; 
		String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex(); 
		String salt = salt1 + salt2;
		SimpleHash hash = new SimpleHash(ALGORITHM_NAME, "123456", salt, hashIterations);  
		String encodedPassword = hash.toHex(); 
		System.out.println(salt + "===================" + encodedPassword);
	}*/
}
