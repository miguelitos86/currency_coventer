<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />
<spring:url value="/users/add" var="urlAddUser" />

<body>
	<div id="login">
		<h2>
			<span class="fontawesome-lock"></span>Sign In
		</h2>

		<form action="j_spring_security_check" method="post">

			<fieldset>
				<c:if test="${error eq true}">
					<div class="error" role="error">error login</div>
				</c:if>
				<c:if test="${logout eq true}">
					<div class="msg">logout success</div>
				</c:if>



				<p>
					<span class="entypo-mail"> <input type="email" class="user"
						placeholder="email@example.com"
						name="username" required/></span>
				</p>

				<p>
					<span class="entypo-key inputPassIcon"> <input
						type="password" class="pass"
						placeholder="<spring:message code="Common.Password" />"
						name="password" required/></span>
				</p>

				<p>
					<input type="submit" value="Sign In">
				</p>

				<a class="link floatRight" href="${urlAddUser}"><span class="entypo-user-add"></span> <spring:message
						code="Common.Register" /></a>
			</fieldset>
		</form>
	</div>
	
	<jsp:include page="../fragments/footer.jsp" />
</body>

</html>