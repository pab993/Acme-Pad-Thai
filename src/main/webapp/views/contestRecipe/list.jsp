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

<display:table name="contestRecipes" id="row" requestURI="${requestURI }" pagesize="5" class="displaytag">

	<spring:message code="recipe.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader }" />
	
	<spring:message code="recipe.summary" var="summaryHeader" />
	<display:column property="summary" title="${summaryHeader }" />
	
	<spring:message code="recipe.momentAuthored" var="momentAuthoredHeader" />
	<display:column property="momentAuthored" title="${momentAuthoredHeader }" />
	
	<spring:message code="recipe.momentLastUpdate" var="momentLastUpdateHeader" />
	<display:column property="momentLastUpdate" title="${momentLastUpdateHeader }" />
	
	<spring:message code="contestRecipe.likes" var="headerLikes"/>
	<display:column property="likes" title="${headerLikes }"/>
	
	<spring:message code="contestRecipe.disLikes" var="headerDisLikes"/>
	<display:column property="dislikes" title="${headerDisLikes }"/>
	
	<spring:message code="contestRecipe.winner" var="headerWinner"/>
		
	
	<jstl:if test="${row.winner == true }">
		<display:column style="background:orange" value="${headerWinner }" title="${headerWinner }"/>
	</jstl:if>
	
	<jstl:if test="${row.winner == false }">
		<display:column value="--" title="${headerWinner }"/>
	</jstl:if>
	<!-- ↓↓↓ pictures to show ↓↓↓ -->
	
	<spring:message code="recipe.pictures" var="pictureHeader" />
	<display:column title="${pictureHeader }">
	
		<a href="contestRecipe/picturesList.do?contestRecipeId=${row.id }">
			<spring:message code="recipe.pictures.link"/>
		</a>
	
	</display:column>
	
	
</display:table>

