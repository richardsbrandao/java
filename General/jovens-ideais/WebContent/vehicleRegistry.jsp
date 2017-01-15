<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="header.jsp"%>

<h2 class="subtitle">Formulario para atualização de preferencias do
	Estacionamento.</h2>

<div id="message">
	<p class="hide"></p>
</div>
<div id="content">
	<h2>Cadastro de Veículos</h2>
	<s:form action="saveVehicle">
		<s:textfield name="vehicle.model" label="Modelo" cssClass="Modelo"></s:textfield>
		<s:textfield name="vehicle.plate" label="Placa" cssClass="Placa"></s:textfield>
		<s:select list="customers" listKey="id" listValue="name"
			name="vehicle.customer.id" label="Clientes" cssClass="Clientes" />
		<s:submit></s:submit>
	</s:form>

	<%@ include file="footer.jsp"%>