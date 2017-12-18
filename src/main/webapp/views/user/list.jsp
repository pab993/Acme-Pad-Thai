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

<form:form action="user/search.do" method="post">

	<input type="text" name="keyword" />	
	
	<input type="submit" name="search" value=" <spring:message code="user.button.search" /> " />

</form:form>

<display:table name="users" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">

	<spring:message code="actor.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader }" />
	
	<spring:message code="actor.surname" var="surnameHeader" />
	<display:column property="surname" title="${surnameHeader }" />
	
	<spring:message code="actor.phoneNumber" var="phoneNumberHeader" />
	<display:column property="phoneNumber" title="${phoneNumberHeader }" />
	
	<spring:message code="actor.postalAddress" var="postalAddressHeader" />
	<display:column property="postalAddress" title="${postalAddressHeader }" />

	<spring:message code="actor.recipe" var="userReciperHeader"/>
	<display:column title="${userReciperHeader }">
		<a href="userRecipes/list.do?userId=${row.id }">
			<spring:message code="user.recipes"/>
		</a>
	</display:column>
	
	
</display:table>

<security:authorize access="isAuthenticated()">
	
</security:authorize>