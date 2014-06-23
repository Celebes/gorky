
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/commons/taglibs.jsp" %>
<head>
    <title>Strona przykładowa</title>
</head>
<body>
<div class="row-fluid">
    <form:form modelAttribute="exampleForm" cssClass="form-horizontal">
        <div class="control-group">
            <form:label cssClass="control-label" path="id">ID:</form:label>
            <div class="controls">
                <form:input path="id"/>
                <form:errors path="id" cssClass="text-error"/>
            </div>
        </div>
        <div class="control-group">
            <form:label cssClass="control-label" path="text">Text:</form:label>
            <div class="controls">
                <form:input path="text"/>
                <form:errors path="text" cssClass="text-error"/>
            </div>
        </div>
        <div class="control-group">
            <div class="controls">
                <input type="submit" class="btn btn-primary">
            </div>
        </div>
    </form:form>
    <c:if test="${not empty feedback}">
        <span class="text-success">${feedback}</span>
    </c:if>
</div>
</body>
