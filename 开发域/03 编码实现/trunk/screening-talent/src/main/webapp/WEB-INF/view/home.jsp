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
<!-- ie8 hack ie8补充样式 -->
<!--[if lt IE 10]> 
<link href="${contextPath}/static/css/ie8.css" rel="stylesheet" />
<![endif]-->
<!--低版本ie补丁js -->
<script type="text/javascript" src="${contextPath}/static/js/html5shiv-3.7.0.js"></script>
<script type="text/javascript" src="${contextPath}/static/js/respond-1.3.0.min.js"></script>
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
			<div id="navbar" class="navbar-collapse collapse">
				<ul id="index" class="nav navbar-nav navbar-right">
					<li></li>
					<li><a href="#" class="active">平台新闻</a></li>
					<li><a href="${contextPath}/contact_us">联系我们</a></li>
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
							class="login_out"> </a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="jumbotron" id="banner">
		<div class="container">
			<h1>
				<img src="${contextPath}/static/img/index/banner-wenzi.png">
			</h1>
			<div class="col-lg-12 col-sm-12">

				<form action="home/member/search" class="form-inline"
					id="filterForm" autocomplete="off" name="homeSearchForm">
					<div
						class="form-group col-lg-offset-3 col-sm-offset-3 col-lg-6 col-sm-6">
						<input id="indexseach" type="search"
							class="form-control col-lg-12" placeholder="请输入您关注的人员姓名"
							name="chName1" value="" style="position: relative;" /> <i
							class="glyphicon glyphicon-search" style="position: absolute;cursor:pointer;"
							id="searchBtn"></i>
					</div>
				</form>

			</div>
		</div>
	</div>
	<div class="con">
		<div class="container">

			<div class="row">
				<div class="col-lg-12">
					<h2 class="header_title">
						<span>平台公告</span>
					</h2>
				</div>
				<div class="col-lg-12">
					<div class="col-lg-offset-1 col-lg-4 span4_">
						<img src="${contextPath}/static/img/index/icon-01.png">
					</div>
					<c:if test="${empty notice.list}">
						<div class="col-lg-7 span7_" style="margin-top: 20px;">
							<p>筛子网2.0已正式上线</p>
							<p>大家好：</p>
							<p style="text-indent: 2em; line-height: 30px;">经过精心策划和准备，筛子网新版主页于2016年2月30日全新上线！企业员工征信输入／查询两大板块携手亮相，改版后的网站内容更加丰富、结构更加清晰，希望更加细致周到的满足企业会员全方位的招聘需求！</p>
						</div>
					</c:if>
					<c:forEach items="${notice.list }" var="no">
						<div class="col-lg-7 span7_" style="margin-top: 20px;">
							<p>${no.title }</p>
							<p>大家好：</p>
							<p style="text-indent: 2em; line-height: 30px;">${no.content}</p>
						</div>
					</c:forEach>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<h2 class="header_title">
						<span>平台新闻</span>
					</h2>
				</div>
				<div class="col-lg-12">
					<c:if test="${empty news.list }">
						<div class="col-lg-8 span7_l" style="margin-top: 30px;">
							<p>
								<a href="javaScript:void(0);">IDsManager---云身份管理平台</a><span
									class="right-">2016-1-24</span>
							</p>
						</div>
					</c:if>
					<c:forEach items="${news.list }" var="n">
						<div class="col-lg-8 span7_l" style="margin-top: 30px;">
							<p>
								<a href="home/news/detail/${n.uuid }">${n.title }</a><span
									class="right-">${n.dateTime }</span>
							</p>
						</div>
					</c:forEach>

					<div class="col-lg-4 span4_r">
						<img src="${contextPath}/static/img/index/icon-02.png">
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="panel-footer">
		<div class="container">
			<div class="col-lg-4 col-sm-7 col-xs-7" style="float: left">
				<ul class="nav navbar-nav navbar-right">
					<li style="width: 100%;"><img
						src="${contextPath}/static/img/logo.png"></li>
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
									src="${contextPath}/static/img/index/qq.png" class="img-hover"><img
									src="${contextPath}/static/img/index/qq_s.png" class="img-show"> </a>
							</li>
							<%-- <li class="follow wechat" id="wechant" style="float: left;"><a href="#"
								class="wechat"><img
									src="${contextPath}/static/img/index/wechat.png"><img
									src="${contextPath}/static/img/index/wechat_s.png"> </a>
							</li> --%>
							<li class="follow microblog" id="sina"><a href="#" class=""><img
									src="${contextPath}/static/img/index/microblog.png" class="img-hover"><img
									src="${contextPath}/static/img/index/microblog_s.png" class="img-show"> </a>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
<!--[if lt IE 11]>
    <script src="${contextPath}/static/js/jquery-1.12.1.min.js" type="text/javascript"></script>
    <script type="text/javascript">
    $("#indexseach").val("请输入您关注的人员姓名");
    var wap_val=$("#indexseach").val(); 
        $("#indexseach").focusin(function(){
            if($("#indexseach").val()==wap_val){
                $("#indexseach").val("");
            }
        }).focusout(function(){
            if($.trim($("#indexseach").val())==""){
                $("#indexseach").val(wap_val);
            }
        })
</script>
<![endif]-->



	<%-- <script src="${contextPath}/static/js/jquery-2.0.3.min.js"
		type="text/javascript"></script> --%>
		<script src="${contextPath}/static/js/jquery-1.12.1.min.js" type="text/javascript"></script>
	<%--<script src="${contextPath}/static/js/ie-emulation-modes-warning.js"></script>
	--%><script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js" type="text/javascript"></script>
<!-- 	<script type="text/javascript" src="http://s.shareto.com.cn/js/shareto_float.js#color=0&align=right&move=1" charset="utf-8"></script> -->
	<script src="${contextPath}/static/js/share.js" type="text/javascript"></script>
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