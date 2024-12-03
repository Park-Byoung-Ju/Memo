package com.totra.memo.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5HashingEncoder {
	// md5로 hashing 암호화
	// md5를 통한 암호화 기능
	public static String encode(String message) {
		String result = "";
		
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("md5");
		
			byte[] bytes = message.getBytes();
			
			messageDigest.update(bytes);
			
			byte[] digest = messageDigest.digest();			

			for(int i = 0; i < digest.length; i++) {
				result += Integer.toHexString(digest[i] & 0xff);
			}
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return result;
	}
}
