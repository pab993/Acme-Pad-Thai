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

<form:form action="socialIdentity/edit.do" modelAttribute="socialIdentity">

	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="actor" />
	
	
	<form:label path="nick">
		<spring:message code="nutritionist.socialIdentity.nick"/>
	</form:label>
	<form:input path="nick"/>
	<form:errors cssClass="error" path="nick"/>

	<br/>
	
	<form:label path="socialNetwork">
		<spring:message code="nutritionist.socialIdentity.socialNetwork"/>
	</form:label>
	<form:input path="socialNetwork"/>
	<form:errors cssClass="error" path="socialNetwork"/>
	
	<br/>
	
	<form:label path="link">
		<spring:message code="nutritionist.socialIdentity.link"/>
	</form:label>
	<form:input path="link"/>
	<form:errors cssClass="error" path="link"/>
	
	<br/>
	
	<form:label path="picture">
		<spring:message code="nutritionist.socialIdentity.picture"/>
	</form:label>
	<form:input path="picture"/>
	<form:errors cssClass="error" path="picture"/>
	
	<!-- BOTONES -->
	
	<br/>
	
	<input type="submit" name="save" value="<spring:message code="nutritionist.save" />" />&nbsp;
	<input type="submit" name="delete" value="<spring:message code="socialIdentity.delete" />" />&nbsp;
	<input type="button" name="cancel"
		value="<spring:message code="user.cancel" />"
		onclick="javascript: window.location.replace('welcome/index.do')" />

</form:form>