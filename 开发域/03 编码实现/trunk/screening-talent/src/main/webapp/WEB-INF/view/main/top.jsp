<%@page
	import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page
	import="org.springframework.security.core.userdetails.UserDetails"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page language="java" import="java.util.*"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"
	scope="application" />
<%
	String path = this.getServletContext().getContextPath();
	String privileges = "";
	if (session.getAttribute("privileges") != null)
		privileges = session.getAttribute("privileges").toString();
%>
<script>
	
<%--JS gloable varilible--%>
	var contextPath = "${contextPath}";
</script>
<style>
.invite_register {
	position: relative;
	min-width: 50px;
	height: 56px;
	display: inline-block;
	top: 8px;
}

.navbar .navbar-inner {
	border: 0;
	-webkit-border-radius: 0;
	-webkit-background-clip: padding-box;
	-moz-border-radius: 0;
	-moz-background-clip: padding;
	border-radius: 0;
	background-clip: padding-box;
	margin: 0;
	padding-left: 0;
	padding-right: 0;
	min-height: 55px;
	position: relative;
	background: #333;
}

#log_out {
	width: 100%;
	margin: 0px;
	height: 45px;
	text-align: left;
	padding-left: 24%;
	line-height: 45px;
	border-bottom: 1px dotted #000;
	color: #303030;
	font-weight: bold;
	font-size: 13px;
	font-family: "Microsoft YaHei";
	position: relative;
	top: 5px;
}

#navbar .nav.navbar-nav.navbar-right li:nth-child(1) {
	margin-right: 31px;
	margin-top: -8px;
}
</style>
<!-- Navbar -->
<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${contextPath}/"><img alt="Brand"
				src="${contextPath}/static/images/logo.png"> </a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<c:if
					test="${fn:contains(SPRING_SECURITY_CONTEXT.authentication.authorities,'[ROLE_ADMIN')}">
					<li><a id="adminPage" href="${contextPath}/admin/enterprise/inviteRegister"
						class="invite_register">邀请注册</a></li>
				</c:if>
				<c:if
					test="${fn:contains(SPRING_SECURITY_CONTEXT.authentication.authorities,'[ROLE_ENTERPRISEADMIN') }">
					<li><a id="enterAdminPage"
						href="${contextPath}/enterpriseAdmin/enterprise/inviteRegister"
						class="invite_register">邀请注册</a></li>
				</c:if>
				<c:if
					test="${fn:contains(SPRING_SECURITY_CONTEXT.authentication.authorities,'[ROLE_ENTERPRISE') && fn:indexOf(SPRING_SECURITY_CONTEXT.authentication.authorities,'[ROLE_ENTERPRISEADMIN') < 0}">
					<li><a href="${contextPath}/enterprise/inviteRegister"
						class="invite_register">邀请注册</a></li>
				</c:if>

				<li><span class="welcome">欢迎您 ${companyName}</span></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">&nbsp;${SPRING_SECURITY_CONTEXT.authentication.principal.username}<span
						class="caret"></span> </a>
					<ul class="dropdown-menu">
						<li></li>
						<c:if
							test="${fn:contains(SPRING_SECURITY_CONTEXT.authentication.authorities,'[ROLE_ADMIN')}">
							<li role="separator" class="dropdown-footer"><a
								href="${contextPath }/admin/index"><i
									class="glyphicon glyphicon-user warning logo_user" style=""></i>系统管理员</a>
							</li>
							<li role="separator" class="divider"></li>
							<li><a
								href="${contextPath }/enterpriseAdmin/company/NoVerify/false"><i
									class="glyphicon glyphicon-user warning logo_user" style=""></i>系统操作员</a>
							</li>
							<li role="separator" class="divider"></li>
							<li><a href="${contextPath }/enterprise/index"><i
									class="glyphicon glyphicon-user warning logo_user" style=""></i>企业用户</a>
							</li>
						</c:if>
						<c:if
							test="${fn:contains(SPRING_SECURITY_CONTEXT.authentication.authorities,'[ROLE_ENTERPRISEADMIN')}">
							<li role="separator" class="divider"></li>
							<li><a
								href="${contextPath }/enterpriseAdmin/company/NoVerify/false"><i
									class="glyphicon glyphicon-user warning logo_user" style=""></i>系统操作员</a>
							</li>
							<li role="separator" class="divider"></li>
							<li><a href="${contextPath }/enterprise/index"><i
									class="glyphicon glyphicon-user warning logo_user" style=""></i>企业用户</a>
							</li>
						</c:if>
						<c:if test="${isSwitch }">
							<li role="separator" class="divider"></li>
							<li><a href="${contextPath }/exit"><i
									class="glyphicon glyphicon-user warning logo_user" style=""></i>原用户</a>
							</li>
						</c:if>
						<li role="separator" class="dropdown-footer" id="logins">
							<form action="${contextPath}/signout" role="search" method="post">
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
								<button type="submit" class="fa fa-sign-out danger" id="log_out">退出</button>
							</form>
						</li>
					</ul>
				</li>
			</ul>
		</div>

	</div>
