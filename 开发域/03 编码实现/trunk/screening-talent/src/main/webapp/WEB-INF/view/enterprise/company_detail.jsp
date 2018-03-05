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
	<ol class="breadcrumb">
		<li><a href="${contextPath}/enterprise/company/detail">首页</a>
		</li>
		<li><a href="${contextPath }/enterprise/company/detail">企业管理</a>
		</li>
		<li class="active">企业详情</li>
	</ol>

	<h3 class="page-header">${formDto.companyName}</h3>

	<form:form commandName="formDto" class="form-horizontal"
		enctype='multipart/form-data' accept=".jpg">
		<div class="form-group">
			<label class="col-sm-offset-1 col-sm-offset-1 control-label col-lg-2">用户名</label>
			<div class="col-lg-2">
				<label class="control-label col-lg-12">${formDto.username}</label>
			</div>
			<label class="col-sm-offset-1 control-label col-lg-2">审核状态</label>
			<div class="col-lg-2">
				<c:if test="${formDto.verifyPass eq 'false'}">
					<label class="control-label col-lg-12">未通过</label>
				</c:if>
				<c:if test="${formDto.verifyPass eq 'true'}">
					<label class="control-label col-lg-12">已通过</label>
				</c:if>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-offset-1 control-label col-lg-2">企业名称 </label>
			<div class="col-lg-2">
				<label class="control-label col-lg-12">${formDto.companyName}</label>
			</div>
			<label class="col-sm-offset-1 control-label col-lg-2">企业邮箱</label>
			<div class="col-lg-2">
				<label class="control-label col-lg-12">${formDto.companyEmail}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-offset-1 control-label col-lg-2">联系人 </label>
			<div class="col-lg-2">
				<label class="control-label col-lg-12">${formDto.contacts}</label>
			</div>
			<label class="col-sm-offset-1 control-label col-lg-2">联系人电话</label>
			<div class="col-lg-2">
				<label class="control-label col-lg-12">${formDto.contactsPhone}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-lg-2">区域<em
				class="text-danger">*</em> </label>
			<div class="col-lg-4">
				<label>${formDto.prov}</label>
			</div>
			<label class="control-label col-lg-2">行业<em
				class="text-danger">*</em> </label>
			<div class="col-lg-4">
				<label>${formDto.industry}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-offset-1 control-label col-lg-2">上传公司营业执照（副本）</label>
			<div class="col-lg-2">
				<img src="${contextPath}/public/image/${formDto.businessPath}?w=150"
					alt="Img" />
			</div>
			<label class="col-sm-offset-1 control-label col-lg-2">上传公司机构证书</label>
			<div class="col-lg-2">
				<img
					src="${contextPath}/public/image/${formDto.authorityPath}?w=150"
					alt="Img" />
			</div>
		</div>
	</form:form>
</body>
</html>
