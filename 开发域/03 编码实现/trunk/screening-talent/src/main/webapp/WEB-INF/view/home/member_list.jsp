<%--
 * Copyright (c) 2015 MONKEYK Information Technology Co. Ltd
 * www.monkeyk.com
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * MONKEYK Information Technology Co. Ltd ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with MONKEYK Information Technology Co. Ltd.
--%>
<%--
 * 
 * @author Shengzhao Li
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../commons/taglib-header.jsp"%>
<html>
<c:set var="contextPath" value="${pageContext.request.contextPath}"
	scope="application" />
<script>
	
<%--JS gloable varilible--%>
	var contextPath = "${contextPath}";
</script>
<head>
<title>职员列表</title>
<link href="${contextPath}/static/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" />
<link href="${contextPath}/static/css/dashboard.css" rel="stylesheet" />
</head>
<body>

	<nav class="navbar navbar-inverse navbar-fixed-top"
		style="background-color: #2a2f3d">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#" style="margin-top: 5px;"><img
					src="${contextPath}/static/img/logo.png"> </a>
			</div>
			<div id="indexnavbar" class="navbar-collapse collapse">
				<ul id="index" class="nav navbar-nav navbar-right">
					<li></li>
					<li><a href="#" class="active">平台新闻</a>
					</li>
					<li><a href="${contextPath}/contact_us">联系我们</a>
					</li>
					<li style="position: relative;"><input type="checkbox"
						id="login_" class="loginbtn"
						style="width: 85px; height: 50px; border: 1px solid red;position: absolute;z-index: 99;top: 0px;left:85px;opacity: 0.001">
						<input type="checkbox" id="register_" class="loginbtn"
						style="width: 85px; height: 50px; border: 1px solid red;position: absolute;z-index: 99;top: 0px;left:0px;opacity: 0.001">
						<!--注册图片--> <a href="#" class="register"
						style="float: left; display: block;"><img
							src="${contextPath}/static/img/index/login.png" class="login_img">
					</a> <!--登录图片--> <a href="#" class="login"
						style="float: left; display: none;"><img
							src="${contextPath}/static/img/index/logout.png"
							class="login_out"> </a>
					</li>
				</ul>
			</div>
		</div>
	</nav>

	<br />
	<br />
	<br />
	<form action="" class="form-inline" id="filterForm">
		<div class="form-group">
			<input type="text" class="form-control" placeholder="职员姓名"
				name="condition" value="${paginated.condition}" />
		</div>
		<button type="submit" class="btn btn-default">
			<em class="glyphicon glyphicon-search"></em>
		</button>
		&nbsp;<span class="text-info">共${paginated.totalSize}条记录</span>
	</form>
	<br />

	<ul class="list-group">

		<c:if test="${empty paginated.list}">
			<li class="list-group-item">
				<p class="text-muted">暂无数据</p>
			</li>
		</c:if>
		<c:forEach items="${paginated.list}" var="m">
			<li class="list-group-item">
				<h3 class="list-group-item-heading text-success">
					<a href="../../login"
						onclick="return confirm('友情提示：查看详细信息需要登录，您当前处于未登录状态，确定跳转到登录页面？')">${m.chName}</a>
				</h3>

				<div class="list-group-item-text text-muted">
					身份证: <span class="text-info">${m.idNumber}</span> <br /> 籍贯: <span
						class="text-info">${m.origin}</span> <br /> 学历: <span
						class="text-info">${m.education}</span> <br />
				</div>
			</li>
		</c:forEach>

	</ul>

	<%--paginated--%>
	<dis:table list="${paginated}" id="d" form="filterForm"
		class="table hidden table-striped table-hover">
		<dis:column property="uuid" />
	</dis:table>

</body>
<script src="${contextPath}/static/js/jquery-1.12.1.min.js"
	type="text/javascript"></script>
<%--<script src="${contextPath}/static/js/ie-emulation-modes-warning.js"></script>
--%><script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script src="${contextPath}/static/js/share.js"></script>
<script type="text/javascript">
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
</html>
