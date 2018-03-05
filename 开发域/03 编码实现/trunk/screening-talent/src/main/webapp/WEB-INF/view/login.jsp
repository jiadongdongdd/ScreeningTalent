<%--
 * 
 * @author Shengzhao Li
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<c:set var="contextPath" value="${pageContext.request.contextPath}"
	scope="application" />
<script>
	
<%--JS gloable varilible--%>
	var contextPath = "${contextPath}";
</script>
<meta charset="utf-8" />

<meta name="viewport" content="width=device-width,user-scalable=no" />
<link rel="shortcut icon" href="${contextPath}/static/favicon.ico" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="${_csrf.headerName}" content="${_csrf.token}" />
<link href="${contextPath}/static/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" />
<link href="${contextPath}/static/css/login.css" rel="stylesheet" />
<script type="text/javascript" src="${contextPath}/static/js/html5shiv-3.7.0.js"></script>
<script type="text/javascript" src="${contextPath}/static/js/respond-1.3.0.min.js"></script>
<title>登录 | 筛子网</title>
</head>
<body>
	<div class="site-wrapper">

		<div class="site-wrapper-inner">

			<div class="cover-container">
				<div class="col-lg-offset-1 col-lg-5 col-sm-6 col-xs-10">
					<img src="${contextPath }/static/img/login/login-left.png">
				</div>
				<div
					class="col-lg-offset-1 col-lg-4 col-sm-offset-1 col-sm-5 col-xs-offset-1 col-xs-10">
					<div class="form-login">
						<form class="form-horizontal"
							action="${pageContext.request.contextPath}/signin" method="post">
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
							<div class="form-group">
								<p class="text-center">登录筛子网</p>
							</div>
							<div class="form-group">
								<div class="col-sm-12">
									<input type="text" class="form-control" required="" value=""
										name="username" placeholder="请输入您的用户名"> <i
										class="glyphicon glyphicon-user"></i>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-12">
									<input type="password" class="form-control" required=""
										value="" name="password" placeholder="请输入您的密码"> <i
										class="glyphicon glyphicon-lock"></i>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-5">
									<label> <input type="checkbox"><span>记住密码
									</span> </label>
								</div>
								<div class="col-lg-offset-3 col-sm-4">
									<a style="color:#9b9b9d"
										href="${contextPath }/public/password/send_eamil">忘记密码？</a>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-12">
									<button type="submit"
										class="btn btn-default col-sm-12 col-sm-12 col-xs-12">登录</button>
									<c:if test="${param.authorization_error eq 2}">
										<span class="label label-danger">权限不足 !!!</span>
									</c:if>
									<c:if test="${param.authentication_error eq 1}">
										<span class="label label-danger">请检查账号密码是否正确</span>
									</c:if>

									<c:if test="${param.authentication_error eq 3}">
										<span class="label label-danger">账号暂未通过审核或未进行激活</span>
									</c:if>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-12 control-label"
									style="text-align: center;"><a
									style="text-shadow:0px 0px 10px white ;color: #9b9b9d;text-decoration: none">还没有账号？</a><a
									style="" href="${contextPath }/public/company/create">立即注册</a>
									<a href="${contextPath }/"
									style="text-shadow:0px 0px 10px white;
                                     color: #9b9b9d;text-decoration: none">回到首页</a>
								</label>
							</div>
						</form>
					</div>
				</div>
			</div>

		</div>

	</div>
</body>
</html>