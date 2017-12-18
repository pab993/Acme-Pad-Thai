<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="message/send.do" modelAttribute="ActorMessage">
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="subject" />
		<form:hidden path="body"/>
		<form:hidden path="momentSent"/>
		<form:hidden path="senderActor"/>
		<form:hidden path="folder"/>

	<form:label path="subject">
		<spring:message code="message.subject" />:
	</form:label>
	<form:input path="subject" />
	<form:errors cssClass="error" path="subject" />
	<br />
	
	<form:label path="body" >
		<spring:message code="message.body" />:
	</form:label>
	<form:textarea path="body" />
	<form:errors cssClass="error" path="body" />
	<br />
			<form:label path="priority">
		<spring:message code="message.priority"/>
	</form:label>
	<form:select id="priorities" path="priority">
		<form:option value="0" label="----" />
		<form:option value="LOW" label="LOW" />
		<form:option value="MEDIUM" label="MEDIUM" />
		<form:option value="HIGH" label="HIGH" />
		</form:select>
	<form:errors cssClass="error" path="priority" />
	<br>
		<form:label path="recipientActor">
		<spring:message code="message.recipientActor"/>
	</form:label>
	<form:select id="recipientActors" path="recipientActor">
		<form:option value="0" label="----" />
		<form:options items="${recipientActors}" itemValue="id" itemLabel="name" />
	</form:select>
	<br>
<input type="submit" name="send" value="<spring:message code="message.save" />" />&nbsp;
	<security:authorize access="hasRole('USER')">
	<input type="button" name="cancel" value="<spring:message code="message.cancel"/>" onclick="javascript: window.location.replace('folder/user/list.do')"/>
		</security:authorize>
	<security:authorize access="hasRole('NUTRITIONIST')">
		<input type="button" name="cancel" value="<spring:message code="message.cancel"/>" onclick="javascript: window.location.replace('folder/nutritionist/list.do')"/>
			</security:authorize>
		<security:authorize access="hasRole('ADMIN')">
			<input type="button" name="cancel" value="<spring:message code="message.cancel"/>" onclick="javascript: window.location.replace('folder/administrator/list.do')"/>
			</security:authorize>
	</form:form>