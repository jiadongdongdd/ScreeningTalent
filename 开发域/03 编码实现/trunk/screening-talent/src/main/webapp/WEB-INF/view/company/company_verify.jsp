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
<style>
#tooltip {
	position: absolute;
	text-align: center;
	border: 1px solid #444;
	padding: 10px 10px 0px;
	background-color: white;
	line-height: 20px;
}

#tooltip img {
	width: 600px;
	height: 500px;
}
</style>
</head>
<body>
	<c:if
		test="${fun:contains(SPRING_SECURITY_CONTEXT.authentication.authorities,'[ROLE_ADMIN') }">
		<ol class="breadcrumb">
			<li><a id="enterIndex" href="${contextPath}/admin/index">首页</a>
			</li>
			<li><a id="enterNo1"
				href="${contextPath }/admin/company/NoVerify/false">企业管理</a>
			<li><a id="enterNo2"
				href="${contextPath }/admin/company/NoVerify/false">待审核企业</a></li>
			<li class="active"><a href="#">审核</a>
			</li>
		</ol>
	</c:if>

	<c:if
		test="${fun:contains(SPRING_SECURITY_CONTEXT.authentication.authorities,'[ROLE_ENTERPRISEADMIN') }">
		<ol class="breadcrumb">
			<li><a id="enterIndex"
				href="${contextPath}/enterpriseAdmin/index">首页</a></li>
			<li><a id="enterNo1"
				href="${contextPath }/enterpriseAdmin/company/NoVerify/false">企业管理</a>
			<li><a id="enterNo2"
				href="${contextPath }/enterpriseAdmin/company/NoVerify/false">待审核企业</a>
			</li>
			<li class="active"><a href="#">审核</a>
			</li>
		</ol>
	</c:if>

	<h3 class="page-header">${formDto.companyName}</h3>

	<form:form commandName="formDto" class="form-horizontal">
		<div class="container">
			<div class="row">
				<div class="form-group">
					<div class="col-lg-6 col-md-6 col-xs-12">
						<strong>用户名:&nbsp;${formDto.username}</strong>
					</div>
					<div class="col-lg-6 col-md-6 col-xs-12">
						<strong>审核状态:&nbsp;${formDto.verifyPass ? '已通过' : '未通过' }</strong>
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-6 col-md-6 col-xs-12">
						<strong>企业名称:&nbsp;${formDto.companyName}</strong>
					</div>
					<div class="col-lg-6 col-md-6 col-xs-12">
						<strong>企业邮箱:&nbsp;${formDto.companyEmail}</strong>
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-6 col-md-6 col-xs-12">
						<strong>联系人:&nbsp;${formDto.contacts}</strong>
					</div>
					<div class="col-lg-6 col-md-6 col-xs-12">
						<strong>联系人电话:&nbsp;${formDto.contactsPhone }</strong>
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-6 col-md-6 col-xs-12">
						<strong>区域:&nbsp;${formDto.prov}</strong>
					</div>
					<div class="col-lg-6 col-md-6 col-xs-12">
						<strong>行业:&nbsp;${formDto.industry }</strong>
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-6 col-md-6 col-xs-12">
						<strong>公司营业执照（副本）:&nbsp;</strong>
						<c:if test="${not empty formDto.businessPath }">
							<a class="imghover"
								href="${contextPath}/public/download/${formDto.businessPath}"
								name="${contextPath}/public/image/${formDto.businessPath}">下载执照</a>
						</c:if>
						<c:if test="${empty formDto.businessPath }">
							<strong>无</strong>
						</c:if>
					</div>
					<div class="col-lg-6 col-md-6 col-xs-12">
						<strong>公司机构证书:&nbsp;</strong>
						<c:if test="${not empty formDto.authorityPath }">
							<a class="imghover"
								href="${contextPath}/public/download/${formDto.authorityPath}"
								name="${contextPath}/public/image/${formDto.authorityPath}">下载证书</a>
						</c:if>
						<c:if test="${empty formDto.authorityPath }">
							<strong>无</strong>
						</c:if>
					</div>
				</div>

			</div>
		</div>
		<%--<div class="form-group">
			<label class="control-label col-lg-2">上传公司营业执照（副本）<em
				class="text-danger">*</em> </label>
			<div class="col-lg-4">
				<img src="${contextPath}/public/image/${formDto.businessPath}?w=500"
					alt="Img" />
			</div>
			<label class="control-label col-lg-2">上传公司机构证书<em
				class="text-danger">*</em> </label>
			<div class="col-lg-4">
				<img
					src="${contextPath}/public/image/${formDto.authorityPath}?w=500"
					alt="Img" />
			</div>
		</div>
		--%>
		<br>
		<div class="form-group">
			<a id="passBtn" class="btn btn-primary">审核通过</a> <a
				id="noPassBtn" class="btn btn-primary"
				href="../reason/${formDto.guid}">审核不通过</a> <label
				class="control-label col-lg-3"><a id="backBtn"
				href="../false">返回</a> </label>
			<c:if test="${alert eq 'upOk'}">
				<span class="label label-info">审核成功,已邮件发至该企业!</span>
			</c:if>
			<c:if test="${successAlert eq 'Ok'}">
				<span class="label label-info">操作成功!</span>
			</c:if>
			<c:if test="${alert eq 'faildCredit'}">
				<span class="label label-info">奖励积分失败,请设置奖励积分!</span>
			</c:if>
			<c:if test="${alert eq 'faildMail'}">
				<span class="label label-info">邮件发送失败,请重试!</span>
			</c:if>
		</div>
	</form:form>
	<script type="text/javascript">
		$(function() {
			new CompanyVerify("${formDto.guid}");
			var href = location.pathname;
			if (href.indexOf("enterpriseAdmin") > 0) {
				$('#enterIndex').attr('href',
						'${contextPath}/enterpriseAdmin/index');
				$('#enterNo1')
						.attr('href',
								'${contextPath }/enterpriseAdmin/company/NoVerify/false');
				$('#enterNo2')
						.attr('href',
								'${contextPath }/enterpriseAdmin/company/NoVerify/false');
			}

			var result1 = "${alert}";
			var result2 = "${successAlert}";

			if (result1 == "upOk" || result2 == "Ok") {
				$('#backBtn').attr('href', '../../false');
				$('#passBtn').attr("onclick", false);
				$('#passBtn').attr('disabled', 'disabled');
				$('#passBtn').removeAttr('href');

				$('#noPassBtn').removeAttr('href');
				$('#noPassBtn').attr('disabled', 'disabled');
			}
		});
	</script>
</body>
</html>
