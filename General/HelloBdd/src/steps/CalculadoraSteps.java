package steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import static org.junit.Assert.*;
import domain.Calculadora;


public class CalculadoraSteps {

	private Calculadora domain = new Calculadora();
	private int resultado;
	
	private int valor1;
	
	@Given("Eu possuo uma calculadora")
	public void possuiUmaCalculadora() {
		assertTrue( Calculadora.class.isInstance(domain) );
	}
	
	@When("somar 2 com 3")
	public void somar() {
		resultado = domain.somar(2, 3);
	}
	
	@Then("recebo como resultado 5")
	public void resultado() {
		assertEquals( 5, resultado );
	}
	
	
	@Given("Eu digito o valor $valor na calculadora")
	public void digitarValorNaCalc(int valor) {
		assertNotNull(valor);
		valor1 = valor;
	}
	
	@When("Eu multiplicar por $fator")
	public void euMultiplicarPorFator(@Named("fator") int fator) {
		assertNotNull(fator);
		assert( fator > 0 );
		resultado = valor1 * fator;
	}
	
	@Then("recebo como resultado da multiplicacao o valor $produto")
	public void assertivaDoProduto(int produto) {
		assertEquals( resultado, produto );
	}
	
}
