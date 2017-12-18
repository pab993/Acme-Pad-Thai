<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<display:table name="categories" id="category" pagesize="5" requestURI="${requestURI}" class="displaytag">
	<display:column>
		<security:authorize access="hasRole('ADMIN')">
			<a href="category/administrator/edit.do?categoryId=${category.id}">
				<spring:message code="category.edit" />
			</a>
		</security:authorize>
	</display:column>
	<spring:message code="category.name" var="cname" />	
	<display:column title="${cname}" property="name" />
	
	<spring:message code="category.description" var="cdescription" />
	<display:column title="${cdescription}" property="description" />	

	<spring:message code="category.display.title" var="administratorCategory"/>
		<display:column title="${administratorCategory}">
			<a href="category/administrator/display.do?categoryId=${category.id}">
				<spring:message code="category.display"/>
			</a>
			
	</display:column>
</display:table>

<!-- Action Links -->
<security:authorize access="hasRole('ADMIN')">
	<div>
		<a href="category/administrator/create.do"><spring:message code="category.create"/></a>
	</div>
</security:authorize>

