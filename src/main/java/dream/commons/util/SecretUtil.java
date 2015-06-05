package dream.commons.util;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class SecretUtil {

	/**
	 * 
	 * ClassName：Algorithm Description: 加密算法
	 * 
	 * @author zhujian
	 * @param args
	 * @date 2015年1月13日 下午3:34:04
	 * @version
	 */
	private enum Algorithm {

		MD5("MD5"), // 常规的MD5算法
		SHA1("SHA1"), // 常规的SHA1算法
		HMACMD5("HMACMD5"), // 加随机码的哈希MD5算法
		HMACSHA1("HMACSHA1") // 加随机码的哈希SHA1算法
		;

		private Algorithm(String value) {
			this.value = value;
		}

		private String value;

		public String getValue() {
			return value;
		}

	}

	/**
	 * 
	 * @Title: validateNotNull
	 * @Description: 判断字符串不为null或""或空格字符串，若真，则抛出非法参数异常
	 * @param text
	 *            字符串
	 *
	 */
	private static void validateNotNull(String text) {
		if (isEmpty(text)) {
			throw new IllegalArgumentException("text is null");
		}
	}

	/**
	 * 
	 * @Title: isEmpty
	 * @Description: 判断字符串不为null或""或空格字符串
	 * @param text
	 *            字符串
	 * @return
	 *
	 */
	private static boolean isEmpty(String text) {
		boolean isEmpty = false;
		if (text == null || (text = text.trim()).isEmpty()) {
			isEmpty = true;
		}

		return isEmpty;
	}

	/**
	 * 
	 * @Title: getMd5
	 * @Description: 获取MD5
	 * @param text
	 *            需要转为为MD5的字符串
	 * @return
	 *
	 */
	public static String getMD5(String text) {
		return getMD(text, Algorithm.MD5.getValue());
	}

	/**
	 * 
	 * @Title: getSHA1
	 * @Description: 获取SHA1
	 * @param text
	 *            需要转为为SHA1的字符串
	 * @return
	 *
	 */
	public static String getSHA1(String text) {
		return getMD(text, Algorithm.SHA1.getValue());
	}

	/**
	 * 
	 * @Title: getMD
	 * @Description: 常规的加密算法（不加随机数）
	 * @param text
	 *            需要加密的字符串
	 * @param algorithm
	 *            具体的算法。如"md5","sha1"
	 * @return
	 *
	 */
	private static String getMD(String text, String algorithm) {
		validateNotNull(text);
		validateNotNull(algorithm);
		String md = null;
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("sha1");
			messageDigest.update(text.getBytes());
			byte targetByte[] = messageDigest.digest();
			md = targetByte2String(targetByte);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return md;
	}

	/**
	 * 
	 * @Title: getHmacmd5
	 * @Description: 加随机数的MD5算法
	 * @param text
	 *            需要加密的字符串
	 * @param salt
	 *            随机数
	 * @return
	 *
	 */
	public static String getHmacMD5(String text, String salt) {
		return getHmac(text, salt, Algorithm.HMACMD5.getValue());
	}

	/**
	 * 
	 * @Title: getHmacSHA1
	 * @Description: 加随机数的SHA1算法
	 * @param text
	 *            需要加密的字符串
	 * @param salt
	 *            随机数
	 * @return
	 *
	 */
	public static String getHmacSHA1(String text, String salt) {
		return getHmac(text, salt, Algorithm.HMACSHA1.getValue());
	}

	/**
	 * 
	 * @Title: getHmac
	 * @Description: 加入随机数的哈希加密算法
	 * @param text
	 *            需要加密的字符串
	 * @param salt
	 *            随机数
	 * @param algorithm
	 *            具体的算法。如"hmacmd5","hmacsha1"
	 * @return
	 *
	 */
	private static String getHmac(String text, String salt, String algorithm) {
		String hmac = null;
		validateNotNull(text);
		validateNotNull(salt);
		validateNotNull(algorithm);
		try {
			Mac mac = Mac.getInstance(algorithm);
			SecretKey sk = new SecretKeySpec(salt.getBytes(), algorithm);
			mac.init(sk);
			mac.update(text.getBytes());
			byte targetByte[] = mac.doFinal();
			hmac = targetByte2String(targetByte);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}

		return hmac;
	}

	/**
	 * 
	 * @Title: encryptAES
	 * @Description: 给字符串进行AES对称加密
	 * @param text
	 *            需要加密的字符串
	 * @param salt
	 *            密钥
	 * @return
	 *
	 */
	public static String encryptAES(String text, String salt) {
		String result = null;
		validateNotNull(text);
		validateNotNull(salt);
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(salt.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			byte[] byteContent = text.getBytes();
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			result = targetByte2String(cipher.doFinal(byteContent));
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 
	 * @Title: decryptAES
	 * @Description: 进行AES解密
	 * @param content
	 * @param salt
	 * @return
	 *
	 */
	public static String decryptAES(String content, String salt) {
		String result = null;
		validateNotNull(content);
		validateNotNull(salt);
		try {
			
			content = content.toUpperCase();
			int length = content.length() / 2;
			char[] hexChars = content.toCharArray();
			byte[] targetBytes = new byte[length];
			for (int i = 0; i < length; i++) {
				int pos = i * 2;
				targetBytes[i] = (byte) ("0123456789ABCDEF".indexOf(hexChars[pos]) << 4 | "0123456789ABCDEF".indexOf(hexChars[pos + 1]));
			}
			
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(salt.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
			byte[] resultByte = cipher.doFinal(targetBytes);
			result = new String(resultByte);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return result;
	}


	/**
	 * 
	 * @Title: targetByte2String
	 * @Description: 把成功加密后得到的字节数组转换成字符串
	 * @param targetByte
	 * @return
	 *
	 */
	private static String targetByte2String(byte[] targetByte) {
		StringBuilder sb = new StringBuilder();
		for (byte b : targetByte) {
			sb.append(Integer.toString(b >>> 4 & 0xF, 16)).append(
					Integer.toString(b & 0xF, 16));
		}
		return sb.toString().toLowerCase();
	}

	public static void main(String[] args) {
		String text = "123456";
		String salt = "521c46fc-e8f8-439e-bca4-08908fe4f06a";
		
		/*String text = "what do ya want for nothing?";
		String salt = "521c46fc-e8f8-439e-bca4-08908fe4f06a";*/
		text = getSHA1(text);
		System.out.println(text);
		System.out.println(getHmacSHA1(text, salt));
	}
}
