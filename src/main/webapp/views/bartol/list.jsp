<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<display:table name="bartols" id="bartol" pagesize="5" requestURI="${requestURI}" class="displaytag">
	
	<spring:message code="bartol.label" var="blabel" />	
	<display:column title="${blabel}" property="label" />
	
	<spring:message code="bartol.title" var="btitle" />	
	<display:column title="${btitle}" property="title" />
	
	<spring:message code="bartol.description" var="bdescription" />
	<display:column title="${bdescription}" property="description" />	

	<spring:message code="bartol.calories" var="bcalories" />
	<display:column title="${bcalories}" property="calories" />

	<security:authorize access="hasRole('NUTRITIONIST')">
		<display:column>
			<jstl:if test="${bartol.nutritionist.id == nutriVar.id }">
				<a href="bartols/recipe/edit.do?bartolId=${bartol.id}">
					<spring:message code="bartol.edit" />
				</a>
			</jstl:if>
		</display:column>
	</security:authorize>
	
</display:table>

<security:authorize access="hasRole('NUTRITIONIST')">
	<div>
		<a href="bartols/recipe/create.do?recipeId=${recipeId}"><spring:message code="bartol.create"/></a>
	</div>	
</security:authorize>