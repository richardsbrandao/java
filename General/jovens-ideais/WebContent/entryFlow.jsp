<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="header.jsp"%>

<h2 class="subtitle">Formulario registro de entrada de veiculos.</h2>
		
<div id="message">
	<p class="hide"></p>
</div>
<div id="content">
	<h2>Registro de entrada de veiculos</h2>
	<s:form action="saveEntryVehicleFlow">
		<s:select list="vehicles" listKey="id" listValue="plate" name="flow.vehicle.id" label="Veiculos" cssClass="selectCase"></s:select>
		<s:submit></s:submit>
	</s:form>
</div>

<%@ include file="footer.jsp"%>
