package string;

public class Urlify {

	public char[] get(char[] string) {
		int spaces = countSpaces(string);
		char[] response = new char[spaces*2 + string.length];
		for(int i = 0, j = 0; i < string.length; i++) {
			if(string[i] != ' ') {
				response[j++] = string[i];
			} else {
				response[j++] = '%';
				response[j++] = '2';
				response[j++] = '0';
			}
		}
		return response;
	}

	private int countSpaces(char[] string) {
		int j = 0;
		for(int i = 0; i < string.length; i++) {
			if(string[i] == ' ') {
				j++;
			}
		}
		return j;
	}

}
