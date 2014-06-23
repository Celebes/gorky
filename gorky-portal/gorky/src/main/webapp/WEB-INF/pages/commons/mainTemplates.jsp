<%--
	Author: Kamil Szokaluk (kamil.szokaluk@execon.pl)
	Created: 18.09.13
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/commons/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>GORKY:: <dec:title/></title>
    <script type="text/javascript" src="<c:url value="/js/jquery-1.10.2.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/js/bootstrap.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/js/display-tag-ajax.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/js/jquery.countdown.min.js"/>"></script>

    <script type="text/javascript" src="<c:url value="/js/main.js"/>"></script>

    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>">
        <!-- Custom styles for this template -->
    <%-- <link rel="stylesheet" href="<c:url value="/css/main.css"/>"> --%>
        <!-- Custom styles for this template -->
    <link rel="stylesheet" href="<c:url value="/css/navbar-static-top.css"/>">
    <link href="css/navbar-static-top.css" rel="stylesheet">

</head>
<body>
<script type="text/javascript">
    var sessionTimeout = ${pageContext.request.session.maxInactiveInterval};
</script>
<div class="container">
    <%--HEADERS--%>
    <div class="row-fluid ex-header-row">
    </div>
    <%--MENU--%>
    <div class="row-fluid">
        <%--PAGE Content--%>
        <div class="span12">
            <div class="ex-well-wrapper">
                <div class="well">
                    <div class="ex-well-inner">
                        <dec:body/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
