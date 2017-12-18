<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="user/edit.do" modelAttribute="user">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="userAccount"/>
	<form:hidden path="userAccount.authorities" value="USER" />
	<form:hidden path="recipes" />
	<form:hidden path="socialIdentities" />
	<form:hidden path="folders" />
	<form:hidden path="senders" />
	<form:hidden path="recipients" />
	<form:hidden path="commentsActor" />
	<form:hidden path="followers" />
	<form:hidden path="followeds" />
	<form:hidden path="likes" />
	
	<fieldset > 
	
	<legend> <spring:message code="datos.personal" /> </legend>
	
	<div> 
	
	<form:label path="name">
		<spring:message code="actor.name" />:
	</form:label>
	
	<form:input path="name" />
	<form:errors cssClass="error" path="name" />
	<br/>
	
	</div>
	
	<div> 

	<form:label path="surname">
		<spring:message code="actor.surname" />:
	</form:label>
	<form:input path="surname" />
	<form:errors cssClass="error" path="surname" />
	<br/>
	
	</div>
	
	<div> 

	<form:label path="phoneNumber">
		<spring:message code="actor.phoneNumber" />:
	</form:label>
	<form:input path="phoneNumber" />
	<form:errors cssClass="error" path="phoneNumber" />
	<br/>
	
	</div>
	
	<div> 
	
	<form:label path="postalAddress">
		<spring:message code="actor.postalAddress" />:
	</form:label>
	<form:input path="postalAddress" />
	<form:errors cssClass="error" path="postalAddress" />
	<br/>
	
	</div>
	
	</fieldset>
	
	
	<div id="botones" > 

	<input type="submit" name="save" value="<spring:message code="user.save" />" />&nbsp;  
	
	<input type="button" name="cancel"
		value="<spring:message code="user.cancel" />"
		onclick="javascript: window.location.replace('welcome/index.do')" />

	</div> 


</form:form>
