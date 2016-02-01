package com.zlp.util;

import java.security.Key;
import java.security.MessageDigest;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 
 * @author zhoulongpeng
 *
 */
public class AesUtil {

	public static final int SECRET_128 = 128;
	public static final int SECRET_256 = 256;
	private static final String DEFAULT_KEY = "rtuy*7890";

	private static final byte[] kbytes = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8,
			9, 0, 1, 2, 3, 4, 5, 6 };
	private static final IvParameterSpec DEFAULT_IV = new IvParameterSpec(
			kbytes);
	private static final String SECRET_AES = "AES";// Secret
	/**
	 * AES使用CBC模式与PKCS5Padding
	 * 
	 * CryptoJS supports the following modes: CBC (the default) CFB CTR OFB ECB
	 * 
	 * And CryptoJS supports the following padding schemes: Pkcs7 (the default)
	 * Iso97971 AnsiX923 Iso10126 ZeroPadding NoPadding PKCS5Padding
	 * */
	private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
	/** AES加解密的密钥 */
	private Key key;
	/** AES CBC模式使用的Initialization Vector */
	private IvParameterSpec iv;
	/** Cipher 物件 */
	private Cipher cipher;

	public AesUtil() {
		this(DEFAULT_KEY, SECRET_256);
	}

	/**
	 * 构造函数，使用128 Bits的AES密钥(计算任意长度密钥的MD5)和预设IV
	 *
	 * @param key
	 *            传入任意长度的AES密钥
	 */
	public AesUtil(final String key) {
		this(key, SECRET_256);
	}

	/**
	 * 构造函数，使用128 Bits或是256 Bits的AES密钥(计算任意长度密钥的MD5或是SHA256)和预设IV
	 *
	 * @param key
	 *            传入任意长度的AES密钥
	 * @param bit
	 *            传入AES密钥长度，數值可以是128、256 (Bits)
	 */
	public AesUtil(final String key, final int bit) {
		this(key, bit, null);
	}

	/**
	 * 构造函数，使用128 Bits或是256 Bits的AES密钥(计算任意长度密钥的MD5或是SHA256)，用MD5计算IV值
	 *
	 * @param key
	 *            传入任意長度的AES密钥
	 * @param bit
	 *            传入AES密钥长度，数值可以是128、256 (Bits)
	 * @param iv
	 *            传入任意长度的IV字串
	 */
	public AesUtil(final String key, final int bit, final String iv) {
		if (bit == 256) {
			 this.key = new SecretKeySpec(getHash("SHA-256", key), SECRET_AES);
//			this.key = new SecretKeySpec(key.getBytes(), SECRET_AES);
		} else {
			this.key = new SecretKeySpec(getHash("MD5", key), SECRET_AES);
		}
		if (iv != null) {
			this.iv = new IvParameterSpec(getHash("MD5", iv));
		} else {
			this.iv = DEFAULT_IV;
		}

		init();
	}

	// -----物件方法-----
	/**
	 * 取得字串的哈西值
	 *
	 * @param algorithm
	 *            传入哈西演算法
	 * @param text
	 *            传入要哈西的字串
	 * @return 传回哈西字节数组
	 */
	private byte[] getHash(final String algorithm, final String text) {
		try {
			return getHash(algorithm, text.getBytes("UTF-8"));
		} catch (final Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	/**
	 * 取得字节数组的哈西值
	 *
	 * @param algorithm
	 *            传入哈西演算法
	 * @param data
	 *            传入要哈西的字节数组
	 * @return 传回哈西后字节数组
	 */
	private byte[] getHash(final String algorithm, final byte[] data) {
		try {
			final MessageDigest digest = MessageDigest.getInstance(algorithm);
			digest.update(data);
			return digest.digest();
		} catch (final Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	/**
	 * 初始化
	 */
	private void init() {
		try {
			cipher = Cipher.getInstance(TRANSFORMATION);
		} catch (final Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	/**
	 * 加密字符串
	 *
	 * @param str
	 *            传入要加密的字符串
	 * @return 传回加密后的字符串
	 */
	public String encrypt(final String str) {
		try {
			return encrypt(str.getBytes("UTF-8"));
		} catch (final Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	/**
	 * 加密字节数组
	 *
	 * @param data
	 *            传入要加密的字节数组
	 * @return 传回加密后的字符串
	 */
	public String encrypt(final byte[] data) {
		try {
			cipher.init(Cipher.ENCRYPT_MODE, key, iv);
			final byte[] encryptData = cipher.doFinal(data);
			return new String(Base64.getEncoder().encode(encryptData), "UTF-8");
		} catch (final Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	/**
	 * 解密
	 *
	 * @param str
	 *            传入要解密的字符串
	 * @return 传回解密后的字符串
	 */
	public String decrypt(final String str) {
		try {
			return decrypt(Base64.getDecoder().decode(str));
		} catch (final Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	/**
	 * 解密
	 *
	 * @param data
	 *            传入要解密的字节数组
	 * @return 传回解密后的字符串
	 */
	public String decrypt(final byte[] data) {
		try {
			cipher.init(Cipher.DECRYPT_MODE, key, iv);
			final byte[] decryptData = cipher.doFinal(data);
			return new String(decryptData, "UTF-8");
		} catch (final Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}


	public static void main(String[] args) {
		AesUtil ggg = new AesUtil("1234567890123456", AesUtil.SECRET_256);
		String ii = ggg.encrypt("0");
		System.out.println(ii);// Vrd1cs1ULxY2bwQ8ckgP6Q==
		String yy = ggg.decrypt(ii);
		System.out.println(yy);
	}

}