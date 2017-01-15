<%@ include file="header.jsp" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>Gerador de Prova</h1>

<c:if test="${link != null}">
	<div id="messageContainer" class=""> 
		<div class="alert alert-block alert-success fade in message">
			<a href="#" class="close">×</a>
			<p>Prova gerada com sucesso.</p>
		</div>
	</div>
</c:if>

<form class="form-horizontal" action="generateLink" method="post" name="formCreateEvaluation">
	<fieldset>
		<legend>Formul&aacute;rio para gera&ccedil;&atilde;o da Prova</legend>
		<div class="control-group">
			<label for="candidateName" class="control-label">Nome</label>
			<div class="controls input-xlarge">
				<input type="text" name="candidate.name" value="<s:property value='candidate.name'/>" id="candidateName" class="input-xlarge focused">
			</div>
		</div>

		<div class="control-group">
			<label for="candidateEmail" class="control-label">E-mail</label>
			<div class="controls input-xlarge">
				<input type="text" name="candidate.email" value="<s:property value='candidate.email'/>" id="candidateEmail" class="input-xlarge focused">
			</div>
		</div>

		<div class="control-group">
			<label for="candidate.level" class="control-label">Nivel</label>
			<div class="controls">
				<select id="candidateLevel" name="candidate.level">
					<option value="">- Escolha uma op&ccedil;&atilde;o -</option>
					<option value="ESTAGIARIO" <c:if test="${candidate.level == 'ESTAGIARIO'}">selected="selected"</c:if>>Estagi&aacute;rio</option>
					<option value="JUNIOR" <c:if test="${candidate.level == 'JUNIOR'}">selected="selected"</c:if>>Junior</option>
					<option value="PLENO"  <c:if test="${candidate.level == 'PLENO'}">selected="selected"</c:if>>Pleno</option>
					<option value="SENIOR" <c:if test="${candidate.level == 'SENIOR'}">selected="selected"</c:if>>Senior</option>
				</select>
			</div>
		</div>
		</div>
		</div>
		<div>
			<hr />
			<div>
				<button type="submit" class="btn btn-primary">Enviar</button>
				<button type="reset" class="btn">Cancel</button>
			</div>

		</div>

	</fieldset>
</form>
</div>
</div>
</div>
<%@ include file="footer.jsp" %>