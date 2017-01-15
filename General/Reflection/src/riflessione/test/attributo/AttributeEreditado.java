package riflessione.test.attributo;

import java.lang.reflect.Field;

import riflessione.studi.attributo.SavingAccount;

public class AttributeEreditado {

	public static void main(String[] args) {
		System.out.println("SavingAccount.getDeclaredFields: ");
		for( Field campo : SavingAccount.class.getDeclaredFields() ) {
			System.out.println( campo.getType().getSimpleName() + ": " + campo.getName() );
		}
		
		System.out.println("SavingAccount.getFields: ");
		for( Field campo : SavingAccount.class.getFields() ) {
			System.out.println( campo.getType().getSimpleName() + ": " + campo.getName() );
		}
		
	}
	
}
