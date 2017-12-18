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


<display:table name="socialIdentities" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">

	<spring:message code="nutritionist.socialIdentity.nick" var="nickHeader" />
	<display:column property="nick" title="${nickHeader }" />
	
	<spring:message code="nutritionist.socialIdentity.socialNetwork" var="socialNetworkHeader" />
	<display:column property="socialNetwork" title="${socialNetworkHeader }" />
	
	<spring:message code="nutritionist.socialIdentity.link" var="linkHeader" />
	<display:column property="link" title="${linkHeader }" />
	
	<spring:message code="nutritionist.socialIdentity.picture" var="pictureHeader" />
	<display:column title="${pictureHeader }">
		<img src="${row.picture }" alt="${row.picture }" width="30" height="30">
	</display:column>
	
	<display:column>
		<a href="socialIdentity/edit.do?socialIdentityId=${row.id }">
			<spring:message code="socialIdentity.edit"/>
		</a>
	</display:column>
	
</display:table>

<a href="socialIdentity/create.do">
	<spring:message code="nutritionist.socialIdentity.create"/>
</a>