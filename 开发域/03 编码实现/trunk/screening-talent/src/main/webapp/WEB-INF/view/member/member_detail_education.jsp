<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../commons/taglib-header.jsp"%>
<html>
<head>
<title>职工信息</title>
</head>
<body>

	<ol class="breadcrumb">
		<li><a href="${contextPath}/enterprise/index">首页</a>
		</li>
		<li><a href="${contextPath}/enterprise/company/detail">企业管理</a>
		</li>
		<li><a href="${contextPath}/enterprise/member/list">职工信息</a>
		</li>
		<li class="active">职工信息详情</li>
	</ol>

	<ul class="nav nav-tabs">
		<li role="presentation"><a href="">基本信息</a>
		</li>
		<li role="presentation" class="active"><a href="">教育背景</a>
		</li>
		<li role="presentation"><a href="">招聘环节</a>
		</li>
		<li role="presentation"><a href="">离职环节</a>
		</li>
		<li role="presentation"><a href="">工作环节</a>
		</li>
	</ul>
	<br />

	<form:form commandName="formDto" cssClass="form-horizontal">
		<div class="form-group">
			<label class="col-sm-offset-1 control-label col-lg-2">性别</label>
			<c:if test="${formDto.gender == 'MALE'}">
				<div class="col-lg-2">
					<label class="control-label col-lg-4">男</label>
				</div>
			</c:if>

			<c:if test="${formDto.gender == 'FEMALE'}">
				<div class="col-lg-2">
					<label class="control-label col-lg-4">女</label>
				</div>
			</c:if>
			<label class=" control-label col-lg-2">年龄</label>
			<div class="col-lg-2">
				<label class="control-label col-lg-4">${formDto.age}</label>
			</div>
		</div>


		<div class="form-group">
			<label class="col-sm-offset-1 control-label col-lg-2">毕业院校 </label>
			<div class="col-lg-2">
				<label class="control-label col-lg-7">${formDto.school}</label>
			</div>
			<label class="control-label col-lg-2">学历</label>
			<div class="col-lg-2">
				<label class="control-label col-lg-5">${formDto.education}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-offset-1 control-label col-lg-2">民族</label>
			<div class="col-lg-2">
				<label class="control-label col-lg-5">${formDto.nation}</label>
			</div>
			<label class="control-label col-lg-2">政治面貌</label>
			<div class="col-lg-2">
				<label class="control-label col-lg-5">${formDto.politicalStatus}</label>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-offset-1 control-label col-lg-2">籍贯</label>
			<div class="col-lg-2">
				<label class="control-label col-lg-4">${formDto.origin}</label>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-offset-1 control-label col-lg-3"><a
				class="btn btn-default" href="javascript:history.back(-1)">返回</a> </label>
		</div>
	</form:form>
</body>
</html>
