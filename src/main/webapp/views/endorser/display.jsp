<%--
 * action-1.jsp
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

<display:table name="endorsers" id="endorser" pagesize="5" requestURI="${requestURI}" class="displaytag">

		<spring:message code="endorser.name" var="name" />
		<display:column title="${name}" property="name" />
		
		<spring:message code="endorser.homePage" var="homePage" />
		<display:column title="${homePage}" property="homePage" />
		
		<display:column>
			<a href="endorser/nutritionist/edit.do?endorserId=${endorser.id}"><spring:message code="endorser.edit" /></a>
		</display:column>
	
</display:table>

<div>
	<a href="endorser/nutritionist/create.do"><spring:message code="endorser.create"/></a>
</div>