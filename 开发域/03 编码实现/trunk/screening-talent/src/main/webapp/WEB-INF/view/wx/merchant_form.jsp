<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../commons/taglib-header.jsp"%>
<html>
<head>
<title>商户新增</title>
</head>
<body>

	<ol class="breadcrumb">
		<li><a href="${contextPath}/admin/index">首页</a>
		</li>
		<li><a href="#">系统设置</a>
		</li>
		<li><a href="#">商户设置</a>
		</li>
		<li class="active">新增</li>
	</ol>

	<br />

	<form:form commandName="formDto" cssClass="form-horizontal">
		<div class="form-group">
			<label for="appId" class="col-sm-2 control-label">公众账号ID<em
				class="text-danger">*</em> </label>

			<div class="col-sm-10">
				<form:input path="appId" cssClass="form-control"
					placeholder="公众账号ID" required="required" maxlength="255" />
				<form:errors path="appId" cssClass="label label-warning" />
				<p class="help-block">必填</p>
			</div>
		</div>
		<div class="form-group">
			<label for="mchId" class="col-sm-2 control-label">商户号<em
				class="text-danger">*</em> </label>

			<div class="col-sm-10">
				<form:input path="mchId" cssClass="form-control"
					placeholder="商户号" required="required" maxlength="255" />
				<form:errors path="mchId" cssClass="label label-warning" />
				<p class="help-block">必填</p>
			</div>
		</div>
		<div class="form-group">
			<label for="mchKey" class="col-sm-2 control-label">商户KEY<em
				class="text-danger">*</em> </label>

			<div class="col-sm-10">
				<form:input path="mchKey" cssClass="form-control"
					placeholder="商户KEY" required="required" maxlength="255" />
				<form:errors path="mchKey" cssClass="label label-warning" />
				<p class="help-block">必填</p>
			</div>
		</div>
		
		<div class="form-group">
			<label for="mchKey" class="col-sm-2 control-label">机器IP<em
				class="text-danger">*</em> </label>

			<div class="col-sm-10">
				<form:input path="ip" cssClass="form-control"
					placeholder="ip" required="required" maxlength="255" />
				<form:errors path="ip" cssClass="label label-warning" />
				<p class="help-block">必填</p>
			</div>
		</div>
		
		<div class="form-group">
			<label for="port" class="col-sm-2 control-label">端口号<em
				class="text-danger">*</em> </label>

			<div class="col-sm-10">
				<form:input path="port" cssClass="form-control"
					placeholder="port" required="required" maxlength="255" />
				<form:errors path="port" cssClass="label label-warning" />
				<p class="help-block">必填</p>
			</div>
		</div>

		<div class="form-group">
			<div class="col-sm-2"></div>
			<div class="col-sm-10">
				<button type="submit" class="btn btn-primary">保存</button>
				<a href="../list">返回</a> &nbsp; <span><em class="text-danger">*</em>
					代表必填值</span>
				<c:if test="${param.alert eq 'saveOK'}">
					<span class="label label-success">保存完成</span>
				</c:if>
			</div>
		</div>
	</form:form>
</body>
</html>
