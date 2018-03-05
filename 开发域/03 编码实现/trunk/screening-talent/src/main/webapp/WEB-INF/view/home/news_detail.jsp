<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../commons/taglib-header.jsp"%>
<html>
<c:set var="contextPath" value="${pageContext.request.contextPath}"
	scope="application" />
<script>
	
<%--JS gloable varilible--%>
	var contextPath = "${contextPath}";
</script>
<style>
.title_div {
	text-align: center;
}

.content_div {
	line-height: 35px;
}

.content_div p {
	color: block;
}

.image_dic {
	text-align: center;
}

.image_dic img {
	width: 60%;
}

.back_a {
	text-align: center;
	font-size: 20px;
	min-height: 650px;
}
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<title>${dto.title}</title>
</head>
<link href="${contextPath}/static/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" />
<link href="${contextPath}/static/css/dashboard.css" rel="stylesheet" />
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
					<li><a href="${contextPath}/" class="active">平台新闻</a>
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

	<div class="con" id="backgroud_img_div">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<h2 class="header_title">
						<!-- 						<span>平台新闻</span> -->
					</h2>
				</div>
				<div class="col-lg-12">
					<c:if test="${empty news.list }">
						<div class="col-lg-14 span7_l" style="margin-top: 30px;">
							<div class="title_div">
								<h3>${dto.title }</h3>
								<p>${dto.dateTime }</p>
							</div>

							<div class="image_dic">
								<img id="newImg" style="display: none;" alt="img"
									src="${contextPath}/public/image/${dto.pictureGuid}">
							</div>

							<div class="content_div">
								<p class="right-">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp${dto.content
									}</p>
							</div>
						</div>
					</c:if>

				</div>
				<div class="back_a">
					<a href="${contextPath}/">返回</a>
				</div>
			</div>
		</div>
	</div>
	<div class="panel-footer"  >
		<div class="container">
			<div class="col-lg-4 col-sm-7 col-xs-7" style="float: left">
				<ul class="nav navbar-nav navbar-right">
					<li style="width: 100%;"><img
						src="${contextPath}/static/img/logo.png">
					</li>
					<li class="follow microblog">COPYRIGHT© 2016 筛子网 ALL RIGHTS
						RESERVED</li>
				</ul>
			</div>
			<div class="col-lg-5 col-sm-5 col-xs-5" style="float: right">
				<div class="row">
					<div class="col-lg-12">
						<ul class="nav navbar-nav navbar-right">
							<li style="width: 100%;"><p style="text-align: left;">分享到</p>
							</li>
							<li class="follow wechat" id="qzone" style="float: left;"><a
								href="#" class="wechat"><img
									src="${contextPath}/static/img/index/qq.png"><img
									src="${contextPath}/static/img/index/qq_s.png"> </a></li>
							<%-- <li class="follow wechat" style="float: left;"><a href="#"
								class="wechat"><img
									src="${contextPath}/static/img/index/wechat.png"><img
									src="${contextPath}/static/img/index/wechat_s.png"> </a>
							</li> --%>
							<li class="follow microblog" id="sina"><a href="#" class=""><img
									src="${contextPath}/static/img/index/microblog.png"><img
									src="${contextPath}/static/img/index/microblog_s.png"> </a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script src="${contextPath}/static/js/jquery-1.12.1.min.js"
		type="text/javascript"></script>
	<%--<script src="${contextPath}/static/js/ie-emulation-modes-warning.js"></script>
	--%><script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
	<script src="${contextPath}/static/js/share.js"></script>
	<script>
		if ('${dto.pictureGuid}' != "") {
			$("#newImg").show();
		}

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

		$(function() {
			new Home();
		});

		function Home() {
			this.search();
		}

		Home.prototype = {
			search : function() {
				$("#searchBtn").click(function() {
					document.homeSearchForm.submit();
				});
			}
		};
	</script>
</body>
</html>