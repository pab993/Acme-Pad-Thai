<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<form:form action="category/administrator/edit.do" modelAttribute="category">
	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<form:label path="name">
		<spring:message code="category.name" />:
	</form:label>
	<form:input path="name" />
	<form:errors cssClass="error" path="name" />
	<br />
	
	<form:label path="description" >
		<spring:message code="category.description" />:
	</form:label>
	<form:textarea path="description" />
	<form:errors cssClass="error" path="description" />
	<br />
	
	<form:label path="picture">
		<spring:message code="category.picture"/>
	</form:label>
	<form:input path="picture" />
	<form:errors cssClass="error" path="picture" />
	<br />
		<form:label path="oneCategory">
		<spring:message code="category.oneCategory"/>
	</form:label>
	<form:select id="oneCategories" path="oneCategory">
		<form:option value="0" label="----" />
		<form:options items="${oneCategories}" itemValue="id" itemLabel="name" />
	</form:select>
	<form:errors cssClass="error" path="oneCategory" />
	<br>
	<input type="submit" name="save" value="<spring:message code="category.save" />" />&nbsp;
	<jstl:if test="${category.id != 0}">
		<input type="submit" name="delete" value="<spring:message code="category.delete" />" onclick="return confirm('<spring:message code="category.confirm.delete" />')" />&nbsp;
	</jstl:if>
	<input type="button" name="cancel" value="<spring:message code="category.cancel"/>" onclick="javascript: window.location.replace('category/administrator/list.do')"/>
	
</form:form>

