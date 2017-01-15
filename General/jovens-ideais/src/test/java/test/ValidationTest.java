package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;
import com.parking.helper.Validation;

@RunWith(JUnit4.class)
public class ValidationTest {

	@Test
	public void testCpfWithDiferentThenElevenCharacter() {
		assertFalse(Validation.cpf("1234-567890"));
		assertFalse(Validation.cpf("1234.567890"));
		assertFalse(Validation.cpf("1234567890"));
		assertFalse(Validation.cpf("123456789087687"));
	}

	@Test
	public void testCpfWithOnlyNumberCharacter() {
		assertFalse(Validation.cpf("1234567890a"));
	}
	
	@Test
	public void testIfRepeatedNumbersAreValid() {
		assertFalse(Validation.cpf("11111111111"));
		assertFalse(Validation.cpf("22222222222"));
		assertFalse(Validation.cpf("33333333333"));
		assertFalse(Validation.cpf("44444444444"));
		assertFalse(Validation.cpf("55555555555"));
		assertFalse(Validation.cpf("66666666666"));
		assertFalse(Validation.cpf("77777777777"));
		assertFalse(Validation.cpf("88888888888"));
		assertFalse(Validation.cpf("99999999999"));
		assertFalse(Validation.cpf("00000000000"));
	}
	
	@Test
	public void testWithTotalInvalidCpf() {
		assertFalse(Validation.cpf("17849667176"));
		assertFalse(Validation.cpf("13964994788"));
		assertFalse(Validation.cpf("14764662994"));
	}
	
	@Test
	public void testWithInvalidFirstCheckerDigit() {
		assertFalse(Validation.cpf("17849667123"));
		assertFalse(Validation.cpf("13964994790"));
		assertFalse(Validation.cpf("14764662979"));
	}
	
	@Test
	public void testWithInvalidSecondCheckerDigit() {
		assertFalse(Validation.cpf("17849667116"));
		assertFalse(Validation.cpf("13964994743"));
		assertFalse(Validation.cpf("14764662993"));
	}

	@Test
	public void testWithValidCpf() {
		assertTrue(Validation.cpf("17849667115"));
		assertTrue(Validation.cpf("13964994740"));
		assertTrue(Validation.cpf("14764662973"));
		assertTrue(Validation.cpf("11253950792"));
	}
	
	@Test
	public void testWithValidCpfWithMask() {
		assertTrue(Validation.cpf("178.496.671-15"));
		assertTrue(Validation.cpf("139.649.947-40"));
		assertTrue(Validation.cpf("147.646.629-73"));
		assertTrue(Validation.cpf("130.551.177-86"));
	}
	
	@Test
	public void testPlateValidation() {
		assertTrue(Validation.plate("ASD-7932"));
		assertTrue(Validation.plate("IEU-0392"));
		assertFalse(Validation.plate("3JF-8FJ3"));
		assertFalse(Validation.plate("KIO39-OW"));
	}
	
	@Test
	public void testTelephoneValidation() {
		assertTrue(Validation.telephone("9382-8473"));
		assertTrue(Validation.telephone("83728327"));
		assertFalse(Validation.telephone("39284789234423"));
		assertFalse(Validation.telephone("jhf234324jhg"));
		assertFalse(Validation.telephone("j3224323"));
		assertFalse(Validation.telephone("8s7w-2382"));
	}
	
	@Test
	public void testOnlyNumber() {
		assertTrue(Validation.isNumber("8327"));
		assertFalse(Validation.isNumber("8323d7"));
	}
}
