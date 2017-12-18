<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<display:table name="steps" id="row" requestURI="${requestURI }" pagesize="5" class="displaytag">

	<spring:message code="step.number" var="numberHeader" />
	<display:column property="number" title="${numberHeader }" />
	
	<spring:message code="step.description" var="descriptionHeader" /> 
	<display:column property="description" title="${descriptionHeader }" />
	
	<spring:message code="step.hints" var="hintsHeader" />
	<display:column property="hints" title="${hintsHeader }" />
	
	<spring:message code="step.picture" var="pictureHeader" />
	<display:column property="picture" title="${pictureHeader }" />
	
	<security:authorize access="hasRole('USER')">
		<spring:message code="step.edit" var="edit"/>
		<display:column title="${edit}">
			<a href="recipe/steps/edit.do?stepId=${row.id}">
				<spring:message code="user.step.edit"/>
			</a>
	</display:column>
	</security:authorize>
	
</display:table>

<security:authorize access="hasRole('USER')">
	<div>
		<a href="recipe/steps/create.do?recipeId=${row.recipe.id}"><spring:message code="step.create"/></a>
	</div>
</security:authorize>
