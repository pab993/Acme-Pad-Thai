<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="user/ingredients/add.do" modelAttribute="unitSystem">
	
	<form:hidden path="id" />
	<form:hidden path="version"/>
	<form:hidden path="unit"/>
	<form:hidden path="recipe"/>
	<form:hidden path="quantity"/>
	
	<div> 
		<form:label path="ingredient">
			<spring:message code="unitSystem.ingredient" />:
		</form:label>
		
		<form:select id="ingredients" path="ingredient" >
			<form:option value="0" label="----" />
			<form:options items="${ingredients}" itemValue="id" itemLabel="name" />
		</form:select>
		<form:errors cssClass="error" path="ingredient" />
		<br/>
	</div>

	
	<div id="botones" > 

	<input type="submit" name="save" value="<spring:message code="unitSystem.save" />" />&nbsp;  
	<input type="button" name="cancel"
		value="<spring:message code="unitSystem.cancel" />"
		onclick="javascript: window.location.replace('user/ingredients/list.do?recipeId=${recipeId}')" />

	</div> 
	
</form:form>