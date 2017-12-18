<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<form:form action="bartols/recipe/edit.do" modelAttribute="bartol">

		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="label"/>
		<form:hidden path="nutritionist"/>
		<form:hidden path="recipe"/>
		
	<security:authorize access="hasRole('NUTRITIONIST')">
		<div> 
		<form:label path="title">
			<spring:message code="bartol.title" />:
		</form:label>
		
		<form:input path="title" />
		<form:errors cssClass="error" path="title" />
		<br/>
	</div>
	
	<div> 
		<form:label path="description">
			<spring:message code="bartol.description" />:
		</form:label>
		
		<form:input path="description" />
		<form:errors cssClass="error" path="description" />
		<br/>
	</div>
	
	<div> 
		<form:label path="calories">
			<spring:message code="bartol.calories" />:
		</form:label>
		
		<form:input path="calories" />
		<form:errors cssClass="error" path="calories" />
		<br/>
	</div>
	
	<div> 
		<form:label path="momentDisplay">
			<spring:message code="bartol.momentDisplay" />:
		</form:label>
		
		<form:input path="momentDisplay" />
		<form:errors cssClass="error" path="momentDisplay" />
		<br/>
	</div>
	
	<div id="botones" > 

	<input type="submit" name="save" value="<spring:message code="bartol.save" />" />&nbsp;  
	<jstl:if test="${bartol.id != 0}">
		<input type="submit" name="delete" value="<spring:message code="bartol.delete" />" onclick="return confirm('<spring:message code="bartol.confirm.delete" />')" />&nbsp;
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="bartol.cancel" />"
		onclick="javascript: window.location.replace('welcome/index.do')" />

	</div> 
	
	</security:authorize>
	
</form:form>