</nav>
<%-- 	<div class="navbar navbar-inverse navbar-fixed-top" style="background-color:#333">
		<div class="navbar-inner">
			<div class="navbar-container">
				<!-- Navbar Barnd -->
				<div class=container-fluid">
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
				</div>
			<!-- /Navbar Barnd -->
			<!-- Sidebar Collapse -->
			<div class="sidebar-collapse" id="sidebar-collapse"></div>
			<div class="navbar-header pull-right">
					<div class="navbar-account">
						<ul class="account-area">
							<c:if test="${SPRING_SECURITY_CONTEXT.authentication.authorities eq '[ROLE_ADMIN]'}">
								<li><a href="${contextPath}/admin/enterprise/inviteRegister" class="invite_register">邀请注册</a></li>
							</c:if>
							<c:if test="${SPRING_SECURITY_CONTEXT.authentication.authorities eq '[ROLE_ENTERPRISEADMIN]' }">
								<li><a href="${contextPath}/enterpriseAdmin/enterprise/inviteRegister" class="invite_register">邀请注册</a></li>
							</c:if>
							<c:if test="${SPRING_SECURITY_CONTEXT.authentication.authorities eq '[ROLE_ENTERPRISE]' }">
								<li><a href="${contextPath}/enterprise/inviteRegister" class="invite_register">邀请注册</a></li>
							</c:if>
							
							<li><a class="login-area dropdown-toggle" data-toggle="dropdown">
								<section>
									<h2>
										<span class="profile"><span>欢迎您：${SPRING_SECURITY_CONTEXT.authentication.principal.username} 
									</h2>
								</section> </a> <!--Login Area Dropdown-->
																																			
								<ul	class="pull-right dropdown-menu  dropdown-login-area"  id="select_ul" style="width: 143%;">
								
												<c:if test="${SPRING_SECURITY_CONTEXT.authentication.authorities eq '[ROLE_ADMIN]'}">
													<li class="dropdown-footer" id="logins" style=''><a href="<%=path %>/admin/index" style=''><i class="glyphicon glyphicon-user warning logo_user" style=""></i><span>超级管理员  </span></a></li>
													<li class="dropdown-footer" id="logins" style=''><a href="<%=path %>/enterpriseAdmin/company/NoVerify/false" style=''><i class="glyphicon glyphicon-user info shiny logo_user" style=""></i>企业管理员 </a></li>
													<li class="dropdown-footer" id="logins" style=''><a href="<%=path %>/switch?username=${SPRING_SECURITY_CONTEXT.authentication.principal.username}&targetUrl='asd'"> <i class="glyphicon glyphicon-user purple  logo_user" style=""></i>企业用户</a></li>
												</c:if>
											
												<c:if test="${SPRING_SECURITY_CONTEXT.authentication.authorities eq '[ROLE_ENTERPRISEADMIN]' }">
													<li class="dropdown-footer" id="logins" style=''><a href="<%=path %>/enterpriseAdmin/company/NoVerify/false" style=''><i class="glyphicon glyphicon-user info shiny logo_user" style=""></i>企业管理员 </a></li>
													<li class="dropdown-footer" id="logins" style=''><a href="<%=path %>/switch?username=${SPRING_SECURITY_CONTEXT.authentication.principal.username}&targetUrl=asd" style=''><i class="glyphicon glyphicon-user purple  logo_user" style=""></i>企业用户</a></li>
												</c:if>
												<li class="dropdown-footer" id="logins">
													<form action="${contextPath}/signout"
														  role="search" method="post">
														<input type="hidden" name="${_csrf.parameterName}"
															value="${_csrf.token}" />
														<button type="submit" class="fa fa-sign-out danger" id="log_out">退出</button>
													</form>
												</li>
								</ul>
								
								 <!--/Login Area Dropdown-->
							</li>
							<!-- /Account Area -->


						</ul>
						<!-- Settings -->
						<div class="setting">
						</div>
						<!-- Settings -->
						<!-- Settings -->
					</div>
				</div>
				<!-- /Account Area and Settings -->
			</div>
		</div>
	</div> --%>
<!-- /Navbar -->
<script src="${contextPath}/static/js/jquery-1.12.1.min.js"
	type="text/javascript"></script>
<script>
	$(".login-area").click(function() {
		$("#select_ul").toggle();
	})
	$(function() {

		var href = location.pathname;
		if (href.indexOf("/xsifter/enterpriseAdmin/") >= 0) {
			$('#adminPage').attr('href','${contextPath}/enterpriseAdmin/enterprise/inviteRegister');
		}
		
		if (href.indexOf("/xsifter/enterprise/") >= 0) {
			$('#adminPage').attr('href','${contextPath}/enterprise/inviteRegister');
			$('#enterAdminPage').attr('href','${contextPath}/enterprise/inviteRegister');
		}

	});
</script>
