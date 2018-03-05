<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="commons/taglib-header.jsp"%>
<html>
<c:set var="contextPath" value="${pageContext.request.contextPath}"
	scope="application" />
<script>
	
<%--JS gloable varilible--%>
	var contextPath = "${contextPath}";
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<title>首页</title>
</head>
<link href="${contextPath}/static/bootstrap/css/bootstrap.css"
	rel="stylesheet" />
<link href="${contextPath}/static/css/dashboard.css" rel="stylesheet" />
<!---联系我们主体样式 本页面只需要需要引用此样式即可-->
    <link href="${contextPath}/static/css/contact.css"  rel="stylesheet">
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
					<li><a href="${contextPath}/">平台新闻</a>
					</li>
					<li><a href="#" class="active">联系我们</a>
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
	
	<section>
		<div class="container" id="contact">
			<div class="container-body-height">
				<h2 class="contactTitle">联系我们</h2>
				<hr>
				<h1 class="contactWay">筛子网</h1>
				<address>
					<abbr title="联系电话:13051478436">联系电话:</abbr>13051478436<br>
					邮&nbsp;&nbsp;箱:<a href="mailto:502304632@qq.com">502304632@qq.com</a><br>
					公司地址：<br>
					感谢您对筛子网的关注与支持，如果您想更多的了解筛子网对企业人力资源背景调查和人力资源辅助工具的各项服务项目，
					请您联系我们，相信我们的沟通将给您的工作带来帮助。
				</address>
				<a href="#" class="thumbnail"> <img src="${contextPath}/static/img/map.jpg"
					alt="筛子网公司地址"> </a>
			</div>
		</div>
	</section>
	
	<div class="panel-footer">
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
									src="${contextPath}/static/img/index/wechat_s.png"> </a></li> --%>
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
					var text = $("#indexseach").val();
					if (text != "") {
						document.homeSearchForm.submit();
					}
				});
			}
		};
	</script>
</body>
</html>