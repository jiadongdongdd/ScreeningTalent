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
<title>审核详情</title>
</head>
<body>
	<c:if
		test="${fun:contains(SPRING_SECURITY_CONTEXT.authentication.authorities,'[ROLE_ADMIN') }">
		<ol class="breadcrumb">
			<li><a id="indexPage" href="${contextPath}/admin/index">首页</a></li>
			<li><a id="compNo"
				href="${contextPath }/admin/company/NoVerify/false">待审核企业</a></li>
			<li class="active"><a href="#">审核详情</a></li>
		</ol>
	</c:if>
	<c:if
		test="${fun:contains(SPRING_SECURITY_CONTEXT.authentication.authorities,'[ROLE_ENTERPRISEADMIN') }">
		<ol class="breadcrumb">
			<li><a href="${contextPath}/enterpriseAdmin/index">首页</a></li>
			<li><a
				href="${contextPath}/enterpriseAdmin/company/NoVerify/false">待审核企业</a>
			</li>
			<li class="active"><a href="#">审核详情</a></li>
		</ol>
	</c:if>

	<h3 class="page-header">${formDto.company.companyName}</h3>

	<form:form commandName="formDto" class="form-horizontal" method="post">
		<input type="hidden" name="company.companyName"
			value="${formDto.company.companyName }" />
		<input type="hidden" name="company.username"
			value="${formDto.company.username }" />
		<input type="hidden" name="company.verifyState"
			value="${formDto.company.verifyState }" />
		<input type="hidden" name="company.companyEmail"
			value="${formDto.company.companyEmail }" />
		<div class="form-group">
			<label class="control-label col-lg-2">用户名<em
				class="text-danger">*</em> </label>
			<div class="col-lg-4">
				<label>${formDto.company.username}</label>
			</div>
			<label class="control-label col-lg-2">审核状态<em
				class="text-danger">*</em> </label>
			<div class="col-lg-4">
				<c:if test="${formDto.company.verifyState eq 'false'}">
					<label>未审核</label>
				</c:if>
				<c:if test="${formDto.company.verifyState eq 'true'}">
					<label>已审核</label>
				</c:if>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-lg-2">企业名称<em
				class="text-danger">*</em> </label>
			<div class="col-lg-4">
				<label>${formDto.company.companyName}</label>
			</div>
			<label class="control-label col-lg-2">企业邮箱<em
				class="text-danger">*</em> </label>
			<div class="col-lg-4">
				<label>${formDto.company.companyEmail}</label>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label">原因<em
				class="text-danger">*</em> </label>
			<div class="col-sm-10" id="verifyCheck">
				<c:forEach items="${re}" var="g">
					<label class="radio-inline"> <input type="checkbox"
						name="verifyReason" value="${g}" class="${g}"
						 /> ${g.label}
					</label>
				</c:forEach>
				<br />
				<form:errors path="verifyReason" cssClass="label label-warning" />
			</div>
		</div>

		<div class="form-group">
			<button id="submit" class="btn btn-primary" type="submit"
				onclick=" return confirm('你确定审核不通过?'); return submitReason();">提交</button>
			<label class="control-label col-lg-3"><a
				href="javaScript:history.back(-1)">返回</a> </label>
		</div>
	</form:form>
	<script type="text/javascript">
		$(function() {
			Noverify();
			var href = location.pathname;
			if (href.indexOf("enterpriseAdmin") > 0) {
				$('#indexPage').attr('href',
						'${contextPath}/enterpriseAdmin/index');
				$('#compNo')
						.attr('href',
								'${contextPath}/enterpriseAdmin/company/NoVerify/false');
			}
		});
	</script>
</body>
</html>
