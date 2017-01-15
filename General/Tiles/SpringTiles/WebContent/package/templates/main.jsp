<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title><tiles:insertAttribute name="title" /></title>
    </head>
    <body> 
        <header id="header" class="navbar navbar-inverse navbar-fixed-top">
        	<tiles:insertAttribute name="header" />
        </header>
        <section id="content" class="container">
            <h1><tiles:insertAttribute name="title" /></h1>
            <tiles:insertAttribute name="body" />
            <tiles:insertAttribute name="report" />
        </section>
        <hr>
        <footer id="footer">
        	<tiles:insertAttribute name="footer" />
        </footer>
    </body>
</html>
                