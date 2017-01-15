<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:form method="post" action="/AcademicWeb/institution/save" commandName="institutionRequest">
	<fieldset>
		<legend>Nova Instituição</legend>
		<label>
			Nome
			<input type="text" name="name" placeholder="Nome da Instituição" autofocus>
		</label>
		<button type="submit" class="btn" id="submit">Cadastrar</button>	
	</fieldset>
</form:form>