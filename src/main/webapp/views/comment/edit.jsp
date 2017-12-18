<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="comment/edit.do" modelAttribute="comment">
	
	<form:hidden path="id" />
	<form:hidden path="version"/>
	<form:hidden path="actor"/>
	<form:hidden path="momentOfCreation"/>
	<form:hidden path="recipe"/>
	
	<div> 
		<form:label path="title">
			<spring:message code="comment.title" />:
		</form:label>
		
		<form:input path="title" />
		<form:errors cssClass="error" path="title" />
		<br/>
	</div>
	<div> 
		<form:label path="text">
			<spring:message code="comment.text" />:
		</form:label>
		
		<form:textarea path="text" />
		<form:errors cssClass="error" path="text" />
		<br/>
	</div>
	<div> 
		<form:label path="numberOfStars">
			<spring:message code="comment.numberOfStars" />:
		</form:label>
		
		<form:input path="numberOfStars" />
		<form:errors cssClass="error" path="numberOfStars" />
		<br/>
	</div>
	
	<div id="botones" > 

	<input type="submit" name="save" value="<spring:message code="comment.save" />" />&nbsp;  
	<input type="button" name="cancel"
		value="<spring:message code="comment.cancel" />"
		onclick="javascript: window.location.replace('comment/list.do')" />

	</div> 
	
</form:form>