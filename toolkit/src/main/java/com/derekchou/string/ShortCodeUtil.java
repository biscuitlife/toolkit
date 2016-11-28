package com.derekchou.string;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * 生短代码
 * @author dirk
 *
 */
public class ShortCodeUtil {
	
	public static void main(String[] args) {
		System.out.println(ShortCodeUtil.generate(UUID.randomUUID().toString()));
	}

	public static String generate(String keyword) {
        final String[] CHARS_DIC = new String[] { "a", "b", "c", "d", "e", "f",
                "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
                "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3",
                "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F",
                "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
                "S", "T", "U", "V", "W", "X", "Y", "Z" };
 
        // final String keyword = "http://sports.sina.com.cn/nba";
        byte[] encryptedTextBytes = null;
 
        try {
            MessageDigest md5Digest = MessageDigest.getInstance("MD5");
            md5Digest.reset();
            md5Digest.update(keyword.getBytes("UTF-8"));
            encryptedTextBytes = md5Digest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
 
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            String hex1 = Integer.toHexString(0xff & encryptedTextBytes[i * 2]);
            String hex2 = Integer
                    .toHexString(0xff & encryptedTextBytes[i * 2 + 1]);
 
            hex1 = hex1.length() == 1 ? "0" + hex1 : hex1;
            hex2 = hex2.length() == 1 ? "0" + hex2 : hex2;
            int index = (int) Long.parseLong(hex1 + hex2, 16)
                    % CHARS_DIC.length;
            result.append(CHARS_DIC[index]);
        }
 
        return result.toString();
    }
}
