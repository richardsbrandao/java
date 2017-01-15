<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="header.jsp"%>

<h2 class="subtitle">Formulario criação de clientes.</h2>
		
<div id="message">
	<p class="hide"></p>
</div>
<div id="content">
	<h2>Registro de Clientes</h2>
	<s:form action="SaveCustomer">
		<s:textfield name="customer.name" label="Nome" cssClass="Nome"></s:textfield>
		<s:textfield name="customer.telephone" label="Telefone" cssClass="Telefone"></s:textfield>
		<s:textfield name="customer.cpf" label="Cpf" cssClass="Cpf"></s:textfield>
		<s:select list="customerType" name="customer.type" label="Tipo de Cliente" ></s:select>
		<s:submit></s:submit>
	</s:form>
</div>

<%@ include file="footer.jsp"%>
