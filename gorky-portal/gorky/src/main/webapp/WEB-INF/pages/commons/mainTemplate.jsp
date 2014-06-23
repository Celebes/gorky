<%--
	Author: Kamil Szokaluk (kamil.szokaluk@execon.pl)
	Created: 18.09.13
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/pages/commons/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<title>PS AUMS :: <dec:title /></title>
<script type="text/javascript"
	src="<c:url value="/js/jquery-1.10.2.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/bootstrap.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/display-tag-ajax.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/jquery.countdown.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/main.js"/>"></script>

<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>">
<%--     <link rel="stylesheet" href="<c:url value="/css/main.css"/>"> --%>
<link rel="stylesheet" href="<c:url value="/css/jumbotron-narrow.css"/>">

<dec:head />
</head>
<body>
	<script type="text/javascript">
		var sessionTimeout = $
		{
			pageContext.request.session.maxInactiveInterval
		};
	</script>

	<%-- 	<div class="header">
	<div class="span8">
			    <a href="<c:url value="/"/>">
                <img src="<c:url value="/img/gorky_logo_portal.png"/>" alt="Logo">
            </a>
    </div>
	<div class="row fluid">
		<ul class="nav nav-pills pull-right">
			<li><a href="/login">Informacje o grze</a></li>
			<li><a href="#">Podgląd konta</a></li>
			<li><a href="#">Edycja konta</a></li>
			<li><a href="#">Statystyki</a></li>
			<li class="active"><a href="#">Zaloguj</a></li>
		</ul>

    </div>
<!-- 		<h3 class="text-muted">Gorky</h3> -->
	</div> --%>
	<%--     HEADERS
    <div class="row-fluid ex-header-row">
        <div class="span6">
            <a href="<c:url value="/"/>">
                <img src="<c:url value="/img/anim_knight_normal_01.png"/>" alt="Logo">
            </a>
        </div>
        <div class="span6">
            <div class="text-right">
                Zalogowany jako: <a href="#">jdynakpoczta@gmail.com</a>
            </div>
            <div class="text-right">
                Czas do wygaśnięcia sesji: <span id="session-timeout"></span>
            </div>
        </div>
    </div> --%>

	<%--MENU--%>
	<%-- 	<div class="row-fluid">
		        <div class="span3">
            <div class="ex-well-wrapper">
                <div class="well">
                    <div class="ex-well-inner">
                        <jsp:include page="/WEB-INF/pages/commons/menu/menu.jsp"/>
                    </div>
                </div>
            </div>
        </div>

		PAGE Content
		<div class="span2"></div>
		<div class="span8">
			<div class="ex-well-wrapper">
				<div class="well">
					<div class="ex-well-inner">
						<dec:body />
					</div>
				</div>
			</div>
		</div>
		<div class="span2"></div>
	</div> --%>
	<div class="container">
		<div class="header">
			<!-- 			<h3 class="text-muted">Project name</h3> -->
			<a href="<c:url value="/"/>"> <img
				src="<c:url value="/img/gorky_logo_portal.png"/>" alt="Logo">
			</a>
		</div>
		<div class="header span12">
			<ul class="nav nav-pills pull-ceter">
				<li><a href="/login">Informacje o grze</a></li>
				<li><a href="#">Podgląd konta</a></li>
				<li><a href="#">Edycja konta</a></li>
				<li><a href="#">Statystyki</a></li>
				<li><a href="#">Wyszukaj</a></li>
				<li class="active"><a href="#">Zaloguj</a></li>
			</ul>
			<!-- 			<h3 class="text-muted">Project name</h3> -->
		</div>

		<div class="jumbotron"></div>

		<div class="row marketing">
			<div class="col-lg-6"></div>

			<div class="col-lg-6">
				<dec:body />
			</div>
		</div>

		<div class="footer">
			<p>&copy; Jedynak Gurniak 2014</p>
		</div>

	</div>
	<!-- /container -->
</body>
</html>
