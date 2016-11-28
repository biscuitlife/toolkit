package com.derekchou.string;

public class MathUtil {

	public static float absAndFormat(float number, int decimals){
		float result = number;
		result = absAndFormat(number, decimals, true);
		return result;
	}
	
	public static float absAndFormat(float number, int decimals, boolean useAbs){
		float result = number;
		if(useAbs){			
			result = Math.abs(result);
		}
		float dec = 1;
		for (int i = 0; i < decimals; i++) {
			dec *= 10;
		}
		result = Math.round(result*dec)/dec;
		return result;
	}
}
