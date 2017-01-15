package riflessione.test.gerarchia;

import java.util.Scanner;

import riflessione.studi.gerarchia.Gerarchia;

public class InformazioniSullaClasse {

	public static void main(String[] args) {
		System.out.println("Nome della classe che vorrai informazioni: ");
		Scanner scanner = new Scanner(System.in);
		String nomeDellaClasse = scanner.nextLine();
		Gerarchia gerarchia = new Gerarchia();
		
		try {
			Class<?> classe = gerarchia.importazione(nomeDellaClasse);
			gerarchia.stampa(classe, 0);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		scanner.close();
	}
	
}
