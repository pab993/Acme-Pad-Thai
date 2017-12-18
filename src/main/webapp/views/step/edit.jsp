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

<form:form action="recipe/steps/edit.do" modelAttribute="step">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="recipe" />
	
	<div> 
	
	<form:label path="number">
		<spring:message code="step.number" />:
	</form:label>
	
	<form:input path="number" />
	<form:errors cssClass="error" path="number" />
	<br/>
	
	</div>
	
	<div> 
	
	<form:label path="description">
		<spring:message code="step.description" />:
	</form:label>
	
	<form:input path="description" />
	<form:errors cssClass="error" path="description" />
	<br/>
	
	</div>
	
	<div> 
	
	<form:label path="hints">
		<spring:message code="step.hints" />:
	</form:label>
	
	<form:textarea path="hints" />
	<form:errors cssClass="error" path="hints" />
	<br/>
	
	</div>
	
	<div> 
	
	<form:label path="picture">
		<spring:message code="step.picture" />:
	</form:label>
	
	<form:input path="picture" />
	<form:errors cssClass="error" path="picture" />
	<br/>
	
	</div>
	
	<div id="botones" > 

	<input type="submit" name="save" value="<spring:message code="step.save" />" />&nbsp;  
	<input type="submit" name="delete" value="<spring:message code="step.delete" />" onclick="return confirm('<spring:message code="step.confirm.delete" />')" />&nbsp;
	<input type="button" name="cancel"
		value="<spring:message code="step.cancel" />"
		onclick="javascript: window.location.replace('welcome/index.do')" />

	</div> 
	
</form:form>