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
	
	<spring:message code="nutritionist.ingredient.edit" var="editHeader"/>
	<display:column>
		<a href="nutritionist/ingredient/edit.do?ingredientId=${row.id}">
			<spring:message code="nutritionist.ingredient.edit"/>
		</a>
	</display:column>
	
	<spring:message code="nutritionist.ingredient.properties.list" var="propertiesListHeader"/>
	<display:column>
		<a href="nutritionist/ingredient/properties/listOfProperties.do?ingredientId=${row.id}">
			<spring:message code="nutritionist.ingredient.properties.list"/>
		</a>
	</display:column>
	
	
</display:table>
<security:authorize access="hasRole('NUTRITIONIST')">
	<!-- Añadir comprobante de que es el dueño de la receta -->
	<a href="nutritionist/ingredient/create.do">
		<spring:message code="nutritionist.recipe.ingredient.create"/>
	</a>
</security:authorize>