<%--
 * list.jsp
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


<security:authorize access="hasRole('SPONSOR')">
<display:table name="campaigns" id="campaign" pagesize="5" requestURI="${requestURI}" class="displaytag">

		
		<spring:message code="campaign.startdate" var="cstartdate" />
		<display:column title="${cstartdate}" property="startdate" />
		
		<spring:message code="campaign.enddate" var="cenddate" />
		<display:column title="${cenddate}" property="enddate" />
		
		<spring:message code="campaign.bannersnumber" var="cbannersnumber" />
		<display:column title="${cbannersnumber}" property="bannersnumber" />
		
		<spring:message code="campaign.maximumbanners" var="cmaximumbanners"/>
		<display:column title="${cmaximumbanners}" property="maximumbanners"/>
		
		<spring:message code="campaign.starcampaign" var="cstarcampaign"/>
		<display:column title="${cstarcampaign}" property="starcampaign"/>
		
		<display:column>
			<a href="campaign/edit.do?campaignId=${campaign.id}"><spring:message code="campaign.edit" /></a>
		</display:column>
	
</display:table>

<div>
	<a href="campaign/create.do"><spring:message code="campaign.create"/></a>
</div>

</security:authorize>
