<%@ include file="header.jsp" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>Nova quest&atilde;o</h1>
<form class="form-horizontal" method="post" action="UpdateQuestion" name="formEditQuestion">
    <fieldset>
        <legend>Editar Quest&atilde;o</legend>
        <div class="control-group">
            <label for="questionTitle" class="control-label">T&itilde;tulo</label>
            <div class="controls input-xlarge">
                <input type="text" name="question.title" value="<s:property value='question.title' />" id="questionTitle" class="input-xlarge focused">
            </div>
        </div>
        <div class="control-group">
            <label for="questionDescription" class="control-label">Enunciado</label>
            <div class="controls input-xlarge">
                <textarea name="question.description" id="questionDescription" class="input-xlarge" id="textarea" rows="3"><s:property value='question.description' /></textarea>
            </div>
        </div>
        <div class="control-group">
            <label for="questionDescription" class="control-label">Estado</label>
            <div class="controls input-xlarge">
				<select name="question.status">
					<option value="ACTIVE" <c:if test="${question.status == 'ACTIVE'}">selected="selected"</c:if>>Ativado</option>
					<option value="INACTIVE" <c:if test="${question.status == 'INACTIVE'}">selected="selected"</c:if>>Inativado</option>
				</select>
            </div>
        </div>
        <div class="control-group">
            <label for="questionDescription" class="control-label">N&iacute;vel</label>
            <div class="controls input-xlarge">
				<select name="question.level">
				<option value="ESTAGIARIO" <c:if test="${question.level.level == 'ESTAGIARIO'}">selected="selected"</c:if>>Estagi&aacute;rio</option>
					<option value="JUNIOR" <c:if test="${question.level.level == 'JUNIOR'}">selected="selected"</c:if>>J&uacute;nior</option>
					<option value="PLENO" <c:if test="${question.level.level == 'PLENO'}">selected="selected"</c:if>>Pleno</option>
					<option value="SENIOR" <c:if test="${question.level.level == 'SENIOR'}">selected="selected"</c:if>>S&ecirc;nior</option>
				</select>
            </div>
        </div>
        <div class="control-group">
            <label for="focusedInput" class="control-label">Respostas</label>
            <div class="controls input-xxlarge">
                <table class="table">
                    <thead>
                        <tr>
                            <th class="centered-text">Op&ccedil;&atilde;o</th>
                            <th class="centered-text">Correta</th>
                            <th>Texto</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td class="centered-text">A</td>
                            <td class="centered-text">
                                <input type="radio" value="0" name="correctAnswer" <c:if test="${question.options[0].correct == true}">checked='checked'</c:if> /></td>
                            <td>
                                <input type="text" name="question.options[0].answer" value="${question.options[0].answer}" id="questionOptions0Answer" class="input-xlarge focused" />
                                <input type="hidden" name="question.options[0].id" value="${question.options[0].id}" /> 
                            </td>
                        </tr>

                        <tr>
                            <td class="centered-text">B</td>
                            <td class="centered-text">
                                <input type="radio"	value="1" name="correctAnswer" <c:if test="${question.options[1].correct == true}">checked="checked"</c:if> />
                            </td>
                            <td>
                                <input type="text" name="question.options[1].answer" value="${question.options[1].answer}" id="questionOptions1Answer" class="input-xlarge focused" />
                                <input type="hidden" name="question.options[1].id" value="${question.options[1].id}" />
                            </td>
                        </tr>

                        <tr>
                            <td class="centered-text">C</td>
                            <td class="centered-text">
                                <input type="radio"	value="2" name="correctAnswer" <c:if test="${question.options[2].correct == 'true'}">checked="checked"</c:if>/></td>
                            <td>
                                <input type="text" name="question.options[2].answer" value="${question.options[2].answer}" id="questionOptions2Answer" class="input-xlarge focused" />
                                <input type="hidden" name="question.options[2].id" value="${question.options[2].id}" />
                            </td>
                        </tr>

                        <tr>
                            <td class="centered-text">D</td>
                            <td class="centered-text">
                                <input type="radio"	value="3" name="correctAnswer" <c:if test="${question.options[3].correct == 'true'}">checked="checked"</c:if>/></td>
                            <td>
                                <input type="text" name="question.options[3].answer" value="${question.options[3].answer}" id="questionOptions3Answer" class="input-xlarge focused" />
                                <input type="hidden" name="question.options[3].id" value="${question.options[3].id}" />
                            </td>
                        </tr>

                        <tr>
                            <td class="centered-text">E</td>
                            <td class="centered-text">
                                <input type="radio"	value="4" name="correctAnswer" <c:if test="${question.options[4].correct == 'true'}">checked="checked"</c:if>/></td>
                            <td>
                                <input type="text" name="question.options[4].answer" value="${question.options[4].answer}"  id="questionOptions4Answer" class="input-xlarge focused" />
                                <input type="hidden" name="question.options[4].id" value="${question.options[4].id}" />
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        </div>
        </div>
        <div class="form-actions">
            <button type="submit" class="btn btn-primary">Salvar</button>
            <button type="javascript:history.back();" class="btn">Cancelar</button>
        </div>
    </fieldset>
	<input type="hidden" name="question.id" value="${question.id}" />
</form>

<%@ include file="footer.jsp" %>