<%--
 *edit.jsp
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
	
<form:form action="configurationSystem/administrator/edit.do" modelAttribute="configurationSystem">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="fee"/>
	
	<form:label path="spamWords">
		<spring:message code="configurationSystem.spamWords"/>
	</form:label>
	<form:textarea path="spamWords"/>
	<form:errors cssClass="error" path="spamWords"/>

	<%-- <form:label path="fee">
		<spring:message code="configurationSystem.fee" />
	</form:label>
	<form:input path="fee"/>
	<form:errors cssClass="error" path="fee" /> --%>
	 
	 
	<input type="submit" name="save" value="<spring:message code ="configurationSystem.save" />" />&nbsp;
	<input type="button" name="cancel" value="<spring:message code="configurationSystem.cancel"/>" onclick="javascript: window.location.replace('/Acme-Pad-Thai/')"/>	
	
</form:form>
	
	