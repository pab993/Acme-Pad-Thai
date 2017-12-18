<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="user/recipes/edit.do" modelAttribute="recipe">
	
	<form:hidden path="id" />
	<form:hidden path="version"/>
	<form:hidden path="ticker"/>
	<form:hidden path="momentAuthored"/>
	<form:hidden path="momentLastUpdate"/>
	<%-- <form:hidden path="pictures"/> --%>
	<form:hidden path="user"/>
	<form:hidden path="steps"/>
	<form:hidden path="comments"/>
	<form:hidden path="unitSystems"/>
	<form:hidden path="likes"/>
	<form:hidden path="qualifications"/>
	
	<div> 
		<form:label path="title">
			<spring:message code="recipe.title" />:
		</form:label>
		
		<form:input path="title" />
		<form:errors cssClass="error" path="title" />
		<br/>
	</div>
	<div> 
		<form:label path="summary">
			<spring:message code="recipe.summary" />:
		</form:label>
		
		<form:input path="summary" />
		<form:errors cssClass="error" path="summary" />
		<br/>
	</div>
	<div> 
		<form:label path="hints">
			<spring:message code="recipe.hints" />:
		</form:label>
		
		<form:input path="hints" />
		<form:errors cssClass="error" path="hints" />
		<br/>
	</div>
	<div> 
		<form:label path="category">
			<spring:message code="recipe.category" />:
		</form:label>
		
		<form:select id="categories" path="category" >
			<form:option value="0" label="----" />
			<form:options items="${categories}" itemValue="id" itemLabel="name" />
		</form:select>
		<form:errors cssClass="error" path="category" />
		<br/>
	</div>
	
	<div> 
		<form:label path="pictures">
			<spring:message code="recipe.pictures.clear" />:
		</form:label>
		
		<form:textarea path="pictures" />
		<form:errors cssClass="error" path="pictures" />
		<br/>
	</div>
	
	<div id="botones" > 

	<input type="submit" name="save" value="<spring:message code="recipe.save" />" />&nbsp;  
	<input type="submit" name="delete" value="<spring:message code="recipe.delete" />" onclick="return confirm('<spring:message code="recipe.confirm.delete" />')" />&nbsp;
	<input type="button" name="cancel"
		value="<spring:message code="recipe.cancel" />"
		onclick="javascript: window.location.replace('welcome/index.do')" />

	</div> 
	
	
</form:form>
