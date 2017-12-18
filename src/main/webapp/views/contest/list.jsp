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

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<display:table name="contests" id="row" pagesize="5" requestURI="${requestURI}" class="displaytag">
	<display:column>
		<security:authorize access="hasRole('ADMIN')">
			
			<jstl:if test="${row.closingTime > date }">
				<a href="contest/administrator/edit2.do?contestId=${row.id}">
					<spring:message code="contest.edit" />
				</a>
			</jstl:if>
			
		</security:authorize>
	</display:column>
	
	<spring:message code="contest.title" var="ctitle" />	
	<display:column title="${ctitle}" property="title" />
	
	<spring:message code="contest.openingTime" var="copeningTime" />
	<display:column title="${copeningTime}" property="openingTime" format="{0,date,dd/MM/yyyy HH:mm}" />	
	
	<spring:message code="contest.closingTime" var="cclosingTime" />
	<display:column title="${cclosingTime}" property="closingTime" format="{0,date,dd/MM/yyyy HH:mm}" />
	
	<spring:message code="contest.recipe" var="contestRecipeHeader"/>
	<display:column title="${contestRecipeHeader }">
	
	<a href="contestRecipe/list.do?contestId=${row.id }">
		<spring:message code="contest.recipe"/>
	
	</a>
	</display:column>
	
	<display:column>
		<security:authorize access="hasRole('USER')">	
			<jstl:if test="${row.closingTime > date}">
				<a href="qualification/edit.do?contestId=${row.id }">
					<spring:message code="contest.recipe.qualify"/>
				</a>
			</jstl:if>
		</security:authorize>
	</display:column>
</display:table>

<!-- Action Links -->
<security:authorize access="hasRole('ADMIN')">
	<div>
		<a href="contest/administrator/create.do"><spring:message code="contest.create"/></a>
	</div>
</security:authorize>
