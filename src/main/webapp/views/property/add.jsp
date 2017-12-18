<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="nutritionist/ingredient/properties/add.do" modelAttribute="possesion">
	
	<form:hidden path="id" />
	<form:hidden path="version"/>
	<form:hidden path="ingredient"/>
	
	<div> 
		<form:label path="property">
			<spring:message code="property.name" />:
		</form:label>
		
		<form:select id="properties" path="property" >
			<form:option value="0" label="----" />
			<form:options items="${properties}" itemValue="id" itemLabel="name" />
		</form:select>
		<form:errors cssClass="error" path="property" />
		<br/>
	</div>
	
	<div> 
		<form:label path="quantity">
			<spring:message code="property.quantity" />:
		</form:label>
		
		<form:input path="quantity" />
		<form:errors cssClass="error" path="quantity" />
		<br/>
	</div>
	
	<div id="botones" > 

	<input type="submit" name="save" value="<spring:message code="property.save" />" />&nbsp;  
	<input type="button" name="cancel"
		value="<spring:message code="property.cancel" />"
		onclick="javascript: window.location.replace('nutritionist/ingredient/properties/listOfProperties.do?ingredientId=${ingredientId}')" />

	</div> 
	
</form:form>