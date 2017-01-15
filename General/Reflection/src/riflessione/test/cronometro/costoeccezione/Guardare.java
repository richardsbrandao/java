package riflessione.test.cronometro.costoeccezione;

import java.lang.reflect.Method;

import riflessione.studi.cronometro.Cronometro;
import riflessione.studi.cronometro.CronometroUnita;
import riflessione.studi.cronometro.TipoLog;
import riflessione.test.cronometro.costoeccezione.supporto.OggettoDiErrore;

public class Guardare {

	public static void main(String[] args) throws Exception {
		
		//quando aprender sobre AOP voltar
		// fazer exercicio de custo de relexão
		
		cronometroDiEccezione();
		cronometroDiRiflessione();
	}

	private static void cronometroDiRiflessione() throws Exception {
		Guardare guardare = new Guardare();
		int j = 100000;
		
		long sN = System.currentTimeMillis();
		guardare.chiamataNormale(j);
		long fN = System.currentTimeMillis();
		
		long sSC = System.currentTimeMillis();
		guardare.chiamateRiflessioneSenzaCache(j);
		long fSC = System.currentTimeMillis();
		
		long sCC = System.currentTimeMillis();
		guardare.chiamateRiflessioneConCache(j);
		long fCC = System.currentTimeMillis();
		
		System.out.println( fN - sN + " -- Normal");
		System.out.println( fSC - sSC + " -- Sem Cache");
		System.out.println( fCC - sCC + " -- Com Cache" );
	}

	private static void cronometroDiEccezione() {
		Guardare guardare = new Guardare();
		
		long sO = System.currentTimeMillis();
		guardare.oggetto(100000);
		long fO = System.currentTimeMillis();
		
		long sE = System.currentTimeMillis();
		guardare.eccezione(100000);
		long fE = System.currentTimeMillis();
		
		System.out.println( fO - sO + " -- Objeto");
		System.out.println( fE - sE + " -- Exceção" );
		
		
		
		sO = System.currentTimeMillis();
		guardare.oggetto(1000000);
		fO = System.currentTimeMillis();
		
		sE = System.currentTimeMillis();
		guardare.eccezione(1000000);
		fE = System.currentTimeMillis();

		System.out.println( fO - sO + " -- Objeto");
		System.out.println( fE - sE + " -- Exceção" );
	}

	@Cronometro(unita=CronometroUnita.MILLISECONDI, log=TipoLog.INFO)
	private void oggetto(int j) {
		for( int i = 0; i < j; j++ ) {
			if(i > 0) {
				new OggettoDiErrore("chiave", "valore");
			}
		}
	}

	@Cronometro(unita=CronometroUnita.MILLISECONDI, log=TipoLog.ERROR)
	private void eccezione(int j) {
		for( int i = 0; i < j; j++ ) {
			try {
				if(i > 0) {
					throw new Exception("chiave");
				}
			} catch(Exception e) { 	}
		}
	}
	
	private void chiamataNormale(int j) {
		ClasseDiTeste istanza = new ClasseDiTeste();
		
		for( int i = 0; i < j; i++ ) {
			istanza.invoke();
		}
	}
	
	private void chiamateRiflessioneConCache(int j) throws Exception {
		ClasseDiTeste istanza = new ClasseDiTeste();
		Method method = istanza.getClass().getMethod("invoke");
		for( int i = 0; i < j; i++ ) {
			method.invoke(istanza);
		}
	}
	
	private void chiamateRiflessioneSenzaCache(int j) throws Exception {
		ClasseDiTeste istanza = new ClasseDiTeste();
		for( int i = 0; i < j; i++ ) {
			Method method = istanza.getClass().getMethod("invoke");
			method.invoke(istanza);
		}
	}
	
	private class ClasseDiTeste {
		public void invoke() { }
	}
	
}
