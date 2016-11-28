package com.derekchou.string;

import java.util.Scanner;

public class Shell {

	public static void exec(String cmd){
		try {
			Process p = Runtime.getRuntime().exec(cmd);
			int r = p.waitFor();
			String msg="";
			Scanner sc = new Scanner(p.getErrorStream());
			while(sc.hasNextLine()){
				msg += sc.nextLine() ;
			}
//		System.out.println("msg:"+msg);
			sc.close();
			p.destroy();
			if (r != 0) {
				throw new Exception(cmd + " returns:" + r +",msg:"+msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
