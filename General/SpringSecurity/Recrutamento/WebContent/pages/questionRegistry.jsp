<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="header.jsp" %>
<h1>Nova quest&atilde;o</h1>

<form class="form-horizontal" name="formCreateNewQuestion" method="post" action="SaveQuestion">
    <fieldset>
        <legend>Formul&aacute;rio de cadastro de quest&otilde;es</legend>
        <div class="control-group">
            <label for="questionTitle" class="control-label">T&iacute;tulo</label>
            <div class="controls input-xlarge">
                <input type="text" name="question.title" id="questionTitle" class="input-xlarge focused">
            </div>
        </div>
        <div class="control-group">
            <label for="questionDescription" class="control-label">Enunciado</label>
            <div class="controls input-xlarge">
                <textarea name="question.description" id="questionDescription" class="input-xlarge" id="textarea" rows="3"></textarea>
            </div>
        </div>
        <div class="control-group">
            <label for="questionDescription" class="control-label">N&iacute;vel</label>
            <div class="controls input-xlarge">
				<select name="question.level">
					<option value="ESTAGIARIO">Estagi&aacute;rio</option>
					<option value="JUNIOR">J&uacute;nior</option>
					<option value="PLENO">Pleno</option>
					<option value="SENIOR">S&ecirc;nior</option>
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
                                <input type="radio" value="0" name="correctAnswer" /></td>
                            <td>
                                <input type="text" name="question.options[0].answer" id="questionOptions0Answer" class="input-xlarge focused" />
                            </td>
                        </tr>

                        <tr>
                            <td class="centered-text">B</td>
                            <td class="centered-text">
                                <input type="radio"	value="1" name="correctAnswer" /></td>
                            <td>
                                <input type="text" name="question.options[1].answer" id="questionOptions1Answer" class="input-xlarge focused" />
                            </td>
                        </tr>

                        <tr>
                            <td class="centered-text">C</td>
                            <td class="centered-text">
                                <input type="radio"	value="2" name="correctAnswer" /></td>
                            <td>
                                <input type="text" name="question.options[2].answer" id="questionOptions2Answer" class="input-xlarge focused" />
                            </td>
                        </tr>

                        <tr>
                            <td class="centered-text">D</td>
                            <td class="centered-text">
                                <input type="radio"	value="3" name="correctAnswer" /></td>
                            <td>
                                <input type="text" name="question.options[3].answer" id="questionOptions3Answer" class="input-xlarge focused" />
                            </td>
                        </tr>

                        <tr>
                            <td class="centered-text">E</td>
                            <td class="centered-text">
                                <input type="radio"	value="4" name="correctAnswer" /></td>
                            <td>
                                <input type="text" value="" name="question.options[4].answer" id="questionOptions4Answer" class="input-xlarge focused" />
                            </td>
                        </tr>

                    </tbody>
                </table>
            </div>
        </div>
        </div>
        </div>
        <div class="form-actions">
            <button type="submit" class="btn btn-primary">Cadastrar</button>
            <button type="reset" class="btn">Cancelar</button>
        </div>
    </fieldset>
</form>

<%@ include file="footer.jsp" %>