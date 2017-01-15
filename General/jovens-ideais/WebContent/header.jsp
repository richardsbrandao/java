<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="scripts/jquery.js"></script>
<script type="text/javascript" src="scripts/parking.js"></script>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<title>Insert title here</title>
</head>
<body>
	<div id="wrap">

		<div id="head">
			<ul class="navbar">
				<li><a href="<s:property value="url" />CustomerRegistry">Customer</a>
				</li>
				<li><a href="<s:property value='url' />VehicleRegistry">Vehicle</a>
				</li>
				<li><a href="<s:property value='url' />ParkingRegistry">Parking</a>
				</li>
				<li><a href="<s:property value='url' />VehicleFlowRegistry">Vehicle Flow Registry</a>
				</li>
			</ul>
			<h1 class="title">Parking</h1>
		</div>
		<div id="main">
			<div id="splash"></div>