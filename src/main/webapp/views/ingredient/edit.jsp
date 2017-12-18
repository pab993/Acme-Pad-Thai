<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="nutritionist/ingredient/edit.do" modelAttribute="ingredient">

	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="unitSystems"/>
	<form:hidden path="possesions"/>

	<form:label path="name">
		<spring:message code="ingredient.name"/>
	</form:label>
	<form:input path="name"/>
	<form:errors cssClass="error" path="name" />
	
	<form:label path="description">
		<spring:message code="ingredient.description"/>
	</form:label>
	<form:input path="description"/>
	<form:errors cssClass="error" path="description" />
	
	<form:label path="picture">
		<spring:message code="ingredient.picture"/>
	</form:label>
	<form:input path="picture"/>
	<form:errors cssClass="error" path="picture" />

	
	<input type="submit" name="save" value="<spring:message code="ingredient.save" />" />&nbsp; 
	<input type="submit" name="delete" value="<spring:message code="ingredient.delete" />" />
	
	<input type="button" name="cancel"
		value="<spring:message code="ingredient.cancel" />"
		onclick="javascript: window.location.replace('nutritionist/ingredient/listAll.do')" />
	

</form:form>