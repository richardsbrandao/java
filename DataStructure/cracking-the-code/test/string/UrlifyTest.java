package string;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Urlify")
public class UrlifyTest {

	private Urlify urlify = new Urlify();
	
	@Test
	@DisplayName("when urlify with string without space must return same")
	public void case_1() {
		char[] string = {'r', 'i', 'c', 'h', 'a', 'r', 'd'};
		char[] urlifyString = urlify.get(string);
		
		assertAll("all",
				() -> assertEquals(7, urlifyString.length),
				() -> assertEquals('r', urlifyString[0]), 
				() -> assertEquals('i', urlifyString[1]),
				() -> assertEquals('c', urlifyString[2]), 
				() -> assertEquals('h', urlifyString[3]), 
				() -> assertEquals('a', urlifyString[4]), 
				() -> assertEquals('r', urlifyString[5]), 
				() -> assertEquals('d', urlifyString[6])
		);
	}
	
	@Test
	@DisplayName("when urlify with string with one space must return a string %20 instead spaces")
	public void case_2() {
		char[] string = {'r', 'i', 'c', ' ', 'h', 'a', 'r', 'd'};
		char[] urlifyString = urlify.get(string);
		
		assertAll("all",
				() -> assertEquals(10, urlifyString.length),
				() -> assertEquals('r', urlifyString[0]), 
				() -> assertEquals('i', urlifyString[1]),
				() -> assertEquals('c', urlifyString[2]), 
				() -> assertEquals('%', urlifyString[3]), 
				() -> assertEquals('2', urlifyString[4]), 
				() -> assertEquals('0', urlifyString[5]), 
				() -> assertEquals('h', urlifyString[6]), 
				() -> assertEquals('a', urlifyString[7]), 
				() -> assertEquals('r', urlifyString[8]), 
				() -> assertEquals('d', urlifyString[9])
		);
	}

	@Test
	@DisplayName("when urlify with string with more than one space must return a string urlified ")
	public void case_3() {
		char[] string = {' ', 'r', 'i', 'c', ' ', 'h', 'a', 'r', 'd'};
		char[] urlifyString = urlify.get(string);
		
		assertAll("all",
				() -> assertEquals(13, urlifyString.length),
				() -> assertEquals('%', urlifyString[0]),
				() -> assertEquals('2', urlifyString[1]),
				() -> assertEquals('0', urlifyString[2]),
				() -> assertEquals('r', urlifyString[3]), 
				() -> assertEquals('i', urlifyString[4]),
				() -> assertEquals('c', urlifyString[5]), 
				() -> assertEquals('%', urlifyString[6]), 
				() -> assertEquals('2', urlifyString[7]), 
				() -> assertEquals('0', urlifyString[8]), 
				() -> assertEquals('h', urlifyString[9]), 
				() -> assertEquals('a', urlifyString[10]), 
				() -> assertEquals('r', urlifyString[11]), 
				() -> assertEquals('d', urlifyString[12])
		);
	}
	
}
