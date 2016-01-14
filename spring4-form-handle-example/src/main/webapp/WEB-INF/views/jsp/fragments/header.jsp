<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<head>
<title>Spring MVC Form Handling Example</title>

<spring:url value="/resources/css/main.css" var="mainCSS" />

<link href="${mainCSS}" rel="stylesheet" />

</head>

<spring:url value="/currency_exchange/new" var="urlNewQuery" />
<spring:url value="/currency_exchange/list" var="urlList" />
<spring:url value="/currency_exchange/current" var="urlCurrent" />
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal.id" var="userID" />
	<spring:url value="/users/${userID}/update/" var="urlMyData" />
</sec:authorize>
<nav>
	<ul>
		<sec:authorize access="isAuthenticated()">
			<li class="floatLeft"><a href="${urlCurrent}"><spring:message
						code="Common.CurrentExchangeRate" /></a></li>
		</sec:authorize>

		<sec:authorize access="isAuthenticated()">
			<li class="floatLeft"><a href="${urlList}"><spring:message
						code="Query.Historical" /></a></li>
		</sec:authorize>

		<sec:authorize access="isAuthenticated()">
			<li class="floatLeft"><a href="${urlNewQuery}"><spring:message
						code="Query.NewQuery" /></a></li>
		</sec:authorize>

		<c:if test="${pageContext.response.locale == 'de' }">
			<li class="floatRight"><a href="?lang=en"><img alt="English"
					src="<c:url value="/resources/images/flag_english.png"/>" /></a></li>
		</c:if>

		<c:if test="${pageContext.response.locale == 'en' }">
			<li class="floatRight"><a href="?lang=de"><img alt="Deutsch"
					src="<c:url value="/resources/images/flag_germany.png"/>" /></a></li>
		</c:if>

		<sec:authorize access="isAuthenticated()">
			<li class="floatRight"><a
				href="<c:url value="/perform_logout" />"><spring:message
						code="Common.Logout" /> <span class="entypo-logout"></span></a></li>
		</sec:authorize>

		<sec:authorize access="isAuthenticated()">
			<li class="floatRight"><a href="${urlMyData}"><spring:message
						code="Common.MyData" /></a></li>
		</sec:authorize>
	</ul>
</nav>