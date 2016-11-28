package com.derekchou.string;

import java.util.Random;

public class RandUtil {
	
	public static String getRandCode(){
		return getRandCode(6);
	}
	public static String getRandCode(int len){
		String result = "";
		Random random = new Random();
		double next = random.nextDouble();
		next *= Math.pow(10, len);
		result = String.valueOf((int)next);
		while(result.length() < len){
			result = "0"+result;
		}
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(getRandCode(18));
	}
}
