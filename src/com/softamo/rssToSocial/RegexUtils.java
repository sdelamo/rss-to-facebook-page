package com.softamo.rssToSocial;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {
	public static boolean IsMatch(String s, String pattern) {
		try {
			Pattern patt = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
			Matcher matcher = patt.matcher(s);
			return matcher.matches();
		} catch (RuntimeException e) {
			return false;
		}           
	}   

	public static String getMatchGroup(String s, String pattern, int group) {
		Pattern patt = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
		Matcher matcher = patt.matcher(s);
		if(matcher!=null) {
			while(matcher.find()) {
				return matcher.group(group);
			}
		}
		return "";
	}
}
