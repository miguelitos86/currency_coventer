<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

<body>
	<div id="main">



		<h2>All Users</h2>

		<fieldset>
			<c:if test="${not empty msg}">
				<div class="msg" role="alert">
					<button type="button" data-dismiss="alert" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<strong>${msg}</strong>
				</div>
			</c:if>
			<table>
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Email</th>
					<th>framework</th>
					<th>Action</th>
				</tr>

				<c:forEach var="user" items="${users}">
					<tr>
						<td>${user.id}</td>
						<td>${user.name}</td>
						<td>${user.email}</td>
						<td>Spring</td>
						<td><spring:url value="/users/${user.id}" var="userUrl" /> <spring:url
								value="/users/${user.id}/delete" var="deleteUrl" /> <spring:url
								value="/users/${user.id}/update" var="updateUrl" />

							<button onclick="location.href='${userUrl}'">Query</button>
							<button onclick="location.href='${updateUrl}'">Update</button>
							<button onclick="this.disabled=true;post('${deleteUrl}')">Delete</button></td>
					</tr>
				</c:forEach>
			</table>
		</fieldset>
	</div>

	<jsp:include page="../fragments/footer.jsp" />

</body>
</html>