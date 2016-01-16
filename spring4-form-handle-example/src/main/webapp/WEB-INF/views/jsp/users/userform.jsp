<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

<spring:url value="/users" var="userActionUrl" />

<body>
	<div class="main_small">
		<c:choose>
			<c:when test="${empty userForm.id}">
				<h2>Add User</h2>
			</c:when>
			<c:otherwise>
				<h2>Update User</h2>
			</c:otherwise>
		</c:choose>

		<fieldset>
			<c:if test="${not empty msg}">
				<div class="alert alert-${css} alert-dismissible" role="alert">
					<button type="button" class="close" data-dismiss="alert"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<strong>${msg}</strong>
				</div>
			</c:if>
			<form:form method="post" modelAttribute="userForm"
				action="${userActionUrl}">

				<spring:bind path="name">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<form:input path="name" type="text" id="name" placeholder="Name" />
						<form:errors path="name" class="control-label" />
					</div>
				</spring:bind>

				<spring:bind path="email">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<form:input path="email" class="form-control" id="email"
							placeholder="Email" />
						<form:errors path="email" class="control-label" />
					</div>
				</spring:bind>

				<spring:bind path="dateOfBirth">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<form:input path="dateOfBirth" id="dateOfBirth" placeholder="Date of Birth - 01/01/1990" />
						<form:errors path="dateOfBirth" class="control-label" />
					</div>
				</spring:bind>

				<spring:bind path="password">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<form:password path="password" class="form-control" id="password"
							placeholder="password" showPassword="true" />
						<form:errors path="password" class="control-label" />
					</div>
				</spring:bind>

				<spring:bind path="confirmPassword">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<form:password path="confirmPassword" class="form-control"
							id="password" placeholder="repeat password" showPassword="true" />
						<form:errors path="confirmPassword" class="control-label" />
					</div>
				</spring:bind>

				<spring:bind path="street">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<form:input path="street" class="form-control" id="street"
							placeholder="street" />
						<form:errors path="street" class="control-label" />
						<div class="col-sm-5"></div>
					</div>
				</spring:bind>

				<spring:bind path="zipCode">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<form:input path="zipCode" class="form-control" id="zipCode"
							placeholder="zipCode" />
						<form:errors path="zipCode" class="control-label" />
						<div class="col-sm-5"></div>
					</div>
				</spring:bind>

				<spring:bind path="city">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<form:input path="city" class="form-control" id="city"
							placeholder="city" />
						<form:errors path="city" class="control-label" />
						<div class="col-sm-5"></div>
					</div>
				</spring:bind>

				<spring:bind path="country">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<form:select path="country" class="form-control">
							<form:option style="witdh:300px" value="NONE" label="Country" />
							<form:options items="${countryList}" />
						</form:select>
						<form:errors path="country" class="control-label" />
					</div>
				</spring:bind>

				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<c:choose>
							<c:when test="${empty userForm.id}">
								<button type="submit" class="btn-lg btn-primary pull-right">Add</button>
							</c:when>
							<c:otherwise>
								<button type="submit" class="btn-lg btn-primary pull-right">Update</button>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</form:form>
		</fieldset>
	</div>

	<jsp:include page="../fragments/footer.jsp" />

</body>

</html>