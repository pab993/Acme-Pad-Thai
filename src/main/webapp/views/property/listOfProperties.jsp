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


<display:table name="properties" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
	
	<spring:message code="property.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader }" />
	
	
	<display:column>
		<a href="nutritionist/ingredient/properties/remove.do?ingredientId=${ingredientId }&propertyId=${row.id}">
			<spring:message code="property.remove"/>
		</a>
	</display:column>


	
</display:table>

<a href="nutritionist/ingredient/properties/add.do?ingredientId=${ingredientId }">
	<spring:message code="property.add" />
</a>