<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../commons/taglib-header.jsp"%>
<html>
<head>
<title>新增职位</title>
</head>
<body>

	<ol class="breadcrumb">
		<li><a href="${contextPath}/admin/index">首页</a>
		</li>
		<li><a href="${contextPath}/admin/position/list">职位管理</a>
		</li>
		<li class="active">新增</li>
	</ol>

	<br />

	<form:form commandName="formDto" cssClass="form-horizontal"
		enctype="multipart/form-data">

		<div class="form-group">
			<label for="title" class="col-sm-2 control-label">行业<em
				class="text-danger">*</em> </label>

			<div class="col-sm-10">
				<form:select path="industry" cssClass="form-control"
					required="required">
					<form:option value="">请选择职位行业</form:option>
					<form:options items="${formDto.industries }" itemLabel="label"
						itemValue="label" />
				</form:select>
				<form:errors path="industry" cssClass="label label-warning" />
				<p class="help-block">必填</p>
			</div>
		</div>

		<div class="form-group">
			<label for="title" class="col-sm-2 control-label">职位级别<em
				class="text-danger">*</em> </label>

			<div class="col-sm-10">
				<form:input path="level" cssClass="form-control" placeholder="职位级别"
					required="required" maxlength="255" />
				<form:errors path="level" cssClass="label label-warning" />
				<p class="help-block">必填</p>
			</div>
		</div>

		<div class="form-group">
			<label for="title" class="col-sm-2 control-label">职位名称<em
				class="text-danger">*</em> </label>

			<div class="col-sm-10">
				<form:input path="positionName" cssClass="form-control"
					placeholder="职位名称" required="required" maxlength="255" />
				<form:errors path="positionName" cssClass="label label-warning" />
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
