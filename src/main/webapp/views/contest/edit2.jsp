<%@page import="org.apache.taglibs.standard.lang.jstl.test.beans.PublicBean1"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="contest/administrator/edit2.do" modelAttribute="contest">

	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="qualifications"/>
	<form:hidden path="contestRecipes"/>
	
	<form:label path="title">
		<spring:message code="contest.title"/>
	</form:label>
	<form:input cssStyle="background-color: rgb(247, 23, 23)" readonly="true" path="title"/>
	
	<form:label path="openingTime">
		<spring:message code="contest.openingTime"/>
	</form:label>
	<form:input cssStyle="background-color: rgb(247, 23, 23)" readonly="true" path="openingTime"/>
	
	<form:label path="closingTime">
		<spring:message code="contest.closingTime"/>
	</form:label>
	<form:input id="textField1"  path="closingTime"/>
	<form:errors cssClass="error" path="closingTime"/>
	
	<input type="submit" name="save2" value="<spring:message code="contest.save" />" />&nbsp;
	<input type="submit" name="delete" value="<spring:message code="contest.delete" />" />&nbsp;
	<input type="button" name="cancel"
		value="<spring:message code="contest.cancel" />"
		onclick="javascript: window.location.replace('contest/list.do')" />
		
</form:form>