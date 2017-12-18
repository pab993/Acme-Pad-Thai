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

<display:table name="messages" id="row" requestURI="${requestURI }" pagesize="5" class="displaytag">

	
	<spring:message code="message.momentSent" var="momentSent" />
	<display:column property="momentSent" title="${momentSent}" format="{0,date,dd/MM/yyyy HH:mm}" />
	
	<spring:message code="message.subject" var="subject" />
	<display:column property="subject" title="${subject }" />
	
	<spring:message code="message.body" var="body" />
	<display:column property="body" title="${body}" />
	
	<spring:message code="message.priority" var="priority" />
	<display:column property="priority" title="${priority}" />
	<spring:message code="message.edit" var="ActorMessage"/>
		<display:column title="${ActorMessage}">
			<a href="message/edit.do?messageId=${row.id}">
				<spring:message code="message.edit"/>
			</a>
	</display:column>
	
</display:table>
