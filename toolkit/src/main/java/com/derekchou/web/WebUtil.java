package com.derekchou.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class WebUtil {

	public static String sendGet(String url) {
		String content = "";
		HttpURLConnection con = null;
		try {
			con = (HttpURLConnection) new URL(url).openConnection();
			// con.setRequestProperty("user-agent", "");
			con.setConnectTimeout(3000);
			con.setReadTimeout(3000);
			con.setRequestMethod("GET");
			con.setDoOutput(true);
			con.setDoInput(true);
			BufferedReader bReader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			StringBuffer sb = new StringBuffer("");
			String temp;
			boolean remaining = true;
			while (remaining) {
				temp = bReader.readLine();
				if (null != temp) {
					sb.append(temp);
				} else {
					remaining = false;
				}
			}
			content = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != con) {
				con.disconnect();
			}
		}
		return content;
	}

	public static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			URLConnection conn = realUrl.openConnection();
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			out = new PrintWriter(conn.getOutputStream());
			out.print(param);
			out.flush();
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
}
