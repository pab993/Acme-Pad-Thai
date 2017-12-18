<%--
 * header.jsp
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div>
	<img src="images/logo.png" alt="Acme Pad-Thai Co., Inc." />
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message	code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="configurationSystem/administrator/edit.do"><spring:message code="master.page.configurationSystem.edit" /></a></li>	
					<li><a href="administrator/display.do"><spring:message code="master.page.dashboard" /></a></li>
					<li><a href="category/administrator/list.do"><spring:message code="master.page.list.categories" /></a></li>
					<li><a href="folder/administrator/list.do"><spring:message code="master.page.list.folders" /></a></li>			
					<li><a href="administrator/selectWinners.do"><spring:message code="master.page.select.winners" /></a></li>		
				</ul>
			</li>
			<li><a href="recipe/adminList.do"><spring:message code="master.page.list.of.recipes" /></a></li>
		</security:authorize>
		
		<security:authorize access="hasRole('USER')">
		
			<li><a class="fNiv"><spring:message	code="master.page.user" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="user/edit.do"><spring:message code="master.page.edit.data.user" /></a></li>	
					<li><a href="folder/user/list.do"><spring:message code="master.page.list.folders" /></a></li>
					<li><a href="customer/list.do"><spring:message code="master.page.list.customers" /></a></li>				
				</ul>
			</li>
			<li><a class="fNiv"><spring:message	code="master.page.user.recipes" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="user/recipes/list.do"><spring:message code="master.page.list.recipes.user" /></a></li>					
				</ul>
			</li>
			<li><a href="user/recipes/create.do"><spring:message code="master.page.recipe.create" /></a></li>
			<li><a href="recipe/lastUpdatesList.do"><spring:message code="master.page.list.lastUpdates" /></a></li>
			<li><a href="recipe/list.do"><spring:message code="master.page.list.of.recipes" /></a></li>
			
		</security:authorize>
		
		<security:authorize access="hasRole('NUTRITIONIST')">
			<li><a class="fNiv"><spring:message	code="master.page.nutritionist" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="nutritionist/edit.do"><spring:message code="master.page.edit.data.nutritionist" /></a></li>	
					<li><a href="curriculum/nutritionist/list.do"><spring:message code="master.page.curriculum.list" /></a></li>	
					<li><a href="folder/nutritionist/list.do"><spring:message code="master.page.list.folders" /></a></li>	
					<li><a href="customer/list.do"><spring:message code="master.page.list.customers" /></a></li>	
					<li><a href="property/nutritionist/list.do"><spring:message code="master.page.property.list" /></a></li>
					<li><a href="nutritionist/ingredient/listAll.do"><spring:message code="master.page.ingredient.list" /></a></li>	
				</ul>
			</li>
			<li><a href="recipe/lastUpdatesList.do"><spring:message code="master.page.list.lastUpdates" /></a></li>
			<li><a href="recipe/watch/list.do"><spring:message code="master.page.list.of.recipes" /></a></li>
		</security:authorize>
		
		<security:authorize access="hasRole('SPONSOR')">
			<li><a class="fNiv"><spring:message	code="master.page.sponsor" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="register/sponsor/edit.do"><spring:message code="master.page.edit.data.sponsor" /></a></li>
					<li><a href="campaign/list.do"><spring:message code="master.page.campaign.list" /></a></li>	
					<li><a href="campaign/edit.do"><spring:message code="master.page.campaign.edit" /></a></li>					
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="isAnonymous()">
			<li><a href="security/login.do"><spring:message code="master.page.login" /></a></li>
			<li><a href="register/user/create.do"><spring:message code="master.page.register.user" /></a></li>
			<li><a href="register/nutritionist/create.do"><spring:message code="master.page.register.nutritionist" /></a></li>
			<%-- <li><a href="register/sponsor/create.do"><spring:message code="master.page.register.sponsor" /></a></li> --%>
			<li><a href="user/list.do"><spring:message code="master.page.list.of.users" /></a></li>
			<li><a href="recipe/watch/list.do"><spring:message code="master.page.list.of.recipes" /></a></li>
			<li><a href="contest/list.do"><spring:message code="master.page.list.of.contest" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>	
					<li><a href="user/list.do"><spring:message code="master.page.list.of.users" /></a></li>
					<li><a href="contest/list.do"><spring:message code="master.page.list.of.contest" /></a></li>
					<li><a href="socialIdentity/list.do"><spring:message code="master.page.list.of.socialIdentity" /></a></li>
									
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

