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

<display:table name="ingredient" id="row" requestURI="${requestURI }" pagesize="5" class="displaytag">

	<spring:message code="ingredient.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader }" />
	
	<spring:message code="ingredient.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader }" />
	
	<spring:message code="ingredient.picture" var="pictureHeader" />
	<display:column property="picture" title="${pictureHeader }" />
	
	<security:authorize access="hasRole('USER')">
			<spring:message code="ingredient.remove" var="ingredientRemove"/>
			<display:column title="${ingredientRemove}">
				<jstl:if test="${recipeFull.user == userVar }">
					<a href="user/ingredients/remove.do?ingredientId=${row.id }&recipeId=${recipeId}">
						<spring:message code="ingredient.remove"/>
					</a>
				</jstl:if>
			</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('USER')">
			<spring:message code="ingredient.measures" var="ingredientUS"/>
			<display:column title="${ingredientUS}">
				<jstl:if test="${recipeFull.user == userVar }">
					<a href="user/unitSystem/edit.do?ingredientId=${row.id }&recipeId=${recipeId}">
						<spring:message code="ingredient.measures"/>
					</a>
				</jstl:if>
			</display:column>
	</security:authorize>
	
	<spring:message code="ingredient.measures.list" var="ingredientUS"/>
	<display:column title="${ingredientUS}">
		<a href="user/unitSystem/list.do?ingredientId=${row.id }&recipeId=${recipeId}">
			<spring:message code="ingredient.measures.list"/>
		</a>
	</display:column>
	
</display:table>
<security:authorize access="hasRole('USER')">
	<!-- Añadir comprobante de que es el dueño de la receta -->
	<jstl:if test="${recipeFull.user == userVar }">
	<a href="user/ingredients/add.do?recipeId=${recipeId}">
		<spring:message code="user.recipe.ingredient.add"/>
	</a>
	</jstl:if>
</security:authorize>