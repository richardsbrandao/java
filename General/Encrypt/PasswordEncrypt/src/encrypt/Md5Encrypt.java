package encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

public class Md5Encrypt {

	public String encrypt(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update( password.getBytes() );
			byte[] bytes = md.digest();
			return Hex.encodeHexString( bytes );
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

}
