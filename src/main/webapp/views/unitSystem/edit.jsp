<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="user/unitSystem/edit.do" modelAttribute="unitSystem">
	
	<form:hidden path="id" />
	<form:hidden path="version"/>
	<form:hidden path="recipe"/>
	<form:hidden path="ingredient"/>
	
	<div> 
		<form:label path="unit">
			<spring:message code="unitSystem.unit" />:
		</form:label>
		
		<form:select path="unit" >
			<form:option value="0" label="----" />
			<form:options items="${units}"/>
		</form:select>
		<form:errors cssClass="error" path="unit" />
		<br/>
	</div>

	<div> 
		<form:label path="quantity">
			<spring:message code="unitSystem.quantity" />:
		</form:label>
		
		<form:input path="quantity" />
		<form:errors cssClass="error" path="quantity" />
		<br/>
	</div>
	
	<div id="botones" > 

	<input type="submit" name="save" value="<spring:message code="unitSystem.save" />" />&nbsp;  
	<input type="button" name="cancel"
		value="<spring:message code="recipe.cancel" />"
		onclick="javascript: window.location.replace('user/ingredients/list.do?recipeId=${recipeId}')" />

	</div> 
	
	
</form:form>
