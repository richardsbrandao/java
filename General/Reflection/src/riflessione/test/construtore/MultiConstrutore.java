package riflessione.test.construtore;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class MultiConstrutore {

	public MultiConstrutore(String s) {
		System.out.println(s);
	}
	
	public MultiConstrutore(String s, Integer i) {
		System.out.println(s);
		System.out.println(i);
	}
	
	public static void main(String[] args) {
		
		try {
			Constructor<MultiConstrutore> constructor = MultiConstrutore.class.getConstructor(String.class);
			constructor.newInstance("Una parole");
			
			System.out.println( "Construtores: " + MultiConstrutore.class.getConstructors().length );
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
	}
	
}
