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
<!DOCTYPE HTML>
<html>
<head>
<title>应用账号</title>
</head>
<body>
	<c:if
		test="${fun:contains(SPRING_SECURITY_CONTEXT.authentication.authorities,'[ROLE_ADMIN') }">
		<ol class="breadcrumb">
			<li><a id="indexPage" href="${contextPath}/admin/index">首页</a></li>
			<li><a id="compNo"
				href="${contextPath}/admin/company/NoVerify/false">企业管理</a></li>
			<li><a id="invitePage"
				href="${contextPath }/admin/enterprise/list">邀请记录</a></li>
			<li class="active">明细</li>
		</ol>
	</c:if>

	<c:if
		test="${fun:contains(SPRING_SECURITY_CONTEXT.authentication.authorities,'[ROLE_ENTERPRISEADMIN') }">
		<ol class="breadcrumb">
			<li><a href="${contextPath}/enterpriseAdmin/index">首页</a></li>
			<li><a
				href="${contextPath}/enterpriseAdmin/company/NoVerify/false">企业管理</a>
			</li>
			<li><a href="${contextPath }/enterpriseAdmin/enterprise/list">邀请记录</a>
			</li>
			<li class="active">明细</li>
		</ol>
	</c:if>

	<h3 class="page-header">${invite.companyName}</h3>

	<form:form commandName="invite" class="form-horizontal"
		enctype='multipart/form-data' accept=".jpg">
		<div class="form-group">
			<label class="control-label col-lg-2">企业收件人<em
				class="text-danger">*</em> </label>
			<div class="col-lg-4">
				<label>${invite.username}</label>
			</div>
			<label class="control-label col-lg-2">企业名称<em
				class="text-danger">*</em> </label>
			<div class="col-lg-4">
				<label>${invite.companyName}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-lg-2">企业邮箱<em
				class="text-danger">*</em> </label>
			<div class="col-lg-4">
				<label>${invite.companyEmail}</label>
			</div>
			<label class="control-label col-lg-2">邀请人<em
				class="text-danger">*</em> </label>
			<div class="col-lg-4">
				<label>${invite.inviteUsername}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-lg-2">邀请状态<em
				class="text-danger">*</em> </label>
			<div class="col-lg-4">
				<c:if test="${invite.inviteState eq 'true'}">
					<label>已接受</label>
				</c:if>
				<c:if test="${invite.inviteState eq 'false' }">
					<label>未接受</label>
				</c:if>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-lg-3"> <c:if
					test="${user.privileges eq '[ADMIN]' }">
					<a href="javaScript:history.back(-1)">返回</a>
				</c:if> <c:if test="${user.privileges eq '[ENTERPRISEADMIN]' }">
					<a href="javaScript:history.back(-1)">返回</a>
				</c:if> <c:if test="${company != null }">
					<a href="${contextPath }/enterprise/invite/List">返回</a>
				</c:if> </label>
		</div>
	</form:form>

	<script type="text/javascript">
		$(function() {
			var href = location.pathname;
			if (href.indexOf("enterpriseAdmin") > 0) {
				$('#indexPage').attr('href',
						'${contextPath}/enterpriseAdmin/index');
				$('#compNo')
						.attr('href',
								'${contextPath}/enterpriseAdmin/company/NoVerify/false');
				$('#invitePage').attr('href',
						'${contextPath }/enterpriseAdmin/enterprise/list');
			}
		});
	</script>
</body>
</html>
