<%--
 * 
 * @author Shengzhao Li
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ include file="../commons/taglib-header.jsp"%>
<%@ include file="../main/header.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8" />
<c:set var="contextPath" value="${pageContext.request.contextPath}"
	scope="application" />
<script>
	
<%--JS gloable varilible --%>
	var contextPath = "${contextPath}";
</script>


<meta name="viewport" content="width=device-width,user-scalable=no" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="${_csrf.headerName}" content="${_csrf.token}" />
<link rel="shortcut icon" href="${contextPath}/static/favicon.ico" />

<title><decorator:title default="" /> | 筛子网</title>

<link href="${contextPath}/static/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" />
<link href="${contextPath}/static/css/xsifter.css" rel="stylesheet" />

<!--[if lt IE 9]>
    <script src="${contextPath}/static/js/html5shiv-3.7.0.js"></script>
    <script src="${contextPath}/static/js/respond-1.3.0.min.js"></script>
    <![endif]-->
<script type="text/javascript"
	src="${contextPath}/static/js/html5shiv-3.7.0.js"></script>
<script type="text/javascript"
	src="${contextPath}/static/js/respond-1.3.0.min.js"></script>
<decorator:head />

</head>
<body>
	<jsp:include page="../main/top.jsp"></jsp:include>
	<%--
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${contextPath}/"><img alt="Brand"
                                                                src="${contextPath}/static/images/logo.png"></a>
        </div>
         <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="${contextPath}/admin/enterprise/inviteRegister" class="text-warning">邀请注册</a></li>
                <li><a href="javascript:void(0);" class="username">${SPRING_SECURITY_CONTEXT.authentication.principal.username}</a>
					</li>
					<li><a href="javascript:void(0);">欢迎您</a>
					</li>
                <li>
                    <form action="${contextPath}/signout" class="navbar-form navbar-right" role="search" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <button type="submit" class="btn btn-warning">退出</button>
                    </form>
                </li>
            </ul>
        </div> 

    </div>
</nav>
    --%>

	<div class="container-fluid">

		<div class="row">
			<div id="click" class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-pills nav-stacked" id="mainMenu">
					<tags:admin_main_menu />
				</ul>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

				<%--JS--%>
				<script src="${contextPath}/static/js/jquery-1.12.1.min.js"></script>
				<script src="${contextPath}/static/bootstrap/js/bootstrap.min.js"></script>
				<script src="${contextPath}/static/js/xsifter-v1.js"></script>
				<script type="text/javascript"
					src="${contextPath}/static/js/js/jquery.cityselect.js"></script>
				<script src="${contextPath}/static/js/jquery-2.0.3.m.js"></script>
				<script type="text/javascript"
					src="${contextPath}/static/js/placeholder.js"></script>
				<decorator:body />

			</div>
		</div>

		<div>
			<hr />
			<p class="text-muted text-center">&copy; 2016 筛子网</p>
		</div>
	</div>

</body>
</html>
