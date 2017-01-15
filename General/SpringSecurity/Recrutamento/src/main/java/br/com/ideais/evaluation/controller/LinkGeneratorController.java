package br.com.ideais.evaluation.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

import br.com.ideais.evaluation.helpers.Email;
import br.com.ideais.evaluation.helpers.Level;
import br.com.ideais.evaluation.model.entity.Candidate;
import br.com.ideais.evaluation.model.entity.Permission;
import br.com.ideais.evaluation.model.service.CandidateService;
import br.com.ideais.evaluation.model.service.EvaluationService;
import br.com.ideais.evaluation.model.service.LinkGeneratorService;
import br.com.ideais.evaluation.model.service.PermissionService;
import br.com.ideais.evaluation.model.service.QuestionService;

public class LinkGeneratorController extends Controller {

	private CandidateService candidateService;
	private EvaluationService evaluationService;
	private PermissionService permissionService;
	private LinkGeneratorService linkGeneratorService;
	private String link;
	private Candidate candidate;
	private JavaMailSender mailSender;
	private VelocityEngine velocityEngine;
	private Email email;
	private Integer linkValidDays;
	private QuestionService questionService;

	public String generate() {
		if ( !hasQuestion ( candidate.getLevel()) ) {
			return FAIL;
		}
		candidateService.save(candidate);
		
		link = linkGeneratorService.getLink(candidate);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, linkValidDays);
		
		mailSender.send(composeMessage(calendar.getTime()));
		
		Permission permission = new Permission();
		permission.setCandidate(candidate);
		permission.setBegin(Calendar.getInstance().getTime());
		permission.setEnd(calendar.getTime());
		permission.setActive(true);
		permissionService.save(permission);
		
		return SUCCESS;
	}

	private boolean hasQuestion(Level level) {
		return (questionService.findByLevel(level).size() > 0 ? true : false );
	}

	private MimeMessagePreparator composeMessage( final Date date ) {
		return new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				Map<String,Object> model = new HashMap<String,Object>();
		        message.setTo(candidate.getEmail());
		        message.setFrom(email.getFrom());
		        message.setSubject(email.getSubject());
		        model.put("candidate", candidate);
		        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		        model.put("endDate", simpleDateFormat.format(date));
		        model.put("link", link);
		        String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "email/templateToCandidate.vm", model);
		        message.setText(text, true);
			}
			
		};
	}

	public EvaluationService getEvaluationService() {
		return evaluationService;
	}

	public void setEvaluationService(EvaluationService evaluationService) {
		this.evaluationService = evaluationService;
	}

	public LinkGeneratorService getLinkGeneratorService() {
		return linkGeneratorService;
	}

	public void setLinkGeneratorService(LinkGeneratorService linkGeneratorService) {
		this.linkGeneratorService = linkGeneratorService;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public CandidateService getCandidateService() {
		return candidateService;
	}

	public void setCandidateService(CandidateService candidateService) {
		this.candidateService = candidateService;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}
	
	public String getLink() {
		return link;
	}

	public void setFrom(String from) {
		this.email.setFrom(from);
	}

	public void setSubjectCandidate(String subjectCandidate) {
		this.email.setSubject(subjectCandidate);
	}
	
	public PermissionService getPermissionService() {
		return permissionService;
	}

	public void setPermissionService(PermissionService permissionService) {
		this.permissionService = permissionService;
	}

	public void setLinkValidDays(Integer linkValidDays) {
		this.linkValidDays = linkValidDays;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}
	
	
}
