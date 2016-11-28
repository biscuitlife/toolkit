package com.derekchou.string;

public class StringUtil {

	//去掉html标签
	public static String asText(String webContent){
		try {
			webContent = webContent.replaceAll("<.*?>", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return webContent;
	}
	
	//检查内容字段是否包含指定特殊标签
	public static boolean hasForbidTags(String webContent, String tags){
		if(tags == null || tags == ""){
			return false;
		}
		String[] split = tags.split("\\|");
		try {
			webContent = webContent.replaceAll(" ", "");
			webContent = webContent.replaceAll("	", "");
			for (int i = 0; i < split.length; i++) {
				if(webContent.contains("<"+split[i])){
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	public static String subString(String string, int count, String ellipsis){
		return subString(string, count, ellipsis, true);
	}
	
	/**
	 * 
	 * @param string 字符串
	 * @param count 截取数量
	 * @param ellipsis 省略号的格式
	 * @param fromLeft true：从头截取，false：从尾截取
	 * @return
	 */
	public static String subString(String string, int count, String ellipsis, boolean fromLeft){
		if(string == null || string == "") return "";
		if(string.trim().length() < count) return string;
		string = string.trim();
		if(fromLeft){
			string = string.substring(0, count);			
		}else{
			string = string.substring(string.length() - count);
		}
		string += ellipsis;
		return string;
	}

	public static String trim(String origin) {
		if(origin == null || origin == ""){
			return "";
		}else{
			return origin.trim();
		}
	}

	/**
	 * 获取最后一个recorder
	 * @param recorder
	 * @return
	 */
	public static String lastRecorder(String recorder) {
		if(recorder == null || recorder == ""){
			return "";
		}
		try {
			String[] split = recorder.split(",");
			if(split.length > 0){
				return split[split.length - 1];
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 去除多余p标签
	 * @param content
	 * @return
	 */
	public static String clearPTag(String content) {
		content = content.replace(" ", "");
		content = content.replace("	", "");
		content = content.replace("\n", "");
		content = content.replace("<p></p>", "");
			
		return content;
	}
	
}
