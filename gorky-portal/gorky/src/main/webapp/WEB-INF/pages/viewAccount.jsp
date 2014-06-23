
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/pages/commons/taglibs.jsp"%>


<head>
<title>Podgląd danych uzytkownika</title>
</head>
<body>
	<script type="text/javascript">
		jQuery(document).ready(function($) {
			$("#viewAccount :input").prop("disabled", true);
		});
	</script>
	
	<fieldset class="nestedFieldset">
        		<legend>
            		Podgląd danych konta
        		</legend>
	<div class="row-fluid">
		<form:form modelAttribute="userViewForm" cssClass="form-horizontal"
			id="viewAccount">
			<div class="control-group">
				<form:label cssClass="control-label" path="name">*Imię:</form:label>
				<div class="controls">
					<form:input path="name" />
					<form:errors path="name" cssClass="text-error" />
				</div>
			</div>
			<div class="control-group">
				<form:label cssClass="control-label" path="surname">*Nazwisko:</form:label>
				<div class="controls">
					<form:input path="surname" />
					<form:errors path="surname" cssClass="text-error" />
				</div>
			</div>
			<div class="control-group">
				<form:label cssClass="control-label" path="login">*Login:</form:label>
				<div class="controls">
					<form:input path="login" />
					<form:errors path="login" cssClass="text-error" />
				</div>
			</div>
			<div class="control-group">
				<form:label cssClass="control-label" path="email">*Email:</form:label>
				<div class="controls">
					<form:input path="email" />
					<form:errors path="email" cssClass="text-error" />
				</div>
			</div>
			<div class="control-group">
				<form:label cssClass="control-label" path="old">Wiek:</form:label>
				<div class="controls">
					<form:input path="old" />
					<form:errors path="old" cssClass="text-error" />
				</div>
			</div>
			<div class="control-group">
				<form:label cssClass="control-label" path="country">Kraj:</form:label>
				<div class="controls">
					<form:input path="country" />
					<form:errors path="country" cssClass="text-error" />
				</div>
			</div>
			<div class="control-group">
				<form:label cssClass="control-label" path="city">Miasto:</form:label>
				<div class="controls">
					<form:input path="city" />
					<form:errors path="city" cssClass="text-error" />
				</div>
			</div>
			<div class="control-group">
				<form:label cssClass="control-label" path="description">Zainteresowania:</form:label>
				<div class="controls">
				<form:textarea path="description" id="opis" rows="3" style="overflow:hidden;" cssErrorClass="input-error"/>
				</div>
			</div>
		</form:form>
		<c:if test="${not empty feedback}">
			<span class="text-success">${feedback}</span>
		</c:if>
	</div>
	</fieldset>
</body>
