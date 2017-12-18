<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="message/edit.do" modelAttribute="ActorMessage">
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="subject" />
		<form:hidden path="body"/>
		<form:hidden path="momentSent"/>
		<form:hidden path="senderActor"/>
		<form:hidden path="recipientActor"/>
		<form:hidden path="priority"/>


			<form:label path="folder">
		<spring:message code="message.folder"/>
	</form:label>
	<form:select id="folders" path="folder">
		<form:option value="0" label="----" />
		<form:options items="${folders}" itemValue="id" itemLabel="name" />
	</form:select>
	<form:errors cssClass="error" path="folder" />
	<br>
<input type="submit" name="save" value="<spring:message code="message.save" />" />&nbsp;
	<jstl:if test="${ActorMessage.id != 0}">
		<input type="submit" name="delete" value="<spring:message code="message.delete" />" onclick="return confirm('<spring:message code="message.confirm.delete" />')" />&nbsp;		</jstl:if>
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