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
<style type="text/css">
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
			<li><a id="indexPage" href="${contextPath}/admin/index">首页</a></li>
			<li><a id="compPass"
				href="${contextPath}/admin/company/deleteList">删除列表</a>
			</li>
			<li class="active"><a href="#">详情</a></li>
		</ol>
	</c:if>

	<c:if
		test="${fun:contains(SPRING_SECURITY_CONTEXT.authentication.authorities,'[ROLE_ENTERPRISEADMIN') }">
		<ol class="breadcrumb">
			<li><a href="${contextPath}/enterpriseAdmin/index">首页</a></li>
			<li><a href="${contextPath}/enterpriseAdmin/company/deleteList">删除列表</a>
			</li>
			<li class="active"><a href="#">详情</a></li>
		</ol>
	</c:if>

	<h3 class="page-header">${formDto.companyName}</h3>

	<form:form commandName="formDto" class="form-horizontal">
		<%--<div class="form-group">
			<label class="control-label col-lg-2">上传公司营业执照（副本）<em
				class="text-danger">*</em> </label>
			<div class="col-lg-4">
				<img src="${contextPath}/public/image/${formDto.businessPath}?w=150"
					alt="Img" />
			</div>
			<label class="control-label col-lg-2">上传公司机构证书<em
				class="text-danger">*</em> </label>
			<div class="col-lg-4">
				<img
					src="${contextPath}/public/image/${formDto.authorityPath}?w=150"
					alt="Img" />
			</div>
		</div>

		--%>
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
								name="${contextPath}/public/image/${formDto.businessPath}"
								href="${contextPath}/public/download/${formDto.businessPath}">下载执照</a>
						</c:if>
						<c:if test="${empty formDto.businessPath }">
							<strong>无</strong>
						</c:if>
					</div>
					<div class="col-lg-6 col-md-6 col-xs-12">
						<strong>公司机构证书:&nbsp;</strong>
						<c:if test="${not empty formDto.authorityPath }">
							<a class="imghover"
								name="${contextPath}/public/image/${formDto.authorityPath}"
								href="${contextPath}/public/download/${formDto.authorityPath}">下载证书</a>
						</c:if>
						<c:if test="${empty formDto.authorityPath }">
							<strong>无</strong>
						</c:if>
					</div>
				</div>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-offset-1 control-label col-lg-3"><a
				class="btn btn-default" href="javascript:history.back(-1)">返回</a> </label>
		</div>
	</form:form>
	<script type="text/javascript">
		$(function() {
			var href = location.pathname;
			if (href.indexOf("enterpriseAdmin") > 0) {
				$('#createCompany').attr('href',
						'${contextPath}/enterpriseAdmin/company/create');
				$('#indexPage').attr('href',
						'${contextPath}/enterpriseAdmin/index');
				$('#compPass')
						.attr('href',
								'${contextPath}/enterpriseAdmin/company/passVerify/true');
			}
			new CompanyVerify();
		});
	</script>
</body>
</html>
