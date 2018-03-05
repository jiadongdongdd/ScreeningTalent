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
<link href="${contextPath}/static/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" />
<link href="${contextPath}/static/css/xsifter.css" rel="stylesheet" />
<head>
<title>应用账号</title>
<style type="text/css">
#urlauthImg {
	border: 0;
}
</style>
</head>
<body>
	<ol class="breadcrumb">
		<li><a href="${contextPath}/admin/index">首页</a></li>
		<li><a href="${contextPath}/admin/list">系统设置</a></li>
		<li><a href="${contextPath}/admin/list">admin用户</a></li>
		<li class="active">新增管理员</li>
	</ol>
	<form:form commandName="formDto" style="margin-top:80px;"
		class="form-horizontal">
		<div class="form-group">
			<label class="control-label col-lg-3">用户名<em
				class="text-danger">*</em> </label>
			<div class="col-lg-6">

				<form:input path="username" cssClass="form-control"
					placeholder="用户名" maxlength="255" required="true" />
				<form:errors path="username" cssClass="label label-warning" />
				<p class="help-block">用户名长度2-16位</p>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-lg-3">密码<em
				class="text-danger">*</em> </label>
			<div class="col-lg-6">
				<form:password path="password" cssClass="form-control"
					placeholder="密码" maxlength="255" required="true" />
				<form:errors path="password" cssClass="label label-warning" />
				<p class="help-block">密码长度2-16位,字母/数字/特殊字符组合.</p>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-lg-3">确认密码<em
				class="text-danger">*</em> </label>
			<div class="col-lg-6">
				<form:password path="rePassword" cssClass="form-control"
					placeholder="确认密码" maxlength="255" required="true" />
				<form:errors path="rePassword" cssClass="label label-warning" />
				<p class="help-block">确认密码必须与密码一致</p>
			</div>
		</div>
		<div class="form-group"  style="display: none">
			<label class="control-label col-lg-3">权限<em
				class="text-danger">*</em>
			</label>

			<div class="col-lg-6">
				<form:checkboxes path="privileges" checked="checked" items="${privilegeList}"
					itemValue="value" itemLabel="label" delimiter="<br/>" />

				<form:errors path="privileges" cssClass="label label-warning" />
				<p class="help-block">审核企业权限</p>
			</div>
		</div>
		<div class="form-group">
			<div class="col-lg-offset-3 col-lg-9">
				<button class="btn btn-primary" type="submit">保存</button>
				&nbsp;<a href="list">返回</a>
				<c:if test="${alert eq 'saveOK'}">
					<span class="label label-info">保存成功!</span>
				</c:if>
			</div>
		</div>
	</form:form>
	<script type="text/javascript">
		$(function() {
			$('#password').val("${password}");
			$('#rePassword').val("${repassword}");
		});
	</script>
</body>
</html>
