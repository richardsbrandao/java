<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title><tiles:insertAttribute name="title" /></title>
        <link rel="stylesheet" href="/AcademicWeb/statics/css/bootstrap.css">
        <link rel="stylesheet" href="/AcademicWeb/statics/css/bootstrap-responsive.css">
        <link rel="stylesheet" href="/AcademicWeb/statics/css/style.css">

        <script type="text/javascript" src="/AcademicWeb/statics/js/jquery_1.8.1.js"></script>
        <script type="text/javascript" src="/AcademicWeb/statics/js/bootstrap-dropdown.js"></script>
        <script type="text/javascript" src="/AcademicWeb/statics/js/bootstrap-button.js"></script>
        <script type="text/javascript" src="/AcademicWeb/statics/js/web.js"></script>
    </head>
    <body> 
        <div id="global" class="container">
            <header id="header" class="navbar navbar-inverse navbar-fixed-top">
            	<tiles:insertAttribute name="header" />
            </header>
            <section id="content" class="container">
            	<c:if test="${!empty messageKey}">
					<div class="alert alert-${messageKey}">
					  <button type="button" class="close" data-dismiss="alert">&times;</button>
					  <p id="message">
					  	${message}
					  </p>
					</div>
				</c:if>
                <h1><tiles:insertAttribute name="title" /></h1>
                <tiles:insertAttribute name="body" />
            </section>
            <hr>
            <footer id="footer">
            	<tiles:insertAttribute name="footer" />
            </footer>
        </div>
    </body>
</html>
                