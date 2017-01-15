package riflessione.studi.validatore.annotazione;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value={ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface StringMatcher {
	
	String modello() default "";
	int lunghezza() default -1;
	
}
