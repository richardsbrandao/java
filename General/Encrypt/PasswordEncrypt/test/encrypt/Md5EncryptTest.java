package encrypt;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class Md5EncryptTest {

	@Test
	public void test_encrypt() {
		Md5Encrypt md5Encrypt = new Md5Encrypt();
		
		String encryptPassword = md5Encrypt.encrypt("123456");
		
		assertEquals( "e10adc3949ba59abbe56e057f20f883e", encryptPassword );
	}
	
}
