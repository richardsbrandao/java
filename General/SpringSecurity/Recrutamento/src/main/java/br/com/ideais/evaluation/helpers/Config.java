package br.com.ideais.evaluation.helpers;

public class Config {
	private String baseUrl;
	private Integer evaluationTimerHour;
	private Integer evaluationTimerMinute;

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public Integer getEvaluationTimerHour() {
		return evaluationTimerHour;
	}

	public void setEvaluationTimerHour(Integer evaluationTimerHour) {
		this.evaluationTimerHour = evaluationTimerHour;
	}

	public Integer getEvaluationTimerMinute() {
		return evaluationTimerMinute;
	}

	public void setEvaluationTimerMinute(Integer evaluationTimerMinute) {
		this.evaluationTimerMinute = evaluationTimerMinute;
	}
		
}
