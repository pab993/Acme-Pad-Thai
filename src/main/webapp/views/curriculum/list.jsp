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

<display:table name="curricula" id="curriculum" pagesize="5" requestURI="${requestURI}" class="displaytag">

	<security:authorize access="hasRole('NUTRITIONIST')">
		
	
		<spring:message code="curriculum.photo" />
		<display:column>
			<a href="${curriculum.photo}"><spring:message code="curriculum.photo" /></a>
		</display:column>
		
		<spring:message code="curriculum.educationSection" var="educationSection" />
		<display:column title="${educationSection}" property="educationSection" />
		
		<spring:message code="curriculum.experienceSection" var="experienceSection" />
		<display:column title="${experienceSection}" property="experienceSection" />
		
		<spring:message code="curriculum.hobbiesSection" var="hobbiesSection" />
		<display:column title="${hobbiesSection}" property="hobbiesSection" />
		
		<spring:message code="curriculum.endorser" var="curriculumEndorser"/>
		<display:column title="${curriculumEndorser}">
			<a href="endorser/nutritionist/display.do?curriculumId=${curriculum.id}"><spring:message code="endorser.list" /></a>
		</display:column>
		
		<display:column>
			<a href="curriculum/nutritionist/edit.do?curriculumId=${curriculum.id}"><spring:message code="curriculum.edit" /></a>
		</display:column>
	
	</security:authorize>
</display:table>

<jstl:if test="${curriculum.id==null}">
			<a href="curriculum/nutritionist/create.do"><spring:message code="curriculum.create" /></a>
	</jstl:if>