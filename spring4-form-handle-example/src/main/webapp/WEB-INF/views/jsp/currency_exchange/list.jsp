<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

<body>
	<div id="main">
		<h2>Historical of Queries</h2>
		
		<c:choose>
			<c:when test="${not empty list}">
				<table>
					<tr>
						<th>created Date</th>
						<th>origin Currency</th>
						<th>destination Currency</th>
						<th>exchange Rate</th>
						<th>action</th>
					</tr>

					<c:forEach var="element" items="${list}">
						<tr>
							<td>${element.createdDate}</td>
							<td>${element.originCurrency}</td>
							<td>${element.destinationCurrency}</td>
							<td>${element.exchangeRate}</td>
							<td><spring:url
									value="/currency_exchange/${element.id}/delete" var="deleteUrl" />
								<button class="delete"
									onclick="this.disabled=true;post('${deleteUrl}')">Delete</button></td>
						</tr>
					</c:forEach>
				</table>
			</c:when>
			<c:otherwise>
				<fieldset>
					<p>No Queries Found</p>
				</fieldset>
			</c:otherwise>
		</c:choose>
	</div>

	<jsp:include page="../fragments/footer.jsp" />

</body>
</html>