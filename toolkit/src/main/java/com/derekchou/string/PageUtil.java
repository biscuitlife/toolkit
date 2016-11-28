package com.derekchou.string;

import java.util.ArrayList;
import java.util.List;

public class PageUtil {
	private static int PAGE_LENGTH = 7;
	private static int LEFT_LENGTH = 5;
	private static int RIGHT_LENGTH = 5;

	public static List<String> getPageNoList(int currentNo, int totalNo) {
		List<String> result = new ArrayList<String>();
		if (totalNo < 1 || currentNo < 1 || totalNo < currentNo) {
			result.add("1");
		} else if (totalNo <= PAGE_LENGTH) {
			for (int i = 1; i <= totalNo; i++) {
				result.add(i + "");
			}
		} else {
			if (currentNo < LEFT_LENGTH) {
				for (int i = 1; i <= currentNo; i++) {
					result.add(i + "");
				}
			} else {
				result.add("1");
				result.add("...");
				for (int i = currentNo - 2; i <= currentNo; i++) {
					result.add(i + "");
				}
			}
			if ((totalNo - currentNo) < RIGHT_LENGTH) {
				for (int i = currentNo + 1; i <= totalNo; i++) {
					result.add(i + "");
				}
			} else {
				for (int i = currentNo + 1; i <= currentNo + 2; i++) {
					result.add(i + "");
				}
				result.add("...");
				result.add(totalNo + "");
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(getPageNoList(10, 10));
	}
}
