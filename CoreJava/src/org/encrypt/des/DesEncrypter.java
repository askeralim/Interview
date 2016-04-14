package org.encrypt.des;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class DesEncrypter {
	private static Cipher cipher;
	private final String pasKey = "Test12**";
	private static DesEncrypter desEncrypter;
	
	private DesEncrypter() throws Exception{
		byte[] keyBytes = pasKey.getBytes();
		SecretKeySpec skeySpec = new SecretKeySpec(keyBytes, "DES");
        cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
	}
	
	public static DesEncrypter getInstance() throws Exception {
		if (desEncrypter == null) {
			desEncrypter = new DesEncrypter();
		}
		return desEncrypter;
	}
	
	public static String encrypt(String data) throws Exception{
		byte[] inBytes = data.getBytes();
        byte[] encrypted = cipher.doFinal(inBytes);
        return toHex(encrypted);
	}
	
	private static String toHex(byte buf[]) {
		StringBuffer strbuf = new StringBuffer(buf.length * 2);
		int i;
		for (i = 0; i < buf.length; i++) {
			if (((int) buf[i] & 0xff) < 0x10)
				strbuf.append("0");

			strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
		}
		return strbuf.toString();
	}
}
