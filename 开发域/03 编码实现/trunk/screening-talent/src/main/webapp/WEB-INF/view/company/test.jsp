<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<title>欢迎页</title>
<link href="${contextPath}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="${contextPath}/static/bootstrap/css/jumbotron-narrow1.css" rel="stylesheet">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<style>
.jumbotron {
	text-align: center;
	background-color: transparent;
}

.jumbotron .btn {
	padding: 14px 24px;
	font-size: 21px;
}
</style>
</head>
<body style=" background: url('../../static/img/login/login-bg.png');">
	<nav class="navbar navbar-inverse navbar-fixed-top"
		style="background-color: #2a2f3d">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#indexnavbar"
					aria-expanded="false" aria-controls="indexnavbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#" style="margin-top: 5px;"><img
					src="${contextPath}/static/img/logo.png">
				</a>
			</div>
			<div id="indexnavbar" class="navbar-collapse collapse">
				<ul id="index" class="nav navbar-nav navbar-right">
					<li></li>
					<li><a href="#" class="active">平台新闻</a>
					</li>
					<li><a href="#">联系我们</a>
					</li>
					<li style="position: relative;">
	                    <input type="checkbox" id="login_" class="loginbtn" style="width: 85px; height: 50px; border: 1px solid red;position: absolute;z-index: 99;top: 0px;left:85px;opacity: 0.001">
	                    <input type="checkbox" id="register_"  class="loginbtn" style="width: 85px; height: 50px; border: 1px solid red;position: absolute;z-index: 99;top: 0px;left:0px;opacity: 0.001">
	                    <!--注册图片-->
	                    <a href="#" class="register" style="float: left; display: block;" ><img src="${contextPath}/static/img/index/login.png" class="login_img" ></a>
	                    <!--登录图片-->
	                    <a href="#" class="login" style="float: left; display: none;"  ><img src="${contextPath}/static/img/index/logout.png"  class="login_out"></a>
                	</li>
				</ul>
			</div>
		</div>
	</nav>
	<div id="ban" class="container">
		<div class="jumbotron">
			<div class="lead">
				<span style="display: block;height: 100px;width: 100px; float: left"><img
					src="${contextPath}/static/img/success.png">
				</span>
				<p>注册成功,欢迎您来到筛子网！</p>
				<p>企业正在审核,审核通过后会邮件发给您!</p>
			</div>
			<p class="lead">
				<a class="btn btn-lg btn-success" href="${contextPath}/login" role="button" style="">登录</a><a
					class="btn btn-lg btn-success" href="${contextPath}/" role="button">返回首页</a>
			</p>
		</div>
	</div>
	<!-- /container -->
	<script src="${contextPath}/static/js/jquery-1.12.1.min.js"
		type="text/javascript"></script>
	<script src="${contextPath}/static/bootstrap/js/bootstrap.min.js"></script>
	<%--<script src="${contextPath}/static/js/ie-emulation-modes-warning.js"></script>
	--%><script>
		 var $loginbtn=$(".loginbtn");
    $loginbtn.click(function(){
        var $this=$(this); //当前被点击的元素
        var $loginid=$this.attr("id"); //获取当前元素的attr
        var login="login_"; //
        var register="register_";
        if($loginid==login){
            if($this.is(":checked")){
                $(".register").hide();
                $(".login").show();
                $this.css({"left":"0px"});
                $("#register_").css({"left":"85px"});
            }else{
                $(".register").show();
                $(".login").hide();
                $this.css({"left":"85px"});
                $("#register_").css({"left":"0px"});
            }
        }
        if($loginid==register){
            var $position=$(this).position().left;
            if($position==0){
                window.location="${contextPath}/public/company/create";
            }else if($position==85){
                window.location="${contextPath}/login";
            }
        }
    });
	</script>
</body>
</html>