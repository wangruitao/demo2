package org.template.com;

import java.util.HashMap;
import java.util.Map;

import org.template.com.model.Permission;
import org.template.com.model.Role;
import org.template.com.model.User;

public class TestMain {

	private static final Map<Class<?>, Object> entityTableMap = new HashMap<Class<?>, Object>();
	
	public static void main(String[] args) {
		entityTableMap.put(User.class, "1");
		entityTableMap.put(Role.class, "2");
		entityTableMap.put(Permission.class, "3");
		System.out.println(entityTableMap.get(User.class));
	}

}
