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
<title>邀请注册</title>
</head>
<body>
	<c:if
		test="${fun:contains(SPRING_SECURITY_CONTEXT.authentication.authorities,'[ROLE_ADMIN') }">
		<ol class="breadcrumb">
			<li><a id="indexPage" href="${contextPath}/admin/index">首页</a>
			</li>
			<li class="active">邀请注册</li>
		</ol>
	</c:if>
	<c:if
		test="${fun:contains(SPRING_SECURITY_CONTEXT.authentication.authorities,'[ROLE_ENTERPRISEADMIN') }">
		<ol class="breadcrumb">
			<li><a id="enterPage"
				href="${contextPath}/enterpriseAdmin/index">首页</a>
			</li>
			<li class="active">邀请注册</li>
		</ol>
	</c:if>
	<c:if
		test="${fun:contains(SPRING_SECURITY_CONTEXT.authentication.authorities,'[ROLE_ENTERPRISE') && fun:indexOf(SPRING_SECURITY_CONTEXT.authentication.authorities,'[ROLE_ENTERPRISEADMIN') < 0 }">
		<ol class="breadcrumb">
			<li><a href="${contextPath}/enterprise/index">首页</a>
			</li>
			<li class="active">邀请注册</li>
		</ol>
	</c:if>

	<form:form commandName="formDto" class="form-horizontal">
		<tags:csrf_hidden />
		<div class="form-group">
			<label class="control-label col-lg-3">用户名<em
				class="text-danger">*</em> </label>
			<div class="col-lg-6">
				<form:input path="username" cssClass="form-control"
					placeholder="用户名" maxlength="255" required="true" />
				<form:errors path="username" cssClass="label label-warning" />
				<p class="help-block">必填项</p>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-lg-3">企业名称<em
				class="text-danger">*</em> </label>
			<div class="col-lg-6">
				<form:input path="companyName" cssClass="form-control"
					placeholder="企业名称" maxlength="255" required="true" />
				<form:errors path="companyName" cssClass="label label-warning" />
				<p class="help-block">必填项</p>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-lg-3">企业邮箱<em
				class="text-danger">*</em> </label>
			<div class="col-lg-6">
				<form:input path="companyEmail" cssClass="form-control"
					placeholder="企业邮箱" maxlength="255" required="true" />
				<form:errors path="companyEmail" cssClass="label label-warning" />
				<p class="help-block">必填项</p>
			</div>
		</div>
		<div class="form-group">
			<div class="col-lg-offset-3 col-lg-9">
				<button class="btn btn-primary" type="submit">邀请</button>
				&nbsp;<a href="javaScript:history.back(-1)">返回</a>
				<c:if test="${alert eq 'saveOK'}">
					<span class="label label-info">邀请成功，邮件已发送至该企业邮箱!</span>
				</c:if>
			</div>
		</div>
	</form:form>
	<script type="text/javascript">
		$(function() {
			var href = location.pathname;
			if (href.indexOf("/enterpriseAdmin/enterprise/inviteRegister") > 0) {
				$('#indexPage').attr('href',
						'${contextPath}/enterpriseAdmin/index');
			}

			if (href.indexOf("xsifter/enterprise/inviteRegister") > 0) {
				$('#indexPage').attr('href', '${contextPath}/enterprise/index');
				$('#enterPage').attr('href', '${contextPath}/enterprise/index');
			}

		});
	</script>
</body>
</html>
