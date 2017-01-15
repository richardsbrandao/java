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
<body class="admin">
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container">
				<div class="logo"><img src="images/IdeaisLogo.png"></div>
				<div class="menu">
					<ul class="nav">
						<li class="active"><a href="/CandidatesEvaluation/home">Home</a></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"> Questão <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="/CandidatesEvaluation/QuestionRegistry">Cadastrar</a></li>
								<li><a href="/CandidatesEvaluation/QuestionList">Visualizar</a></li>
							</ul>
						</li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown"> Provas <b class="caret"></b> </a>
							<ul class="dropdown-menu">
								<li><a href="/CandidatesEvaluation/linkGenerator">Nova Prova</a></li>
							</ul>
						</li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown"> Relat&oacute;rios <b class="caret"></b> </a>
							<ul class="dropdown-menu">
								<li><a href="/CandidatesEvaluation/reportGenerator">Relat&oacute;rio de Avalia&ccedil;&atilde;o</a></li>
							</ul>
						</li>
					</ul>
				</div>
				<div class="logout">
					<a href="/CandidatesEvaluation/Logout"><img src="http://cdn1.iconfinder.com/data/icons/minimal/24x24/apps/gnome-session-logout.png" alt="logout" title="Logout" /></a>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="span12">
				<div id="errorContainer" class="hide"> 
					<div class="alert alert-block alert-error fade in message">
						<a href="#" class="close">×</a>
						<p></p>
					</div>
				</div>