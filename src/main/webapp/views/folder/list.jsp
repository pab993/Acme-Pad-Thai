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

<display:table name="folders" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">

	<spring:message code="folder.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader }" />

	<%-- <spring:message code="folder.messages" var="editHeader"/>
	<display:column title="${editHeader }">
		<a href="">
			<spring:message code="folder.messages"/>
		</a>
	</display:column> --%>
	
	<security:authorize access="hasRole('USER')">
		<spring:message code="user.folder.edit" var="userFolder"/>
		<display:column title="${userFolder}">
		<jstl:if test="${!((row.name == inbox)||(row.name == spambox)||(row.name == outbox)||(row.name == trashbox))}">
			<a href="folder/user/edit.do?folderId=${row.id}">
				<spring:message code="user.folder.edit"/></a>
				</jstl:if>
	</display:column>
			
	<spring:message code="user.folder.display" var="userFolder"/>
		<display:column title="${userFolder}">
			<a href="message/display.do?folderId=${row.id}">
				<spring:message code="user.folder.display"/>
			</a>
	</display:column>
		<div>
		<a href="folder/user/create.do"><spring:message code="folder.create"/></a>
	</div>
	</security:authorize>
	
		<security:authorize access="hasRole('NUTRITIONIST')">
		<spring:message code="nutritionist.folder.edit" var="nutritionistFolder"/>
		<display:column title="${nutritionistFolder}">
			<jstl:if test="${!((row.name == inbox)||(row.name == spambox)||(row.name == outbox)||(row.name == trashbox))}">
			<a href="folder/nutritionist/edit.do?folderId=${row.id}">
				<spring:message code="nutritionist.folder.edit"/>
			</a>
			</jstl:if>
			</display:column>
		<spring:message code="nutritionist.folder.display" var="nutritionistFolder"/>
		<display:column title="${nutritionistFolder}">
			<a href="message/display.do?folderId=${row.id}">
				<spring:message code="user.folder.display"/>
			</a>
	</display:column>
	<div>
		<a href="folder/nutritionist/create.do"><spring:message code="folder.create"/></a>
	</div>
	</security:authorize>
	
		<security:authorize access="hasRole('ADMIN')">
		<spring:message code="administrator.folder.edit" var="administratorFolder"/>
		<display:column title="${administratorFolder}">
		<jstl:if test="${!((row.name == inbox)||(row.name == spambox)||(row.name == outbox)||(row.name == trashbox))}">
			<a href="folder/administrator/edit.do?folderId=${row.id}">
				<spring:message code="administrator.folder.edit"/>
			</a>
			</jstl:if>
			</display:column>
			<spring:message code="administrator.folder.display" var="administratorFolder"/>
		<display:column title="${administratorFolder}">
			<a href="message/display.do?folderId=${row.id}">
				<spring:message code="user.folder.display"/>
			</a>
	</display:column>
	</security:authorize>
	
	
	
	
</display:table>


<security:authorize access="hasRole('ADMIN')">
	<div>
		<a href="folder/administrator/create.do"><spring:message code="folder.create"/></a>
	</div>
</security:authorize>

<security:authorize access="hasRole('USER')">
	<div>
		<a href="folder/user/create.do"><spring:message code="folder.create"/></a>
	</div>
</security:authorize>

<security:authorize access="hasRole('NUTRITIONIST')">
	<div>
		<a href="folder/nutritionist/create.do"><spring:message code="folder.create"/></a>
	</div>
</security:authorize>
	<div>
		<a href="message/send.do">
				<spring:message code="message.send"/></a>
		</div>