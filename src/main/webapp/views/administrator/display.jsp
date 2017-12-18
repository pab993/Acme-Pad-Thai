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

<security:authorize access="hasRole('ADMIN')">
		<div>
			<display:table name="maxRecipes" id="maxRecipe" pagesize="5" requestURI="${requestURI}" class="displaytag">
				<spring:message code="dashboard.max.recipes" var="dash1" />
				<display:column title="${dash1}">
					<jstl:out value="${maxRecipe}"/>
				</display:column>
			</display:table>
		</div>
		<div>
			<display:table name="minRecipes" id="minRecipe" pagesize="5" requestURI="${requestURI}" class="displaytag">
				
				<spring:message code="dashboard.min.recipes" var="dash2" />
				<display:column title="${dash2}">
					<jstl:out value="${minRecipe}"/>
				</display:column>
				
			</display:table>
		</div>
		<div>
			<display:table name="avgRecipes" id="avgRecipe" pagesize="5" requestURI="${requestURI}" class="displaytag">
				
				<spring:message code="dashboard.avg.recipes" var="dash3" />
				<display:column title="${dash3}">
					<jstl:out value="${avgRecipe}"/>
				</display:column>
				
			</display:table>
		</div>
		
		<div>
			<display:table name="moreAuthoreds" id="moreAuthoreds" pagesize="5" requestURI="${requestURI}" class="displaytag">
				
				<spring:message code="dashboard.more.authoreds" var="dash9" />
				<display:column title="${dash9}">
					<jstl:out value="${moreAuthoreds.userAccount.username}"/>
				</display:column>
				
			</display:table>
		</div>
		
		<div>
			<display:table name="maxRecipesPerContest" id="maxRecipesPerContest" pagesize="5" requestURI="${requestURI}" class="displaytag">
				
				<spring:message code="dashboard.max.recipes.per.contest" var="dash10" />
				<display:column title="${dash10}">
					<jstl:out value="${maxRecipesPerContest}"/>
				</display:column>
				
			</display:table>
		</div>
		
		<div>
			<display:table name="minRecipesPerContest" id="minRecipesPerContest" pagesize="5" requestURI="${requestURI}" class="displaytag">
				
				<spring:message code="dashboard.min.recipes.per.contest" var="dash11" />
				<display:column title="${dash11}">
					<jstl:out value="${minRecipesPerContest}"/>
				</display:column>
				
			</display:table>
		</div>
		
		<div>
			<display:table name="avgRecipesPerContest" id="avgRecipesPerContest" pagesize="5" requestURI="${requestURI}" class="displaytag">
				
				<spring:message code="dashboard.avg.recipes.per.contest" var="dash12" />
				<display:column title="${dash12}">
					<jstl:out value="${avgRecipesPerContest}"/>
				</display:column>
				
			</display:table>
		</div>
		
		<div>
			<display:table name="contestWithMore" id="contestWithMore" pagesize="5" requestURI="${requestURI}" class="displaytag">
				
				<spring:message code="dashboard.more.contest" var="dash4" />
				<display:column title="${dash4}">
					<jstl:out value="${contestWithMore.title}"/>
				</display:column>
				
			</display:table>
		</div>
		<div>
			<display:table name="avgSteps" id="avgStep" pagesize="5" requestURI="${requestURI}" class="displaytag">
				
				<spring:message code="dashboard.avg.step" var="dash5" />
				<display:column title="${dash5}">
					<jstl:out value="${avgStep}"/>
				</display:column>
				
			</display:table>
		</div>
		<div>
			<display:table name="sdSteps" id="sdStep" pagesize="5" requestURI="${requestURI}" class="displaytag">
				
				<spring:message code="dashboard.sd.step" var="dash6" />
				<display:column title="${dash6}">
					<jstl:out value="${sdStep}"/>
				</display:column>
				
			</display:table>
		</div>
		
		<div>
			<display:table name="avgIngredient" id="avgIngredient" pagesize="5" requestURI="${requestURI}" class="displaytag">
				
				<spring:message code="dashboard.avg.ingredient" var="dash13" />
				<display:column title="${dash13}">
					<jstl:out value="${avgIngredient}"/>
				</display:column>
				
			</display:table>
		</div>
		<div>
			<display:table name="sdIngredient" id="sdIngredient" pagesize="5" requestURI="${requestURI}" class="displaytag">
				
				<spring:message code="dashboard.sd.ingredient" var="dash14" />
				<display:column title="${dash14}">
					<jstl:out value="${sdIngredient}"/>
				</display:column>
				
			</display:table>
		</div>
		
		<div>
			<display:table name="popularUsers" id="popularUsers" pagesize="5" requestURI="${requestURI}" class="displaytag">
				
				<spring:message code="dashboard.popular.users" var="dash7" />
				<display:column title="${dash7}">
					<jstl:out value="${popularUsers.userAccount.username}"/>
				</display:column>
				
			</display:table>
		</div>
		<div>
			<display:table name="likeUsers" id="likeUsers" pagesize="5" requestURI="${requestURI}" class="displaytag">
				
				<spring:message code="dashboard.like.users" var="dash8" />
				<display:column title="${dash8}">
					<jstl:out value="${likeUsers.userAccount.username}"/>
				</display:column>
				
			</display:table>
		</div>
		
		<%-- <div>
			<display:table name="minCampaigns" id="minCampaign" pagesize="5" requestURI="${requestURI}" class="displaytag">
				
				<spring:message code="dashboard.min.campaigns" var="dash9" />
				<display:column title="${dash9}">
					<jstl:out value="${minCampaign}"/>
				</display:column>
				
			</display:table>
		</div>
		<div>
			<display:table name="maxCampaigns" id="maxCampaign" pagesize="5" requestURI="${requestURI}" class="displaytag">
				
				<spring:message code="dashboard.max.campaigns" var="dash10" />
				<display:column title="${dash10}">
					<jstl:out value="${maxCampaign}"/>
				</display:column>
				
			</display:table>
		</div>
		<div>
			<display:table name="avgCampaigns" id="avgCampaign" pagesize="5" requestURI="${requestURI}" class="displaytag">
				
				<spring:message code="dashboard.avg.campaigns" var="dash11" />
				<display:column title="${dash11}">
					<jstl:out value="${avgCampaign}"/>
				</display:column>
				
			</display:table>
		</div>
		<div>
			<display:table name="avgACampaigns" id="avgACampaign" pagesize="5" requestURI="${requestURI}" class="displaytag">
				
				<spring:message code="dashboard.avg.active.campaigns" var="dash12" />
				<display:column title="${dash12}">
					<jstl:out value="${avgACampaign}"/>
				</display:column>
				
			</display:table>
		</div>
		<div>
			<display:table name="maxACampaigns" id="maxACampaign" pagesize="5" requestURI="${requestURI}" class="displaytag">
				
				<spring:message code="dashboard.max.active.campaigns" var="dash13" />
				<display:column title="${dash13}">
					<jstl:out value="${maxACampaign}"/>
				</display:column>
				
			</display:table>
		</div>
		<div>
			<display:table name="minACampaigns" id="minACampaign" pagesize="5" requestURI="${requestURI}" class="displaytag">
				
				<spring:message code="dashboard.min.active.campaigns" var="dash14" />
				<display:column title="${dash14}">
					<jstl:out value="${minACampaign}"/>
				</display:column>
				
			</display:table>
		</div>
		<div>
			<display:table name="rankingCompanies" id="rankingCompanies" pagesize="5" requestURI="${requestURI}" class="displaytag">
				
				<spring:message code="dashboard.ranking.companies" var="dash15" />
				<display:column title="${dash15}">
					<jstl:out value="${rankingCompanies}"/>
				</display:column>
				
			</display:table>
		</div>
		<div>
			<display:table name="rankingBCompanies" id="rankingBCompanies" pagesize="5" requestURI="${requestURI}" class="displaytag">
				
				<spring:message code="dashboard.ranking.bill.companies" var="dash16" />
				<display:column title="${dash16}">
					<jstl:out value="${rankingBCompanies}"/>
				</display:column>
				
			</display:table>
		</div>
		<div>
			<display:table name="avgPaidBills" id="avgPaidBill" pagesize="5" requestURI="${requestURI}" class="displaytag">
				
				<spring:message code="dashboard.avg.paid.bills" var="dash17" />
				<display:column title="${dash17}">
					<jstl:out value="${avgPaidBill}"/>
				</display:column>
				
			</display:table>
		</div>
		<div>
			<display:table name="avgUnpaidBills" id="avgUnpaidBill" pagesize="5" requestURI="${requestURI}" class="displaytag">
				
				<spring:message code="dashboard.avg.unpaid.bills" var="dash18" />
				<display:column title="${dash18}">
					<jstl:out value="${avgUnpaidBill}"/>
				</display:column>
				
			</display:table>
		</div>
		<div>
			<display:table name="sdPaidBills" id="sdPaidBill" pagesize="5" requestURI="${requestURI}" class="displaytag">
				
				<spring:message code="dashboard.sd.paid.bills" var="dash19" />
				<display:column title="${dash19}">
					<jstl:out value="${sdPaidBill}"/>
				</display:column>
				
			</display:table>
		</div>
		<div>
			<display:table name="sdUnpaidBills" id="sdUnpaidBill" pagesize="5" requestURI="${requestURI}" class="displaytag">
				
				<spring:message code="dashboard.sd.unpaid.bills" var="dash20" />
				<display:column title="${dash20}">
					<jstl:out value="${sdUnpaidBill}"/>
				</display:column>
				
			</display:table>
		</div>
		<div>
			<display:table name="inactiveSponsors" id="inactiveSponsor" pagesize="5" requestURI="${requestURI}" class="displaytag">
				
				<spring:message code="dashboard.inactive.sponsor" var="dash21" />
				<display:column title="${dash21}">
					<jstl:out value="${inactiveSponsor.name}"/>
				</display:column>
				
			</display:table>
		</div>
		<div>
			<display:table name="lessThanAvgSponsor" id="lessThanAvgSponsor" pagesize="5" requestURI="${requestURI}" class="displaytag">
				
				<spring:message code="dashboard.less.avg.sponsor" var="dash22" />
				<display:column title="${dash22}">
					<jstl:out value="${lessThanAvgSponsor}"/>
				</display:column>
				
			</display:table>
		</div>
		<div>
			<display:table name="ninePerCent" id="ninePerCent" pagesize="5" requestURI="${requestURI}" class="displaytag">
				
				<spring:message code="dashboard.ninepercent.sponsor" var="dash23" />
				<display:column title="${dash23}">
					<jstl:out value="${ninePerCent}"/>
				</display:column>
				
			</display:table>
		</div> --%>
		
</security:authorize>