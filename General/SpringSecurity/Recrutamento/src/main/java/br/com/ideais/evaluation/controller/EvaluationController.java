package br.com.ideais.evaluation.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.struts2.interceptor.SessionAware;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

import br.com.ideais.evaluation.helpers.Email;
import br.com.ideais.evaluation.helpers.Filter;
import br.com.ideais.evaluation.model.entity.Candidate;
import br.com.ideais.evaluation.model.entity.Evaluation;
import br.com.ideais.evaluation.model.entity.Permission;
import br.com.ideais.evaluation.model.entity.Question;
import br.com.ideais.evaluation.model.service.CandidateService;
import br.com.ideais.evaluation.model.service.EvaluationService;
import br.com.ideais.evaluation.model.service.PermissionService;

public class EvaluationController extends Controller implements SessionAware {
	
	private EvaluationService evaluationService;
	private CandidateService candidateService;
	private PermissionService permissionService;
	protected Map<String, Object> session;
	
	private JavaMailSender mailSender;
	private VelocityEngine velocityEngine;
	private Email email;
	
	private Candidate candidate;
	private Evaluation evaluation;
	private List<Question> questions;
	
	private List<Integer> candidateResponses = new ArrayList<Integer>();
	private List<Evaluation> evaluations;
	
	private String evaluationUrl;
	private Filter filter;

	public String introduction() {
		evaluationUrl = getConfig().getBaseUrl() + "test/doEvaluation/" + candidate.getId() + "/";

		return SUCCESS;
	}
	
	public String doEvaluation(	) {
		candidate = candidateService.findById(candidate);
		Permission permission = permissionService.findByCandidate(candidate);
		if(isPermissionInvalid(permission)) {
			return FAIL;
		}
		permission.setActive(false);
		permissionService.update(permission);
		evaluation = evaluationService.generateEvaluation(candidate);
		
		this.getSession().put("evaluation", evaluation);
		return SUCCESS;
	}
	
	public String saveResult() {
		evaluation = (Evaluation) this.getSession().get("evaluation");
		if( isNotSavingWithinTimeLimit() ) {
			return FAIL;
		}
		setCandidateResponses();
		evaluation.calculateResult();
		evaluationService.save(evaluation);
		mailSender.send(composeMessage());
		return SUCCESS;
	}

	public String viewEvaluations() {
		evaluations = evaluationService.generateReport(filter);
		return SUCCESS;
	}

	private MimeMessagePreparator composeMessage() {
		return new MimeMessagePreparator() {

			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				Map<String,Object> model = new HashMap<String,Object>();
		        message.setTo(email.getTo());
		        message.setFrom(email.getFrom());
		        message.setSubject(email.getSubject());
		        model.put("evaluation", evaluation);
		        String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "email/templateToAppraiser.vm", model);
		        message.setText(text, true);
			}
			
		};
	}
	
	private Boolean isNotSavingWithinTimeLimit() {
		return evaluation.getEndDate().before(new Date());
	}

	private void setCandidateResponses() {
		for(int i = 0; i < candidateResponses.size(); i++) {
			evaluation.setCandidateResponse(i, candidateResponses.get(i));
		}
	}

	private Boolean isPermissionInvalid(Permission permission) {
		return permission == null || isDateWithinTimeLimit(permission);
	}

	private Boolean isDateWithinTimeLimit(Permission permission) {
		return Calendar.getInstance().getTime().after(permission.getEnd()) || Calendar.getInstance().getTime().before(permission.getBegin());
	}


	public Evaluation getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(Evaluation evaluation) {
		this.evaluation = evaluation;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public List<Integer> getCandidateResponses() {
		return candidateResponses;
	}

	public void setCandidateResponses(List<Integer> candidateResponses) {
		this.candidateResponses = candidateResponses;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public void setEvaluationService(EvaluationService evaluationService) {
		this.evaluationService = evaluationService;
	}

	public void setCandidateService(CandidateService candidateService) {
		this.candidateService = candidateService;
	}

	public String getEvaluationUrl() {
		return evaluationUrl;
	}

	public List<Evaluation> getEvaluations() {
		return evaluations;
	}

	public void setEvaluations(List<Evaluation> evaluations) {
		this.evaluations = evaluations;
	}
	
	public Date getCurrentDate() {
		return new Date();
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setPermissionService(PermissionService permissionService) {
		this.permissionService = permissionService;
	}
	
	public void setToAppraiser(String toAppraiser) {
		this.email.setTo(toAppraiser);
	}
	
	public void setSubjectResult(String subjectResult) {
		this.email.setSubject(subjectResult);
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	public Filter getFilter() {
		return filter;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

}