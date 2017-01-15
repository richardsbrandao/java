package encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

import org.apache.commons.codec.binary.Hex;

public class Md5SaltEncrypt {

	public String getSalt() {
		SecureRandom sr;
		try {
			sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
			byte[] salt = new byte[16];
			sr.nextBytes(salt);
			return salt.toString();
		} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String encrypt(String password, String salt) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update( salt.getBytes() );
			byte[] digest = md.digest( password.getBytes() );
			return Hex.encodeHexString( digest );
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
