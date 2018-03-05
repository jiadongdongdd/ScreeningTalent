<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../commons/taglib-header.jsp"%>
<html>
<head>
<title>商品新增</title>
</head>
<body>

	<ol class="breadcrumb">
		<li><a href="${contextPath}/admin/index">首页</a>
		</li>
		<li><a href="#">系统设置</a>
		</li>
		<li><a href="#">商品中心</a>
		</li>
		<li class="active">新增</li>
	</ol>

	<br />

	<form:form commandName="formDto" cssClass="form-horizontal">
		<div class="form-group">
			<label for="productName" class="col-sm-2 control-label">商品名<em
				class="text-danger">*</em> </label>

			<div class="col-sm-10">
				<form:input path="productName" cssClass="form-control"
					placeholder="商品名" required="required" maxlength="255" />
				<form:errors path="productName" cssClass="label label-warning" />
				<p class="help-block">必填</p>
			</div>
		</div>
		<div class="form-group">
			<label for="productPrice" class="col-sm-2 control-label">商品价格<em
				class="text-danger">*</em> </label>

			<div class="col-sm-10">
				<form:input path="productPrice" cssClass="form-control"
					placeholder="商品价格" required="required" maxlength="255" />
				<form:errors path="productPrice" cssClass="label label-warning" />
				<p class="help-block">必填</p>
			</div>
		</div>
		<div class="form-group">
			<label for="credit" class="col-sm-2 control-label">积分数量<em
				class="text-danger">*</em> </label>

			<div class="col-sm-10">
				<form:input path="credit" cssClass="form-control"
					placeholder="积分数量" required="required" maxlength="255" />
				<form:errors path="credit" cssClass="label label-warning" />
				<p class="help-block">必填</p>
			</div>
		</div>
		
		<div class="form-group">
			<label for="productBody" class="col-sm-2 control-label">简单描述<em
				class="text-danger">*</em> </label>

			<div class="col-sm-10">
				<form:input path="productBody" cssClass="form-control"
					placeholder="简单描述" required="required" maxlength="255" />
				<form:errors path="productBody" cssClass="label label-warning" />
				<p class="help-block">必填</p>
			</div>
		</div>
		
		<div class="form-group">
			<label for="productDetail" class="col-sm-2 control-label">详细描述<em
				class="text-danger">*</em> </label>

			<div class="col-sm-10">
				<form:input path="productDetail" cssClass="form-control"
					placeholder="详细描述" required="required" maxlength="255" />
				<form:errors path="productDetail" cssClass="label label-warning" />
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
