<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
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
			<c:if test="${param.auth == 'error'}">
				<div id="errorContainer"> 
					<div class="alert alert-block alert-error fade in message">
						<a href="#" class="close">×</a>
						<p>Usuario ou senha inválidas!</p>
					</div>
				</div>
			</c:if>
			<h1>Login</h1>
			<form class="form-horizontal" method="post" action="/CandidatesEvaluation/j_spring_security_check">
				<fieldset>
					<legend>Formul&aacute;rio de Login </legend>
					<div class="control-group">
	            		<label for="userName" class="control-label">Usu&aacute;rio</label>
	            		<div class="controls input-xlarge">
	                		<input type="text" name="j_username" id="userName" class="input-xlarge focused" />
	            		</div>
	       			</div>
	       			<div class="control-group">
	            		<label for="userPassword" class="control-label">Senha</label>
	            		<div class="controls input-xlarge">
	                		<input type="password" name="j_password" id="userPassword" class="input-xlarge focused" />
	            		</div>
	       			</div>
					<div class="form-actions">
			            <button type="submit" class="btn btn-primary">Logar</button>
			        </div>
		        </fieldset>
			</form>
		</div>
	</div>
</div>
</body>
</html>
