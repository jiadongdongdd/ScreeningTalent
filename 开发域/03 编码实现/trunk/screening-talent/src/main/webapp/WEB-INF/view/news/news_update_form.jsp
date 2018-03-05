<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../commons/taglib-header.jsp"%>
<html>
<head>
<title>新增新闻</title>
<link rel="stylesheet"
	href="${contextPath}/static/datepicker/datepicker3.css" />
</head>
<body>

	<ol class="breadcrumb">
		<li><a href="${contextPath}/admin/index">首页</a></li>
		<li><a href="${contextPath}/admin/news/list">内容管理</a></li>
		<li><a href="${contextPath}/admin/news/list">新闻管理</a></li>
		<li class="active">编辑</li>
	</ol>

	<br />

	<form:form commandName="formDto" cssClass="form-horizontal"
		enctype="multipart/form-data">
		<div class="form-group">
			<label for="title" class="col-sm-2 control-label">发布标题<em
				class="text-danger">*</em> </label>

			<div class="col-sm-10">
				<form:input path="title" cssClass="form-control" placeholder="发布标题"
					required="required" maxlength="255" />
				<form:errors path="title" cssClass="label label-warning" />
				<p class="help-block">必填</p>
			</div>
		</div>

		<div class="form-group">
			<label for="category" class="col-sm-2 control-label">发布类别 <em
				class="text-danger">*</em> </label>

			<div class="col-sm-10">
				<form:select path="platform" cssClass="form-control">
					<form:option value="" label="请选择发布类别" />
					<form:options items="${plat}" itemLabel="label" />
				</form:select>
				<form:errors path="platform" cssClass="label label-warning" />
				<p class="help-block">必填</p>
			</div>
		</div>

		<div class="form-group">
			<label for="category" class="col-sm-2 control-label">新闻类别 <em
				class="text-danger">*</em> </label>

			<div class="col-sm-10">
				<form:select path="category" cssClass="form-control">
					<form:option value="" label="请选择新闻类别" />
					<form:options items="${formDto.categories}" itemLabel="label" />
				</form:select>
				<form:errors path="category" cssClass="label label-warning" />
				<p class="help-block">必填</p>
			</div>
		</div>

		<div class="form-group">
			<label for="title" class="col-sm-2 control-label">是否发布<em
				class="text-danger">*</em> </label>

			<div class="col-sm-10">
				是:<input type="radio" name="ispush"
					<c:if test="${formDto.ispush eq 'true' }">checked</c:if>
					value="true" /> 否:<input type="radio" name="ispush"
					<c:if test="${formDto.ispush eq 'false' }">checked</c:if>
					value="false" />
				<p class="help-block">必选</p>
			</div>
		</div>

		<div class="form-group">
			<label for="content" class="col-sm-2 control-label">新闻内容<em
				class="text-danger">*</em> </label>

			<div class="col-sm-10">
				<form:textarea path="content" cssClass="form-control"
					placeholder="新闻内容" required="required" rows="10" cols="25" />
				<form:errors path="content" cssClass="label label-warning" />
				<p class="help-block">必填</p>
			</div>
		</div>

		<div id="fileBlock"
			class="${formDto.platform.platNews ? '' : 'hidden' }">
			<div class="form-group">
				<label for="category" class="col-sm-2 control-label">新闻图片</label>
				<div class="col-sm-10">
					<input type="file" name="pictureFile" accept=".jpg" />
					<c:if test="${not empty formDto.pictureGuid}">
						<a href="${contextPath}/public/download/${formDto.pictureGuid}">下载新闻图片</a>
					</c:if>
					<form:errors path="pictureGuid" cssClass="label label-warning" />
					<p class="help-block">可选，上传图片必须是大小2M内的jpg图片</p>
				</div>
			</div>
		</div>


		<div class="form-group">
			<label for="dateTime" class="control-label col-sm-2">新闻编辑时间</label>

			<div class="col-sm-10">
				<form:input path="dateTime" cssClass="form-control datepicker"
					placeholder="新闻编辑时间" />
				<form:errors path="dateTime" cssClass="label label-warning" />
				<p class="help-block">新闻编辑时间</p>
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
	<script src="${contextPath}/static/datepicker/bootstrap-datepicker.js"></script>
	<script
		src="${contextPath}/static/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
	<script type="text/javascript">
		$(function() {
			new News();
		});
	</script>
</body>
</html>
