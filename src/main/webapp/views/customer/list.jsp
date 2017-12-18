<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<display:table name="customer" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">

	<spring:message code="actor.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader }" />
	
	<spring:message code="actor.surname" var="surnameHeader" />
	<display:column property="surname" title="${surnameHeader }" />
	
	<spring:message code="actor.phoneNumber" var="phoneNumberHeader" />
	<display:column property="phoneNumber" title="${phoneNumberHeader }" />
	
	<spring:message code="actor.postalAddress" var="postalAddressHeader" />
	<display:column property="postalAddress" title="${postalAddressHeader }" />

	<spring:message code="actor.follow" var="customerFollowHeader"/>
	<display:column title="${customerFollowHeader }">
		<jstl:if test="${row.id != customerVar.id }">
			<a href="customer/editFollow.do?customerId=${row.id }">
				<spring:message code="customer.follow"/>
			</a>
			<br>
			<a href="customer/editUnfollow.do?customerId=${row.id }">
				<spring:message code="customer.unfollow"/>
			</a>
		</jstl:if>
	</display:column>
	
	
</display:table>