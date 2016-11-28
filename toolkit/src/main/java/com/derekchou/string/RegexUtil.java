package com.derekchou.string;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {

	/**
	 * 通过正则表达式，从输入字符串中，获取指定值
	 * 
	 * @param str
	 *            输入字符串
	 * @param regEx
	 *            正则表达式
	 */
	public static String getValueByPattern(String str, String regEx) {
		String rtnStr = "";
		if (regEx != null && regEx.length() > 0) {
			Pattern p = Pattern.compile(regEx);
			Matcher m = p.matcher(str);
			boolean rs = m.find();

			if (rs && m.groupCount() > 0) {
				rtnStr = m.group(1);
			}
		} else {
			rtnStr = str;
		}
		return rtnStr;
	}
	
	public static String getValueByPattern(String str, String regEx, int group) {
		String rtnStr = "";
		if (regEx != null && regEx.length() > 0) {
			Pattern p = Pattern.compile(regEx);
			Matcher m = p.matcher(str);
			boolean rs = m.find();
			
			if (rs && m.groupCount() > 0) {
				rtnStr = m.group(group);
			}
		} else {
			rtnStr = str;
		}
		return rtnStr;
	}
	
	public static List<String> getAllValueByPattern(String str, String regEx) {
		List<String> results = new ArrayList<String>();
		if (regEx != null && regEx.length() > 0) {
			Pattern p = Pattern.compile(regEx);
			Matcher m = p.matcher(str);
			while(m.find()) {
				results.add(m.group(1));
			}
		} else {
		}
		return results;
	}
	
}
