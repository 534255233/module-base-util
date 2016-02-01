package com.zlp.util;

import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class CryptUtil {

//	private static Logger log = LoggerFactory.getLogger(CryptUtil.class);
	/** 
     * DES算法 
     */  
    private static final String ALGORITHM_DES = "DES"; 

	/**
	 * MD5加密
	 * @param arg 需要加密的信息
	 * @return
	 */
	public static String md5encrypt(String arg) {
		try {
			// 得到一个md5的消息摘要
			final MessageDigest alga = MessageDigest.getInstance("MD5");
			// 添加要进行计算摘要的信息
			alga.update(arg.getBytes());
			// 得到该摘要
			final byte[] digesta = alga.digest();
			return byte2hex(digesta);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
//			log.severe(e.toString());
			return "0";
		}
	}

	/**
	 * 将二进制转化为16进制字符串
	 * 
	 * @param b 二进制字节数组
	 * @return String
	 */
	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
		}
		return hs.toUpperCase();
	}

	/**
	 * 根据密匙进行DES加密
	 * 
	 * @param key 密匙
	 * @param info 要加密的信息
	 * @return String 加密后的信息
	public static String encryptToDES(SecretKey key, String info) {
		// 定义 加密算法,可用 DES,DESede,Blowfish
		final String algorithm = "DES";
		// 加密随机数生成器 (RNG),(可以不写)
		final SecureRandom sr = new SecureRandom();
		try {
			// 得到加密/解密器
			Cipher c1 = Cipher.getInstance(algorithm);
			// 用指定的密钥和模式初始化Cipher对象 参数:(ENCRYPT_MODE, DECRYPT_MODE, WRAP_MODE,UNWRAP_MODE)
			c1.init(Cipher.ENCRYPT_MODE, key, sr);
			// 要生成的密文, 对要加密的内容进行编码处理,
			byte[] cipherByte = c1.doFinal(info.getBytes());
			return byte2hex(cipherByte);
		} catch (Exception e) {
			e.printStackTrace();
			return "0";
		}
		// 返回密文的十六进制形式
	} */

	/**
	 * 根据密匙进行DES解密
	 * 
	 * @param key 密匙
	 * @param sInfo 要解密的密文
	 * @return String 返回解密后信息
	public static String decryptByDES(SecretKey key, String secuStr) {
		// 定义 加密算法,
		final String algorithm = "DES";
		// 加密随机数生成器 (RNG)
		final SecureRandom sr = new SecureRandom();
		try {
			// 得到加密/解密器
			Cipher c1 = Cipher.getInstance(algorithm);
			// 用指定的密钥和模式初始化Cipher对象
			c1.init(Cipher.DECRYPT_MODE, key, sr);
			// 对要解密的内容进行编码处理
			byte[] cipherByte = c1.doFinal(hex2byte(secuStr));
			return new String(cipherByte);
		} catch (Exception e) {
			e.printStackTrace();
//			log.severe(e.toString());
			return "0";
		}
	}*/
	
	/** 
     * DES加密 
     * @param data 要加密的数据 
     * @param key 密钥 
     * @return 返回加密后的数据(经过base64编码) 
     */  
    public static String DESEncrypt(String data,String key) {  
        return DESCipher(data, key, Cipher.ENCRYPT_MODE);  
    }  
    
    /** 
     * DES解密 
     * @param data 要解密的数据 
     * @param key 密钥 
     * @return 返回解密后的数据 
     */  
    public static String DESDecrypt(String data, String key) {  
        return DESCipher(data, key, Cipher.DECRYPT_MODE);  
    }  
    
    /** 
     * DES的加密解密 
     * @param data 要加密或解密的数据 
     * @param key 密钥 
     * @param mode 加密或解密模式 
     * @return 返回加密或解密的数据 
     */  
    private static String DESCipher(String data, String key, int mode) {  
        try {  
            Key k = toKey(key, ALGORITHM_DES);  
            Cipher cipher = Cipher.getInstance(ALGORITHM_DES);  
            cipher.init(mode, k);  
            final byte[] cipherByte = cipher.doFinal(data.getBytes());
			return byte2hex(cipherByte);
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return "";  
    }  
	
	/**
	 * 创建密匙
	 * 
	 * @param algorithm  加密算法,只能用 [DES,DESede,Blowfish]
	 * @return SecretKey 秘密（对称）密钥
	public static SecretKey createSecretKey(String algorithm) {
		try {
			// KeyGenerator对象,返回生成指定算法的秘密密钥的 KeyGenerator 对象
			final KeyGenerator keygen = KeyGenerator.getInstance(algorithm);
			// 生成一个密钥
			final SecretKey deskey = keygen.generateKey();
			return deskey;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		// 返回密匙
	}*/
	
	/** 
     * 将base64编码后的密钥字符串转换成密钥对象 
     * @param key 密钥字符串 
     * @param algorithm 加密算法 
     * @return 返回密钥对象 
     */  
    private static Key toKey(String key, String algorithm) {  
        SecretKey secretKey = new SecretKeySpec(key.getBytes(), algorithm);  
        return secretKey;  
    }  

	/**
	 * 十六进制字符串转化为2进制
	 * 
	 * @param hex
	 * @return
	 
	public static byte[] hex2byte(String hex) {
		byte[] ret = new byte[8];
		byte[] tmp = hex.getBytes();
		for (int i = 0; i < 8; i++) {
			ret[i] = uniteBytes(tmp[i * 2], tmp[i * 2 + 1]);
		}
		return ret;
	}*/

	/**
	 * 将两个ASCII字符合成一个字节； 如："EF"--> 0xEF
	 * 
	 * @param src0 byte
	 * @param src1 byte
	 * @return byte
	
	public static byte uniteBytes(byte b0, byte b1) {
		byte _b0 = Byte.decode("0x" + new String(new byte[] { b0 })).byteValue();
		_b0 = (byte) (_b0 << 4);
		byte _b1 = Byte.decode("0x" + new String(new byte[] { b1 })).byteValue();
		byte ret = (byte) (_b0 ^ _b1);
		return ret;
	} */
	
	public static void main(String[] args) {
		
//		String desKey = "12345678";
//		String pwd = DESEncrypt("0", desKey);
//		System.out.println(pwd);
//		String pwd2 = DESDecrypt("0CA1F353FAAEE02A", desKey);
//		System.out.println(pwd2);
		
		String tmp = md5encrypt("0");
		System.out.println(tmp);
	}
	
}
