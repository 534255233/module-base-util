package com.zlp.util;

import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 
 * @author zhoulongpeng
 *
 */
public class AesCrypt {

	private final static String CRYPT_AES = "AES";
	// "算法/模式/补码方式"
	private static final String AES_CBC_PKCS5 = "AES/CBC/PKCS5Padding";
	/**微信的key: 0123456789asdfghjklz9876543210qwertyuiopxyz*/
	private static final String DEFAULT_KEY = "1234567890abcdef";
	private static final String DEFAULT_IV = "6666661234567890";
	
	private static Cipher genCipher(int mode, String sKey) throws Exception {
		// 判断Key是否为16位
		if (sKey == null || sKey.length() != 16) sKey =DEFAULT_KEY;
		byte[] raw = sKey.getBytes("ASCII");//"utf-8"，"ASCII"
		Key skeySpec = new SecretKeySpec(raw, CRYPT_AES);
		Cipher cipher = Cipher.getInstance(AES_CBC_PKCS5);
		// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
		IvParameterSpec iv = new IvParameterSpec(DEFAULT_IV.getBytes());
		cipher.init(mode, skeySpec, iv);
		return cipher;
	}

	// 加密
	public static String encrypt(String sSrc, String sKey) {
		try {
			Cipher cipher = genCipher(Cipher.ENCRYPT_MODE, sKey);
			byte[] encrypted = cipher.doFinal(sSrc.getBytes());
			// 此处使用BAES64做转码功能，同时能起到2次加密的作用。
			return new String(Base64.getEncoder().encode(encrypted), "UTF-8");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 解密
	public static String decrypt(String sSrc, String sKey) {
		try {
			Cipher cipher = genCipher(Cipher.DECRYPT_MODE, sKey);
			// 先用bAES64解密
			byte[] encrypted1 = Base64.getDecoder().decode(sSrc);
			byte[] original = cipher.doFinal(encrypted1);
			return new String(original);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
//uid=TMy4SMENm2CnCVE6um1nEA==&acc=Z5j5jm2r+WU5BGF+xIw5DeWE6Rw/lmT7mvtBkrBrUNs=
//		String jkl = AesCrypt.encrypt("123456", "1234567890abcdef");
//		System.out.println(jkl);// zU975HtvyI1dzz5LiAimxA==,1JQou2OQn7HZtqjIYKZgLQ==
//		String jkl2 = AesCrypt.decrypt(jkl, "1234567890abcdef");//Vx 0iTCruLoEVRhdw8Y3FQ==
//		System.out.println(jkl2);
		final String uid = AesCrypt.decrypt("TMy4SMENm2CnCVE6um1nEA==", null);
		System.out.println(uid);
		String jkl2 = AesCrypt.decrypt("Z5j5jm2r+WU5BGF+xIw5DeWE6Rw/lmT7mvtBkrBrUNs=", null);//Vx 0iTCruLoEVRhdw8Y3FQ==
		System.out.println(jkl2);

	}

}