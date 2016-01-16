<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

<body>
	<div class="main_big">
		<h2>Current Exchange Rates</h2>

		<table>
			<tr>
				<th>Origin Currency : <select>
						<option value="USD">USD</option>
				</select></th>
				<th>1.00 USD</th>
				<th>inv. 1.00 USD</th>
			</tr>

			<c:forEach var="current" items="${current}">
				<tr>
					<td>${current.target}</td>
					<td>${current.rate}</td>
					<td><fmt:formatNumber type="number" maxFractionDigits="6"
							value="${1/current.rate}" /></td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<jsp:include page="../fragments/footer.jsp" />

</body>
</html>