<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %><html>
<head>
<title>Sistema de Provas Online Ideais</title>
<link rel="stylesheet" type="text/css" href="/CandidatesEvaluation/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="/CandidatesEvaluation/css/main.css" />
<link type="text/css" href="/CandidatesEvaluation/css/jquery-ui-1.8.17.custom.css" rel="stylesheet" />
<link type="text/css" href="/CandidatesEvaluation/css/jquery.countdown.css" rel="stylesheet" />	

<script type="text/javascript" src="/CandidatesEvaluation/scripts/jquery_1.7.1.js"></script>
<script type="text/javascript" src="/CandidatesEvaluation/scripts/jquery-ui-1.8.17.custom.min.js"></script>
<script type="text/javascript" src="/CandidatesEvaluation/scripts/bootstrap-dropdown.js"></script>
<script type="text/javascript" src="/CandidatesEvaluation/scripts/bootstrap-alert.js"></script>
<script type="text/javascript" src="/CandidatesEvaluation/scripts/jquery.countdown.js"></script>
<script type="text/javascript" src="/CandidatesEvaluation/scripts/main.js"></script>
<script type="text/javascript">
	var Configs = {
		'baseUrl':'${config.baseUrl}',
		'hour':'${config.evaluationTimerHour}',
		'minute':'${config.evaluationTimerMinute}'
	};
</script>
</head>
<body class="evaluation">
	<div id="clock"></div>
	<div class="container">
		<div class="row">
			<div class="span12">
				<div id="successContainer" class="hide"> 
					<div class="alert alert-block alert-success fade in message">
						<a href="#" class="close">×</a>
						<p></p>
					</div>
				</div>
				<h1>Prova de Avalia&ccedil;&atilde;o de Candidato</h1>
				<hr>
				<form method="post" action="${config.baseUrl}test/saveResult">
					<c:forEach items="${evaluation.questions}" var="question"
						varStatus="index">
						<div class="class-group">${question.description}</div>
						<div class="controls input-xxlarge">
							<table class="table">
								<thead>
									<tr>
										<th class="centered-text option">Op&ccedil;&atilde;o</th>
										<th class="centered-text correct">&nbsp;</th>
										<th class="answer">Texto</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${question.options}" var="option"
										varStatus="optionIndex">
										<c:if test="${option.answer != ''}">
											<tr>
												<td class="centered-text">${optionIndex.count}</td>
												<td class="centered-text"><input type="radio" value="${optionIndex.count-1}"
													name="candidateResponses[${index.count-1}]" />
												</td>
												<td>${option.answer}</td>
											</tr>
										</c:if>
									</c:forEach>
									<input type="hidden" value="${evaluation.candidate.id}" name="evaluation.candidate.id" />
								</tbody>
							</table>
						</div>
					</c:forEach>
					<div class="form-actions">
						<button type="submit" class="btn btn-primary">Salvar</button>
						<button type="reset" class="btn">Limpar</button>
					</div>
				</form>

			</div>
		</div>
	</div>
</body>
</html>
