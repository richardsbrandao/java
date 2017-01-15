package riflessione.studi.validatore.annotazione;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Gamma {
	
	int minimo() default Integer.MIN_VALUE;
	int massimo() default Integer.MAX_VALUE;
	
}
