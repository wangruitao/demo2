package org.template.com.common.util;

import org.apache.commons.lang.StringUtils;

public final class StringUtil {

	
	public static boolean isEmpty(String value) {
		
		if(value != null) {
			value = value.trim();
		}
		return StringUtils.isEmpty(value);
	}
	
	public static boolean isNotEmpty(String value) {
		return !isEmpty(value);
	}
}
