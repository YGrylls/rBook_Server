package com.rbook.util;

import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class Encoder {

	private static final byte[] DES_KEY = { 21, 1, -110, 82, -32, -85, -128, -65, 3, -99, 6, -63, 49, 125 };

	public static String encryptBasedDes(String data) {
		String encryptedData = null;
		try {
			SecureRandom sr = new SecureRandom();
			DESKeySpec deskey = new DESKeySpec(DES_KEY);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey key = keyFactory.generateSecret(deskey);
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, key, sr);
			encryptedData = Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
		} catch (Exception e) {
			throw new RuntimeException("Encryption error, error message:", e);
		}
		return encryptedData;
	}

	public static String decryptBasedDes(String cryptData) {
		String decryptedData = null;
		try {
			SecureRandom sr = new SecureRandom();
			DESKeySpec deskey = new DESKeySpec(DES_KEY);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey key = keyFactory.generateSecret(deskey);
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, key, sr);
			decryptedData = new String(cipher.doFinal(Base64.getDecoder().decode(cryptData)));
		} catch (Exception e) {
			throw new RuntimeException("Decrypt error, error message:", e);
		}
		return decryptedData;
	}

}
