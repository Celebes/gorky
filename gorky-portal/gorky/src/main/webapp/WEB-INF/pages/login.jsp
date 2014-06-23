
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/pages/commons/taglibs.jsp"%>
<head>
<title>Strona logowania</title>
</head>
<div class="row-fluid">
		<form:form modelAttribute="loggedForm" cssClass="form-horizontal">
			<div class="control-group">
				<form:label cssClass="control-label" path="login">*Login:</form:label>
				<div class="controls">
					<form:input path="login" />
					<form:errors path="login" cssClass="text-error" />
				</div>
			</div>
			<div class="control-group">
				<form:label cssClass="control-label" path="password">*Hasło</form:label>
				<div class="controls">
					<form:input type="password" path="password" />
					<form:errors path="password" cssClass="text-error" />
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<button type="submit" class="btn btn-success">Zaloguj się</button>
				</div>
			</div>
		</form:form>
		<c:if test="${not empty feedback}">
			<span class="text-success">${feedback}</span>
		</c:if>
	</div>
</body>
</html>