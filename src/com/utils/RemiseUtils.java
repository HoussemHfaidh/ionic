package com.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RemiseUtils {

	public static String getRemiseFromPercentage(String remiseStr){
		String result = null;
		
		Pattern p = Pattern.compile("^(\\d{0,2})(\\.\\d{1,4})? *%?$"); 
		Matcher m = p.matcher(remiseStr);

		if (m.find()) {
			result = m.group(1);
			if (m.group(2) != null) {
				result += m.group(2);
			}
		}
		
		return result;
	}
	
	public static boolean isValidPercentageremise(String remiseStr){
		Pattern p = Pattern.compile("^(\\d{0,2})(\\.\\d{1,4})? *%?$"); 
		Matcher m = p.matcher(remiseStr);

		return m.find();
	}
	
}
