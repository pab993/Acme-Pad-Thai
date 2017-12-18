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

<form:form action="campaign/sponsor/edit.do" modelAttribute="campaign">

		<form:hidden path="id" />
		<form:hidden path="version" />
		
	<security:authorize access="hasRole('SPONSOR')">
		<form:label path="bannersnumber">
			<spring:message code="campaign.bannersnumber" />:
		</form:label>
		<form:input path="bannersnumber" />
		<form:errors cssClass="error" path="bannersnumber" />
		<br />
		
		<form:label path="maximumbanner">
			<spring:message code="campaign.maximumbanner" />:
		</form:label>
		<form:input path="maximumbanner" />
		<form:errors cssClass="error" path="maximumbanner" />
		<br />
		
		<input type="submit" name="save" value="<spring:message code="campaign.save" />" />&nbsp;
		<jstl:if test="${campaign.id != 0}">
			<input type="submit" name="delete" value="<spring:message code="campaign.delete" />" onclick="return confirm('<spring:message code="campaign.confirm.delete" />')" />&nbsp;
		</jstl:if>
		<input type="button" name="cancel" value="<spring:message code="campaign.cancel"/>" onclick="javascript: window.location.replace('campaign/administrator/list.do')"/>
	</security:authorize>
		
		<security:authorize access="hasRole('ADMINISTRATOR')">
		
		
		<input type="submit" name="Change" value="<spring:message code="campaign.change" />" />
		
		</security:authorize>
	</form:form>