package algorithm.stack;

import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class MatchingExpression {

	private String expression;
	private Map<Character, Character> entries;
	private Stack<Character> match;

	@SuppressWarnings("serial")
	public MatchingExpression(String expression) {
		this.expression = expression;
		this.entries = new HashMap<Character, Character>() { {
			put('(', ')');
			put('[', ']');
			put('{', '}');
		} };
		this.match = new Stack<Character>();
	}

	public Boolean evaluate() {
		try {
			for(int i = 0; i < expression.length(); i++) {
				Character character = expression.charAt(i);
				if( entries.keySet().contains(character) ) {
					match.push(character);
				}
				if( entries.values().contains(character) ) {
					Character startCharacter = match.pop();
					if( ! entries.get(startCharacter).equals(character) ) {
						return false;
					}
				}
			}
			return match.isEmpty();
		} catch(EmptyStackException e) {
			return false;
		}
	}

}
