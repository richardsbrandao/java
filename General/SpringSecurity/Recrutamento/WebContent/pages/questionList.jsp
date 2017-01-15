<!DOCTYPE html>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="header.jsp" %>

				<h1>Listagem de Quest&otilde;es</h1>
					<table class="table table-bordered">
							<tr>
								<td style="width: 200px;">ID</td>
								<td style="width: 500px;">T&itilde;tulo</td>
								<td style="width: 500px;">Status</td>
							</tr>
						<s:iterator value="questions">
							<tr>
								<td><s:property value="id"/></td>
								<td><s:property value="title"/></td>
								<td><s:property value="status"/></td>
								<td><a href="EditQuestion?question.id=<s:property value='id'/>">edit</a></td>
								<td><a href="DeleteQuestion?question.id=<s:property value='id'/>" onclick="return confirm('Voc&ecirc; tem certeza que deseja excluir esta quest&atilde;o?');">delete</a></td>
							</tr>
						</s:iterator>
					</table>
			
<%@ include file="footer.jsp" %>