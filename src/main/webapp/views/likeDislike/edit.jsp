<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="customer/recipe/like.do" modelAttribute="likeDislike">
	
	<form:hidden path="id" />
	<form:hidden path="version"/>
	<form:hidden path="recipe"/>
	<form:hidden path="customer"/>

	<div> 
		<form:label path="likeOrDislike">
			<spring:message code="likeDislike.likeDislike" />:
		</form:label>
		
		<form:select path="likeOrDislike">
			<form:option label="-----" value="0"/>
			
			<form:option label="Like" value="${test1}"/>
			<form:option label="Dislike" value="${test2}"/>
				
		</form:select>
		<form:errors cssClass="error" path="likeOrDislike" />
		<br/>
	</div>
	
	<div id="botones" > 

	<input type="submit" name="save" value="<spring:message code="likeDislike.save" />" />&nbsp;
	<input type="submit" name="delete" value="<spring:message code="likeDislike.delete" />" onclick="return confirm('<spring:message code="likeDislike.confirm.delete" />')" />&nbsp; 
	<input type="button" name="cancel"
		value="<spring:message code="likeDislike.cancel" />"
		onclick="javascript: window.location.replace('recipe/list.do')" />

	</div> 
	
</form:form>