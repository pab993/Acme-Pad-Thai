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


<form:form action="recipe/search.do" method="post">

	<input type="text" name="keyword" />	
	
	<input type="submit" name="search" value=" <spring:message code="recipe.button.search" /> " />

</form:form>

<display:table name="recipe" id="row" requestURI="${requestURI }" pagesize="5" class="displaytag">

	<spring:message code="recipe.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader }" />
	
	<spring:message code="recipe.summary" var="summaryHeader" />
	<display:column property="summary" title="${summaryHeader }" />
	
	<spring:message code="recipe.momentAuthored" var="momentAuthoredHeader" />
	<display:column property="momentAuthored" title="${momentAuthoredHeader }" format="{0,date,dd/MM/yyyy HH:mm}"/>
	
	<spring:message code="recipe.momentLastUpdate" var="momentLastUpdateHeader" />
	<display:column property="momentLastUpdate" title="${momentLastUpdateHeader }" format="{0,date,dd/MM/yyyy HH:mm}"/>
	
	<spring:message code="recipe.steps" var="stepsHeader" />
	<display:column title="${stepsHeader }">
	
		<a href="recipe/steps/list.do?recipeId=${row.id }">
			<spring:message code="recipe.steps"/>
		</a>
	
	</display:column>
	
	<!-- ↓↓↓ pictures to show ↓↓↓ -->
	
	<spring:message code="recipe.pictures" var="pictureHeader" />
	<display:column title="${pictureHeader }">
	
		<a href="recipe/picturesList.do?recipeId=${row.id }">
			<spring:message code="recipe.pictures.link"/>
		</a>
	
	</display:column>
	
	<spring:message code="recipe.authors" var="authorHeader"/>
	<display:column title="${authorHeader }">
		<a href="user/display.do?recipeId=${row.id }">
			<spring:message code="recipe.author"/>
		</a>
	</display:column>
	
	<spring:message code="recipe.comments" var="commentsHeader"/>
	<display:column title="${commentsHeader }">
		<a href="comment/list.do?recipeId=${row.id }">
			<spring:message code="recipe.comments"/>
		</a>
	</display:column>
	
	<security:authorize access="isAuthenticated()">
		<spring:message code="recipe.like" var="likeHeader"/>
		<display:column title="${likeHeader}">
			<jstl:if test="${row.user.id != userVar.id }">
				<a href="customer/recipe/like.do?recipeId=${row.id}">
					<spring:message code="recipe.like" />
				</a>
			</jstl:if>
		</display:column>
	</security:authorize> 
	
	<security:authorize access="isAuthenticated()">
			<spring:message code="recipe.bartols" var="bar1"/>
			<display:column title="${bar1}">
					<a href="bartols/recipe/listNutri.do?recipeId=${row.id}">
						<spring:message code="bartols.list"/>
					</a>
			</display:column>
	</security:authorize>
	
</display:table>