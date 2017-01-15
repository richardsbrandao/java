<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listar Máquina</title>
</head>
<body>

<c:if test="${ !empty mensagem }">
${mensagem}
</c:if>

<table>
	<tr>
		<th>ID</th>
		<th>Nome</th>
	</tr>
	<c:forEach items="${maquinas}" var="maquina">
		<tr>
			<td>${maquina.id}</td>
			<td>${maquina.nome}</td>
		</tr>	
	</c:forEach>
</table>

<a href="/MvcBasico/CriarMaquina">Criar Máquina</a>

</body>
</html>