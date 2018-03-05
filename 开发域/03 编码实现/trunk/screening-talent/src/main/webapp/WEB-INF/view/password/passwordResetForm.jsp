<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../commons/taglib-header.jsp"%>
<html>
<head>
<title>密码重置</title>
<meta charset="utf-8" />
<c:set var="contextPath" value="${pageContext.request.contextPath}"
	scope="application" />

<meta name="viewport" content="width=device-width,user-scalable=no" />
<link rel="shortcut icon" href="${contextPath}/static/favicon.ico" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="${_csrf.headerName}" content="${_csrf.token}" />
<link href="${contextPath}/static/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" />
<!-- ie8 hack ie8补充样式 -->
<!--[if lt IE 10]> 
<link href="${contextPath}/static/css/ie8.css" rel="stylesheet" />
<![endif]-->
<!--低版本ie补丁js -->
<script type="text/javascript"
	src="${contextPath}/static/js/html5shiv-3.7.0.js"></script>
<script type="text/javascript"
	src="${contextPath}/static/js/respond-1.3.0.min.js"></script>
</head>
<body>


	<h3 class="page-header text-center">密码重置</h3>
	<form:form commandName="formDto" cssClass="form-horizontal">

		<form:hidden path="uuid" />

		<div class="form-group">
			<label for="username" class="col-sm-offset-1 col-sm-2 control-label">用户名<em
				class="text-danger">*</em> </label>

			<div class="col-sm-6">
				<form:input path="username" cssClass="form-control" id="username"
					placeholder="用户名" required="required" readonly="true"
					maxlength="255" />
				<form:errors path="username" cssClass="label label-warning" />
				<p class="help-block">用户名是必需的</p>
			</div>
		</div>

		<div class="form-group">
			<label for="password" class="col-sm-offset-1 col-sm-2 control-label">新密码<em
				class="text-danger">*</em> </label>

			<div class="col-sm-6">
				<form:password path="password" cssClass="form-control" id="password"
					placeholder="新密码，长度2-16位，应至少包含一个字母" required="required" maxlength="255" />
				<form:errors path="password" cssClass="label label-warning" />
				<p class="help-block">新密码是必需的, 长度2-16位,应至少包含一个字母</p>
			</div>
		</div>
		<div class="form-group">
			<label for="repassword"
				class="col-sm-offset-1 col-sm-2 control-label">确认密码<em
				class="text-danger">*</em> </label>

			<div class="col-sm-6">
				<form:password path="repassword" cssClass="form-control"
					id="repassword" placeholder="确认密码" required="required"
					maxlength="255" />
				<form:errors path="repassword" cssClass="label label-warning" />
				<p class="help-block">确认密码不能为空，两次密码必须一致</p>
			</div>
		</div>


		<div class="form-group">
			<div class="col-sm-offset-1 col-sm-2"></div>
			<div class="col-sm-6">
				<button type="submit" class="btn btn-primary"
					onclick="return confirm('确认重置密码?')">重置</button>
				<c:if test="${alert eq 'resetOk'}">
					<span class="label label-info">重置成功!</span>
					<a href="${contextPath }/login">登录</a>
				</c:if>
				<c:if test="${alert eq 'fail'}">
					<span class="label label-info">重置失败!</span>
				</c:if>
			</div>
		</div>

		<%--<c:if test="${alert eq 'resetOk'}">
			<span class="label label-info">重置成功!</span>
		</c:if>
		<c:if test="${alert eq 'fail'}">
			<span class="label label-info">重置失败!</span>
		</c:if>
	--%>
	</form:form>

	<script src="${contextPath}/static/js/jquery-1.12.1.min.js"></script>
	<script type="text/javascript"
		src="${contextPath}/static/js/placeholder.js"></script>

	<script type="text/javascript">
		$(function() {
			$('#password').val("${formDto.password}");
			$('#repassword').val("${formDto.repassword}");
		});
	</script>
</body>
</html>
