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

<form:form action="contest/qualify.do" modelAttribute="recipeContestForm">

	<form:hidden path="id" />
	<form:hidden path="version"/>
	<form:hidden path="contest"/>

	<br>
	
	<div>
		<spring:message code="choose" />
		<form:select id="recipes" path="recipes">
			<form:option label="----" value="0"/>
			<form:options items="${recipe}" itemValue="id" itemLabel="title"/>
		</form:select>
	</div>
	
	<div id="botones" >

		<input type="submit" name="save" value="<spring:message code="qualify.save" />" />&nbsp;  
		
		<input type="button" name="cancel"
			value="<spring:message code="qualify.cancel" />"
			onclick="javascript: window.location.replace('contest/list.do')" />

	</div> 
	
</form:form>