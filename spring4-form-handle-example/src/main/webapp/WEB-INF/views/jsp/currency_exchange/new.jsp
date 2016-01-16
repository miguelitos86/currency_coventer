<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />
<spring:url value="/currency_exchange/new" var="urlNew" />

<body>
	<div class="main_big">
		<h2>
			<spring:message code="Query.NewQuery" />
		</h2>
		<fieldset>

			<form:form method="post" modelAttribute="queryForm"
				action="${urlNew}">

				<p>
					<form:input type="number" path="quantityOrigin" id="quantityOrigin"
						step="any" min="0"/>
					<form:select path="originCurrency" id="originCurrency">
						<form:options items="${originCurrencyList}" />
					</form:select>

					<form:errors path="originCurrency" class="control-label" />
				</p>
				<p>
					<form:input type="number" path="exchangeRate" id="exchangeRate"
						step="any" min="0"/>
					<form:select name="destinationCurrency" path="destinationCurrency" onchange="submit()">
						<form:options items="${destinationCurrenyList}" />
					</form:select>
					<form:errors path="destinationCurrency" class="control-label" />
				</p>
				<p>
					<button name="add" value="add" type="submit" style="width: 100px; padding: 10px;">Add</button>
				</p>
			</form:form>

			<c:if test="${not empty msg}">
				<div class="alert alert-${css}">
					<strong>${msg}</strong>
				</div>
			</c:if>
		</fieldset>
	</div>

	<jsp:include page="../fragments/footer.jsp" />

	<script type="text/javascript">
		$(document).ready(function() {
			var rate = ${rate};
			$("#quantityOrigin").change(function() {
				var total = $('#quantityOrigin').val() * rate;
				$('#exchangeRate').val(total);
			});

			$("#exchangeRate").change(function() {
				var total = $('#exchangeRate').val() / rate;
				$('#quantityOrigin').val(total);
			});
		});
	</script>

</body>
</html>