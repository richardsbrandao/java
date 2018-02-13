package algorithm.cases.numbers;

import static org.junit.Assert.*;

import org.junit.Test;

public class NumberStringMaskTest {

	@Test
	public void when_mask_with_only_letters_must_return_empty_string() {
		assertEquals("", new NumberStringMask("asd").mask());
	}
	
	@Test
	public void when_mask_with_only_one_number_must_return_a_string_with_one_number() {
		assertEquals("1", new NumberStringMask("1").mask());
	}
	
	@Test
	public void when_mask_with_only_one_number_and_any_strings_must_return_a_string_with_one_number() {
		assertEquals("1", new NumberStringMask("(1)").mask());
	}
	
	@Test
	public void when_mask_with_two_numbers_must_return_one_string_with_two_numbers() {
		assertEquals("21", new NumberStringMask("21").mask());
	}
	
	@Test
	public void when_mask_with_two_numbers_and_any_strings_must_return_a_string_with_two_numbers() {
		assertEquals("21", new NumberStringMask("2,1").mask());
	}
	
	@Test
	public void when_mask_with_three_numbers_must_return_one_string_with_three_numbers() {
		assertEquals("321", new NumberStringMask("321").mask());
	}
	
	@Test
	public void when_mask_with_three_numbers_and_any_strings_must_return_a_string_with_three_numbers() {
		assertEquals("321", new NumberStringMask("3sd2b1bb").mask());
	}
	
	@Test
	public void when_mask_with_four_numbers_must_return_one_string_with_2_and_2_numbers_separating_by_hiffen() {
		assertEquals("43-21", new NumberStringMask("4321").mask());
	}
	
	@Test
	public void when_mask_with_four_numbers_and_any_strings_must_return_a_string_with_2_and_2_numbers_separating_by_hiffen() {
		assertEquals("43-21", new NumberStringMask("432;(1").mask());
	}
	
	@Test
	public void when_mask_with_five_numbers_must_return_one_string_with_3_and_2_numbers_separating_by_hiffen() {
		assertEquals("543-21", new NumberStringMask("54321").mask());
	}
	
	@Test
	public void when_mask_with_five_numbers_and_any_strings_must_return_a_string_with_3_and_2_numbers_separating_by_hiffen() {
		assertEquals("543-21", new NumberStringMask("5   43s21").mask());
	}
	
	@Test
	public void when_mask_with_six_numbers_must_return_one_string_with_3_and_3_numbers_separating_by_hiffen() {
		assertEquals("654-321", new NumberStringMask("654321").mask());
	}
	
	@Test
	public void when_mask_with_six_numbers_and_any_strings_must_return_a_string_with_3_and_3_numbers_separating_by_hiffen() {
		assertEquals("654-321", new NumberStringMask("6Ç54a3c21").mask());
	}
	
	@Test
	public void when_mask_with_seven_numbers_must_return_one_string_with_3_2_2_numbers_separating_by_hiffen() {
		assertEquals("765-43-21", new NumberStringMask("7654321").mask());
	}
	
	@Test
	public void when_mask_with_seven_numbers_and_any_strings_must_return_a_string_with_3_2_2_numbers_separating_by_hiffen() {
		assertEquals("765-43-21", new NumberStringMask("7654C321c").mask());
	}
	
	@Test
	public void when_mask_with_eight_numbers_must_return_one_string_with_3_3_2_numbers_separating_by_hiffen() {
		assertEquals("876-543-21", new NumberStringMask("87654321").mask());
	}
	
	@Test
	public void when_mask_with_eight_numbers_and_any_strings_must_return_a_string_with_3_3_2_numbers_separating_by_hiffen() {
		assertEquals("876-543-21", new NumberStringMask("a876m543/21").mask());
	}
	
	@Test
	public void when_mask_with_nine_numbers_must_return_one_string_with_3_3_3_numbers_separating_by_hiffen() {
		assertEquals("987-654-321", new NumberStringMask("987654321").mask());
	}
	
	@Test
	public void when_mask_with_nine_numbers_and_any_strings_must_return_a_string_with_3_3_3_numbers_separating_by_hiffen() {
		assertEquals("987-654-321", new NumberStringMask("987sd6g5r*-4321").mask());
	}
}
