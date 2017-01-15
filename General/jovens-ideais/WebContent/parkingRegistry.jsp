<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="header.jsp"%>

<h2 class="subtitle">Formulario para atualização de preferencias do
	Estacionamento.</h2>
	
<div id="message">
	<p class="hide"></p>
</div>
<div id="content">
	<h2>Cadastro de Vagas</h2>
	<s:form action="saveParking">
		<s:textfield name="parking.totalVacancyNumber" label="Nº Vagas" cssClass="Nº Vagas"></s:textfield>
		<s:submit></s:submit>
	</s:form>
</div>
<%@ include file="footer.jsp"%>