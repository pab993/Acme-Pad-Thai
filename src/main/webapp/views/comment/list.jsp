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

<display:table name="comment" id="row" requestURI="${requestURI }" pagesize="5" class="displaytag">

	<spring:message code="comment.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader }" />
	
	<spring:message code="comment.text" var="textHeader" />
	<display:column property="text" title="${textHeader }" />
	
	<spring:message code="comment.numberOfStars" var="numberHeader" />
	<display:column property="numberOfStars" title="${numberHeader }" />
	
	<spring:message code="comment.momentOfCreation" var="momentOfCreationHeader" />
	<display:column property="momentOfCreation" title="${momentOfCreationHeader }" format="{0,date,dd/MM/yyyy HH:mm}"/>
		
</display:table>
<security:authorize access="isAuthenticated()">
	<a href="comment/create.do?recipeId=${recipeId }">
		<spring:message code="comment.create"/>
	</a>
</security:authorize>