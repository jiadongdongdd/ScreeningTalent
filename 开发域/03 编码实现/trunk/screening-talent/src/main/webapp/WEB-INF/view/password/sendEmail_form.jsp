<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../commons/taglib-header.jsp"%>
<html>
<head>
<title>密码重置-发送邮件</title>
<meta charset="utf-8" />
<c:set var="contextPath" value="${pageContext.request.contextPath}"
	scope="application" />

<meta name="viewport" content="width=device-width,user-scalable=no" />
<link rel="shortcut icon" href="${contextPath}/static/favicon.ico" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="${_csrf.headerName}" content="${_csrf.token}" />
<link href="${contextPath}/static/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" />
<link href="${contextPath}/static/css/dashboard.css" rel="stylesheet" />
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
<body id="ie-body">
	<div class="container">
		<div class="row">
			<div class="col-md-offset-2 col-md-8">
				<div id="ie-form" class="bor-form">
					<h3 class="page-header text-center">重置密码</h3>
					<form:form commandName="formDto" cssClass="form-horizontal">

						<div class="form-group">
							<label for="email" class="col-sm-offset-1 col-sm-2 control-label">注册时邮箱<!-- <em
							class="text-danger">*</em>  --> </label>

							<div class="col-sm-8">
								<form:input path="email" cssClass="form-control" id="email"
									placeholder="注册时邮箱" required="required" maxlength="255" />
								<form:errors path="email" cssClass="label label-warning" />
								<p class="help-block">注册时邮箱是必需的</p>
							</div>
						</div>

						<div class="form-group">
							<div class="col-sm-offset-1 col-sm-2"></div>
							<div class="col-sm-8">
								<button type="submit" class="btn btn-primary">发送</button>
								<a href="../../login">返回</a>
								<c:if test="${alert eq 'sendOk'}">
									<span class="label label-info">邮件发送成功!</span>
								</c:if>
								<c:if test="${alert eq 'fail'}">
									<span class="label label-info">邮件发送失败!</span>
								</c:if>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10"></div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
	<script src="${contextPath}/static/js/jquery-1.12.1.min.js"
		type="text/javascript"></script>
	<script type="text/javascript"
		src="${contextPath}/static/js/placeholder.js"></script>
	<%--<script src="${contextPath}/static/js/ie-emulation-modes-warning.js"></script>
	--%>
	<script>
		var $loginbtn = $(".loginbtn");
		$loginbtn
				.click(function() {
					var $this = $(this); //当前被点击的元素
					var $loginid = $this.attr("id"); //获取当前元素的attr
					var login = "login_"; //
					var register = "register_";
					if ($loginid == login) {
						if ($this.is(":checked")) {
							$(".register").hide();
							$(".login").show();
							$this.css({
								"left" : "0px"
							});
							$("#register_").css({
								"left" : "85px"
							});
							setTimeout(function() {
								window.location = "${contextPath}/login";
							}, 100)
						} else {
							$(".register").show();
							$(".login").hide();
							$this.css({
								"left" : "85px"
							});
							$("#register_").css({
								"left" : "0px"
							});
							setTimeout(
									function() {
										window.location = "${contextPath}/public/company/create";
									}, 100)
						}
					}
					if ($loginid == register) {
						var $position = $(this).position().left;
						if ($position == 0) {
							window.location = "${contextPath}/public/company/create";
						} else if ($position == 85) {
							window.location = "${contextPath}/login";
						}
					}
				});
	</script>
</body>
</html>
