<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../commons/taglib-header.jsp"%>
<html>
<head>
<title>职工信息</title>
</head>
<body>

	<ol class="breadcrumb">
		<li><a href="${contextPath}/admin/index">首页</a>
		</li>
		<li><a href="${contextPath}/admin/company/NoVerify/false">企业管理</a>
		</li>
		<li href="${contextPath}/admin/member/list">职工信息</li>
		<li class="active">导入Excel</li>
	</ol>

	<br />

	<h5 align="center">
		<a href="../exportModel">下载导入模板</a>
	</h5>

	<form:form commandName="formDto" action="" cssClass="form-horizontal"
		enctype="multipart/form-data">
		<div class="form-group">
			<label for="excelFile" class="col-sm-2 control-label">导入文件<em
				class="text-danger">*</em> </label>

			<div class="col-sm-10">
				<input type="file" id="excelFile" name="excelFile" accept=".xls"
					required="required" />
				<form:errors path="excelFile" cssClass="label label-warning" />
				<p class="help-block">请导入.xls文件</p>
			</div>
		</div>
		<div class="form-group">
			<label for="" class="col-sm-2 control-label"> </label>
			<div class="col-sm-10">
				<p class="help-block">
					<strong>系统只能导入特定的格式的excel文件，请下载模版并填充数据，然后导入</strong>
				</p>
			</div>
		</div>

		<div class="form-group">
			<div class="col-sm-2"></div>
			<div class="col-sm-10">
				<button type="submit" class="btn btn-primary">
					<em class="glyphicon glyphicon-upload"></em> 导入文件
				</button>
				<a href="../list">返回</a>
			</div>
		</div>
		<c:if test="${resultDto.result eq false }">
			<div class="form-group">
				<label for="" class="col-sm-2 control-label">错误信息：</label>
				<div class="col-sm-10">
					<p class="help-block">
						<strong>下列数据出现非法格式， 请修复后重新提交</strong>
					</p>
					<c:forEach items="${resultDto.errorDatas }" var="error">
						<p class="help-block">
							<strong style="color: red">第${error.row
								}行，第${error.column }列</strong>
						</p>
					</c:forEach>

				</div>
			</div>
		</c:if>

	</form:form>
</body>
</html>
