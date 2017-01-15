package encrypt;

import static org.junit.Assert.*;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class Md5SaltTest {

	@Test
	public void test_salt() {
		Md5SaltEncrypt md5 = new Md5SaltEncrypt();
		String salt1 = md5.getSalt();
		String salt2 = md5.getSalt();
		
		assert( !salt1.equals(salt2) );
	}
	
	@Test
	public void test_md5_with_salt_different() {
		Md5SaltEncrypt md5_salt = new Md5SaltEncrypt();
		assertEquals( "423e2457ac72c165edba4a4ef2e9959a", md5_salt.encrypt("123456", "SALT") );
		
		Md5Encrypt md5 = new Md5Encrypt();
		assertEquals( "e10adc3949ba59abbe56e057f20f883e", md5.encrypt("123456") );
		

		assert( ! md5.encrypt("123456").equals( md5_salt.encrypt("123456", "SALT") ) );
	}
	
	@Test
	public void test_md5_and_digest_libary() {
		Md5Encrypt md5 = new Md5Encrypt();
		assertEquals( DigestUtils.md5Hex( "123456".getBytes() ), md5.encrypt("123456") );
	}

	
}
