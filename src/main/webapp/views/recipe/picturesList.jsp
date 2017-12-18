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

<display:table name="recipe" id="row" requestURI="${requestURI }" pagesize="5" class="displaytag">

	<spring:message code="recipe.title" var="titleHeader" />
	<spring:message code="recipe.summary" var="summaryHeader" />
	<spring:message code="recipe.momentAuthored" var="momentAuthoredHeader" />
	<spring:message code="recipe.momentLastUpdate" var="momentLastUpdateHeader" />
	<spring:message code="recipe.pictures" var="pictureHeader" />
	
	<display:column title="${recipe.title }">
	
		<jstl:forEach
		var="recipeImag"
		items="${recipe.pictures }">
			
			<a href="${recipeImag }"><jstl:out value="${recipeImag}"/></a>
		
		</jstl:forEach>
	
	</display:column>
		
</display:table>