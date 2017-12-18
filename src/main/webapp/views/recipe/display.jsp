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





<display:table name="recipes" id="row" requestURI="${requestURI }" pagesize="5" class="displaytag">

	<spring:message code="recipe.title" var="titleHeader" />
	<spring:message code="recipe.summary" var="summaryHeader" />
	<spring:message code="recipe.momentAuthored" var="momentAuthoredHeader" />
	<spring:message code="recipe.momentLastUpdate" var="momentLastUpdateHeader" />
	
	
	<spring:message code="recipe.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader }" />
	
	<spring:message code="recipe.summary" var="summaryHeader" />
	<display:column property="summary" title="${summaryHeader }" />
	
	<spring:message code="recipe.momentAuthored" var="momentAuthoredHeader" />
	<display:column property="momentAuthored" title="${momentAuthoredHeader }" />
	
	<spring:message code="recipe.momentLastUpdate" var="momentLastUpdateHeader" />
	<display:column property="momentLastUpdate" title="${momentLastUpdateHeader }" />
		
	
		<spring:message code="recipe.pictures" var="pictureHeader" />
	<display:column title="${pictureHeader }">
	
		<a href="recipe/picturesList.do?recipeId=${row.id }">
			<spring:message code="recipe.pictures.link"/>
		</a>
	
	</display:column>
	
	<spring:message code="recipe.winner" var="recipeWinnerHeader"/>
	<display:column style="background:orange" title="${recipeWinnerHeader }">
	<jstl:out value="TO DO"/>
	</display:column>
	
	<!-- SHOW AUTHOR OF RECIPE -->
	<spring:message code="recipe.authors" var="authorHeader"/>
	<display:column title="${authorHeader }">
		<a href="user/display.do?recipeId=${row.id }">
			<spring:message code="recipe.author"/>
		</a>
	</display:column>
		

</display:table>