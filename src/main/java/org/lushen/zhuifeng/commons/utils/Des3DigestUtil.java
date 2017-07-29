package org.lushen.zhuifeng.commons.utils;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * 3DES加密解密工具类
 * 
 * @author hlm
 */
public final class Des3DigestUtil {
	
	/**
	 * 3DES加密
	 * 
	 * @param secretKey 秘钥
	 * @param iv 偏移向量
	 * @param plainText	普通文本
	 * 
	 * @return String
	 */
	public static final String encryptString(String secretKey,String iv,String plainText) {
		try {
			
			DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
			SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
			Key deskey = keyfactory.generateSecret(spec);
	
			Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
			IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
			cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
			byte[] encryptData = cipher.doFinal(plainText.getBytes("utf-8"));
			
			return Base64DigestUtil.encode(encryptData);
			
		} catch (Exception e) {
			
			throw new RuntimeException(e);
			
		}
	}
	
	/**
	 * URLEncoder编码加密信息
	 * 
	 * @param secretKey
	 * @param iv
	 * @param plainText
	 * 
	 * @return String
	 */
	public static final String encryptStringURLEncoder(String secretKey,String iv,String plainText) {
		try {
			return URLEncoder.encode(encryptString(secretKey, iv, plainText), "utf-8");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 3DES解密
	 * 
	 * @param secretKey 秘钥
	 * @param iv 偏移向量
	 * @param encryptText 密文
	 * 
	 * @return String
	 */
	public static final String decryptString(String secretKey,String iv,String encryptText) {
		try {
			
			DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
			SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
			Key deskey = keyfactory.generateSecret(spec);
			
			Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
			IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
			byte[] decryptData = cipher.doFinal(Base64DigestUtil.decode(encryptText));

			return new String(decryptData, "utf-8");
			
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	/**
	 * 3DES解码后解密
	 * 
	 * @param secretKey 秘钥
	 * @param iv 偏移向量
	 * @param encryptText 密文
	 * 
	 * @return String
	 */
	public static final String decryptStringURLDecoder(String secretKey,String iv,String encryptText) {
		try {
			return decryptString(secretKey, iv, URLDecoder.decode(encryptText, "utf-8"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
