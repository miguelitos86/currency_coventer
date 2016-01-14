<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div>
	<footer>
		<h1>
			Contact: Miguel del Prado | <a class="link"
				href="mailto:m.delpradoaranda@gmail.com">m.delpradoaranda@gmail.com</a>

		</h1>
	</footer>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<spring:url value="/resources/js/hello.js" var="coreJs" />
<spring:url value="/resources/js/bootstrap.min.js" var="bootstrapJs" />

<script src="${coreJs}"></script>
<script src="${bootstrapJs}"></script>