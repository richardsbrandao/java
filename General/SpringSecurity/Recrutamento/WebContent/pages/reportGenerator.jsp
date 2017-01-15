<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="header.jsp" %>
<h1>Gerador de Relat&oacute;rio</h1>

<form class="form-horizontal" action="generateReport" method="post" name="formReportGenerator">

	<fieldset>
		<legend>Selecione as datas do per&iacute;odo que deseja:</legend>
		<div>
		<table>
			<tr>
				<td width="300px">
					<label for="date_1" class="control-label">Data In&iacute;cio</label>
					<input type="text" class="datepicker" id="date_1" name="filter.beginning" />
				</td>
			</tr>
			<tr>
				<td>
					<label for="date_2" class="control-label">Data Fim</label>
					<input type="text" class="datepicker" id="date_2" name="filter.end" />
				</td>
				<td>
					<label for="level" class="control-label">N&iacute;vel</label>
					<select name="filter.level" id="level">
						<option value=""></option>
						<option value="ESTAGIARIO">Estagi&aacute;rio</option>
						<option value="JUNIOR">J&uacute;nior</option>
						<option value="PLENO">Pleno</option>
						<option value="SENIOR">S&ecirc;nior</option>
					</select>
				</td>
			</tr>
		</table>
		</div>
		<div>
			<div>
				<button type="submit" class="btn btn-primary">Gerar Relat&oacute;rio</button>
				<button type="reset" class="btn">Cancel</button>
			</div>
		</div>
	</fieldset>
</form>

<%@ include file="footer.jsp" %>