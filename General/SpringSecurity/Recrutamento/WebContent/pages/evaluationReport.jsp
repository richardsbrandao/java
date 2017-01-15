<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="header.jsp"%>

<h1>Relat&oacute;rio de Resultados</h1>
<hr>
<table>
	<tr>
		<td width="100px">Data Gera&ccedil;&atilde;o:</td>
		<td width="300px"><s:date name="currentDate" format="dd/MM/yyyy hh:mm" /></td>
	</tr>
	<tr>
		<td width="100px">Data In&iacute;cio:</td>
		<td width="300"><s:date name="filter.beginning" format="dd/MM/yyyy" />
		</td>
	</tr>
	<tr>
		<td width="100px">Data Fim:</td>
		<td width="300px"><s:date name="filter.end" format="dd/MM/yyyy" /></td>
		<td width="100px">N&iacute;vel:</td>
		<td width="300"><s:property value="filter.level" /></td>
	</tr>
</table>
<hr>
<table class="table table-bordered">
	<tr>
		<td width="100px">Data</td>
		<td width="300px">Candidato</td>
		<td width="300px">E-mail</td>
		<td width="300px">N&iacute;vel</td>
		<td width="100px">Resultado</td>
	</tr>
	<s:iterator value="evaluations">
		<tr>
			<td><s:date name="endDate" format="dd/MM/yyyy" /></td>
			<td><s:property value="candidate.name" /></td>
			<td><s:property value="candidate.email" /></td>
			<td><s:property value="candidate.level" /></td>
			<td><s:property value="result" /></td>
		</tr>
	</s:iterator>
</table>

<%@ include file="footer.jsp"%>