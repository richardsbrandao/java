<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<html>
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
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="span12">
				<h1>Instru&ccedil;&otilde;es para a realiza&ccedil;&atilde;o da prova de avalia&ccedil;&atilde;o de candidato:</h1>
				<hr>
				<p>Prezado candidato(a)</p>
				<p>Evite atualizar a p&aacute;gina com o uso da tecla F5 ou de qualquer outra forma. Caso contr&aacute;rio, sua avalia&ccedil;&atilde;o ser&aacute; anulada.</p>
				<p>O cron&ocirc;metro no canto superior direito da p&aacute;gina indica o tempo restante para a realiza&ccedil;&atilde;o da prova. Caso conclua a avalia&ccedil;&atilde;o antes do tempo definido, clique no botão 'Enviar' no fim da p&aacute;gina.</p>
				<p>Quando o tempo esgotar, a avalia&ccedil;&atilde;o ser&aacute; enviada automaticamente. Qualquer quest&atilde;o n&atilde;o respondida ser&aacute; considerada incorreta.</p>
				<p>Para o bom funcionamento do sistema, não desabilite o Javascript.</p>
				<p>Ao clicar no bot&atilde;o "Ir para Prova", a avalia&ccedil;&atilde;o ser&aacute; iniciada, e deve ser realizada segundo as normas descritas. Boa prova!</p>
				<button class="btn btn-primary" onclick="redirect('${evaluationUrl}')">Ir para prova</button>
			</div>
		</div>
	</div>
</body>
</html>