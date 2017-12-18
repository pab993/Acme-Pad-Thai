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

<display:table name="bills" id="row" requestURI="${requestURI }" pagesize="5" class="displaytag">


	
	<spring:message code="bill.momentCreate" var="momentCreateHeader" />
	<display:column property="momentCreate" title="${momentCreateHeader }" />
	
	<spring:message code="bill.momentPay" var="momentPayHeader" />
	<display:column property="momentPay" title="${momentPayHeader }" />
	
	<spring:message code="bill.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader }" />
	
	<spring:message code="bill.cost" var="costHeader" />
	<display:column property="cost" title="${costHeader }" />


	
</display:table>