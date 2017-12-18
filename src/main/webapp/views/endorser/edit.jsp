<%--
 * action-2.jsp
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


<form:form action="endorser/nutritionist/edit.do" modelAttribute="endorser">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="curriculum" />
		
	<form:label path="name">
		<spring:message code="endorser.name" />:
	</form:label>
	<form:input path="name" />
	<form:errors cssClass="error" path="name" />
	<br />
	
	<form:label path="homePage" >
		<spring:message code="endorser.homePage" />:
	</form:label>
	<form:input path="homePage" />
	<form:errors cssClass="error" path="homePage" />
	<br />
	
	
	<input type="submit" name="save" value="<spring:message code="endorser.save" />" />&nbsp;
	<jstl:if test="${endorser.id != 0}">
		<input type="submit" name="delete" value="<spring:message code="endorser.delete" />" onclick="return confirm('<spring:message code="endorser.confirm.delete" />')" />&nbsp;
	</jstl:if>
	<input type="button" name="cancel" value="<spring:message code="endorser.cancel"/>" onclick="javascript: window.location.replace('curriculum/nutritionist/list.do')"/>
	
</form:form>