<%--
 * action-1.jsp
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="register/sponsor/edit.do" modelAttribute="sponsor">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="userAccount.authorities" value="SPONSOR" />
	<form:hidden path="creditCards" />
	<form:hidden path="bills" />
	<form:hidden path="campaigns" />
	<form:hidden path="socialIdentities" />
	<form:hidden path="folders" />
	<form:hidden path="senders" />
	<form:hidden path="recipients" />
	<form:hidden path="commentsActor" />
	
	
	<fieldset> 
	
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
	
	
	<div> 
	
	<form:label path="userAccount.username">
		<spring:message code="userAccount.username" />:
	</form:label>
	<form:input path="userAccount.username" />
	<form:errors cssClass="error" path="userAccount.username" />
	<br />
	
	</div>
	
	<div> 
	
	<form:label path="userAccount.password">
		<spring:message code="userAccount.password" />:
	</form:label>
	<form:password path="userAccount.password" />
	<form:errors cssClass="error" path="userAccount.password" />
	<br />
	
	</div>
	
	<div> 
	
	<form:label path="company">
		<spring:message code="sponsor.company" />:
	</form:label>
	<form:input path="company" />
	<form:errors cssClass="error" path="company" />
	<br />
	
	</div>
	
	</fieldset>
	
	
	<div id="botones" > 

	<input  type="submit" name="save" value="<spring:message code="sponsor.save" />" />&nbsp;  
	
	<input type="button" name="cancel"
		value="<spring:message code="sponsor.cancel" />"
		onclick="javascript: window.location.replace('welcome/index.do')" />

	</div> 


</form:form>
