package br.com.ideais.evaluation.helpers;

public enum QuestionStatus {
	ACTIVE("ACTIVE"), INACTIVE("INACTIVE"), DELETED("DELETED");
	private String status;
	
	QuestionStatus(String status) {
		this.status=status; 
	}

	public String getStatus() {
		return status;
	}
}
