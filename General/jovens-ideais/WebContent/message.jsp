<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="header.jsp"%>

<div id="message">
	<p class="<s:property value="message.type" />"><s:property value="message.message"/></p>
</div>

<div id="content">
	Voltar?
</div>

<%@ include file="footer.jsp"%>
