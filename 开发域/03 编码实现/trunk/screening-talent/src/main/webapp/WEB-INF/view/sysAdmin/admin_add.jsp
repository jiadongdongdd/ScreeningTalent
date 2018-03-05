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
<link href="${contextPath}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
<link href="${contextPath}/static/css/xsifter.css" rel="stylesheet"/>
<head>
<title>应用账号</title>
<style type="text/css">
	#urlauthImg{
		border:0;
	}
</style>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" style="background-color: #2a2f3d">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#" style="margin-top: 5px;"><img src="${contextPath}/static/img/logo.png"></a>
        </div>
        <div id="indexnavbar" class="navbar-collapse collapse">
            <ul id="index" class="nav navbar-nav navbar-right">
                <li></li>
                <li><a href="#" class="active">平台新闻</a></li>
                <li><a href="${contextPath}/contact_us">联系我们</a></li>
                <li><a href="localhost:8080" class="login" style="position: relative;">
                        <img src="${contextPath}/static/img/index/login.png" class="login_img" style="display: none;">
                        <img src="${contextPath}/static/img/index/logout.png"  class="login_out">
                        <input type="checkbox" id="login_" style="width: 170px; height: 50px; border: 1px solid red;position: absolute;z-index: 99;top: 0px;opacity: 0.00001">
                    </a></li>
            </ul>
        </div>
    </div>
</nav>
	<form:form commandName="formDto" style="margin-top:80px;" class="form-horizontal"  enctype='multipart/form-data'
		accept=".jpg">
		<div class="form-group">
			<label class="control-label col-lg-3">用户名<em class="text-danger">*</em>
			</label>
			<div class="col-lg-5">
			
				<form:input path="username" value="dushaofei" cssClass="form-control"
					placeholder="Type username" maxlength="255" required="true" />
				<form:errors path="username" cssClass="label label-warning" />
				<p class="help-block">Username is required</p>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-lg-3">密码<em class="text-danger">*</em>
			</label>
			<div class="col-lg-8">
				<form:password path="password" value="123456" cssClass="form-control"
					placeholder="Type password" maxlength="255" required="true" />
				<form:errors path="password" cssClass="label label-warning" />
				<p class="help-block">Password is required</p>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-lg-3">确认密码<em class="text-danger">*</em>
			</label>
			<div class="col-lg-8">
				<form:password path="rePassword" value="123456"
					cssClass="form-control" placeholder="Type rePassword"
					maxlength="255" required="true" />
				<form:errors path="rePassword" cssClass="label label-warning" />
				<p class="help-block">Type the password again</p>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-lg-3">角色权限<em class="text-danger">*</em>
			</label>
			<div class="col-lg-8">
				<form:select path="privileges" items="${formDto.privileges}" >
				
				</form:select>
				<p class="help-block">companyName is required</p>
			</div>
		</div>
		<div class="form-group">
			<div class="col-lg-offset-3 col-lg-1">
				<button class="btn btn-primary" type="submit">保存</button>
		    &nbsp;<a href="list">返回</a>
				<c:if test="${alert eq 'saveOK'}">
					<span class="label label-info">保存成功!</span>
				</c:if>
			</div>
		</div>
	</form:form>
</body>
</html>
