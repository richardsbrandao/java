package br.com.ideais.evaluation.helpers;

public enum Level {
	
	ESTAGIARIO("ESTAGIARIO"), JUNIOR("JUNIOR"), PLENO("PLENO"), SENIOR("SENIOR");
	
	private String level;
	
	Level(String level) {
		this.level = level;
	}
	
	public String getLevel() {
		return level;
	}
	
}
