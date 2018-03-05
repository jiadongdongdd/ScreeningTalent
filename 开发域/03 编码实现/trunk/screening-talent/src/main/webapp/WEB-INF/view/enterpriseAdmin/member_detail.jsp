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
		<li><a href="${contextPath}/admin/index">企业管理</a>
		</li>
		<li><a href="${contextPath}/admin/company/passVerify/true">异常列表</a>
		</li>
		<li><a href="">详情</a>
		</li>
	</ol>

	<h3 class="page-header">
		${formDto.chName}  
	</h3>

	<form:form commandName="formDto" class="form-horizontal"
		enctype='multipart/form-data' accept=".jpg">
		
		<div class="form-group">
			<label class="control-label col-lg-2">职工姓名<em
				class="text-danger">*</em> </label>
			<div class="col-lg-4">
				<label>${formDto.chName}</label>
			</div>
			<label class="control-label col-lg-2">身份证号<em
				class="text-danger">*</em> </label>
			<div class="col-lg-4">
				<label>${formDto.idNumber}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-lg-2">职工邮箱<em
				class="text-danger">*</em> </label>
			<div class="col-lg-4">
				<label>${formDto.email}</label>
			</div>
			<label class="control-label col-lg-2">职工电话<em
				class="text-danger">*</em> </label>
			<div class="col-lg-4">
				<label>${formDto.mobile}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-lg-2">入职时间<em
				class="text-danger">*</em> </label>
			<div class="col-lg-4">
				<label><fmt:formatDate value="${formDto.entryDate}" type="both"/> </label>
			</div>
			<label class="control-label col-lg-2">离职时间<em
				class="text-danger">*</em> </label>
			<div class="col-lg-4">
				<label><fmt:formatDate value="${formDto.turnoverDate}" type="both"/></label>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-lg-2">企业名称<em
				class="text-danger">*</em> </label>
			<div class="col-lg-4">
				<label>${company.companyName}</label>
			</div>
			<label class="control-label col-lg-2">企业邮箱<em
				class="text-danger">*</em> </label>
			<div class="col-lg-4">
				<label>${company.companyEmail}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-lg-2">企业联系人<em
				class="text-danger">*</em> </label>
			<div class="col-lg-4">
				<label>${company.contacts}</label>
			</div>
			<label class="control-label col-lg-2">企业联系人电话<em
				class="text-danger">*</em> </label>
			<div class="col-lg-4">
				<label>${company.contactsPhone}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-lg-2">企业营业执照（副本）<em class="text-danger">*</em> </label> 
			<div class="col-lg-4">
					<img src="${contextPath}/public/image/${company.businessPath}?w=150"
					alt="Img" />
			</div>
				<label class="control-label col-lg-2">企业机构证书<em class="text-danger">*</em></label>
			<div class="col-lg-4">
				<img
					src="${contextPath}/public/image/${company.authorityPath}?w=150"
					alt="Img" />
			</div>
		</div>
		<div class="form-group">
				<label class="control-label col-lg-3"><a href="${contextPath }/admin/memberExcep/list">返回</a><em class="text-danger">*</em></label>
		</div>
	</form:form>
</body>
</html>
