<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="property/nutritionist/edit.do" modelAttribute="property">

	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="possesions"/>

	<form:label path="name">
		<spring:message code="property.name"/>
	</form:label>
	<form:input path="name"/>
	<form:errors cssClass="error" path="name" />

	
	<input type="submit" name="save" value="<spring:message code="property.save" />" />&nbsp; 
	<input type="submit" name="delete" value="<spring:message code="property.delete" />" />
	
	<input type="button" name="cancel"
		value="<spring:message code="user.cancel" />"
		onclick="javascript: window.location.replace('welcome/index.do')" />
	

</form:form>