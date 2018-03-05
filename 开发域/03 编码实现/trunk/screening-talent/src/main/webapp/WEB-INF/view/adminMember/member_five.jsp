<%--
 * 
 * @author andy
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../commons/taglib-header.jsp"%>
<html>
<head>
<title>工作环节</title>
<link rel="stylesheet"
	href="${contextPath}/static/datepicker/datepicker3.css" />
<link rel="stylesheet" href="${contextPath}/static/select2/select2.css" />
<style>
#date_div {
	margin-top: 10px;
}
</style>
</head>
<body>

	<ol class="breadcrumb">
		<li><a href="${contextPath}/admin/index">首页</a>
		</li>
		<li><a href="${contextPath}/admin/company/NoVerify/false">企业管理</a>
		</li>
		<li><a href="${contextPath}/admin/member/list">职工信息</a>
		</li>
		<li class="active">职工信息录入</li>
	</ol>


	<ul class="nav nav-tabs">
		<li role="presentation"><a href="../form_1/${formDto.memberUuid}">基本信息</a>
		</li>
		<li role="presentation"><a href="../form_2/${formDto.memberUuid}">基本信息</a>
		</li>
		<li role="presentation"><a href="../form_3/${formDto.memberUuid}">招聘环节</a>
		</li>
		<li role="presentation"><a href="../form_4/${formDto.memberUuid}">离职环节</a>
		</li>
		<li role="presentation" class="active"><a
			href="../form_5/${formDto.memberUuid}">工作环节</a> <jsp:include
				page="../tsca/tsca.jsp"></jsp:include></li>
	</ul>
	<br />


	<form:form commandName="formDto" cssClass="form-horizontal">
		<form:hidden path="memberUuid" />


		<h4 class="page-header text-primary">嘉奖</h4>

		<div class="form-group">
			<label class="control-label col-sm-2">嘉奖类型</label>

			<div class="col-sm-10">
				<form:select path="awards" cssClass="form-control">
					<form:option value="" label="选择嘉奖类型" />
					<form:options items="${formDto.workedAwards}" itemLabel="label" />
				</form:select>
				<form:errors path="awards" cssClass="label label-warning" />
				<p class="help-block">嘉奖类型</p>
			</div>
		</div>


		<div class="form-group">
			<label class="control-label col-sm-2">嘉奖注明</label>

			<div class="col-sm-10">
				<form:input path="awardsRemark" cssClass="form-control"
					placeholder="嘉奖注明" />
				<form:errors path="awardsRemark" cssClass="label label-warning" />
				<p class="help-block">嘉奖注明</p>
			</div>
		</div>

		<h4 class="page-header text-primary">处分</h4>

		<div class="form-group">
			<label class="control-label col-sm-2">处分类型</label>

			<div class="col-sm-10">
				<form:select path="punished" cssClass="form-control">
					<form:option value="" label="选择处分类型" />
					<form:options items="${formDto.workPunishedList}" itemLabel="label" />
				</form:select>
				<form:errors path="punished" cssClass="label label-warning" />
				<p class="help-block">处分类型</p>
			</div>
		</div>

		<div class="form-group">
			<label class="control-label col-sm-2">处分注明</label>

			<div class="col-sm-10">
				<form:input path="punishedRemark" cssClass="form-control"
					placeholder="处分注明" />
				<form:errors path="punishedRemark" cssClass="label label-warning" />
				<p class="help-block">处分注明</p>
			</div>
		</div>

		<h4 class="page-header text-primary">升迁</h4>

		<div class="form-group">
			<label class="control-label col-sm-2">工作期间是否有职位变化</label>

			<div class="col-sm-10">
				<label class="radio-inline"> <form:radiobutton
						path="workChange" value="true" />是</label> <label class="radio-inline">
					<form:radiobutton path="workChange" value="false" /> 否 </label>

				<p class="help-block">工作期间是否有职位变化</p>
			</div>
		</div>
		<div id="workChange" class="${formDto.workChange?'':'hidden'}">
			<div class="form-group">
				<label class="control-label col-sm-2">工作期间职位变化</label>
				<div class="col-sm-10">
					<div class="col-sm-10" id="date_div">
						<div class="col-sm-4">
							<form:input path="changeDateOne"
								cssClass="form-control datepicker" placeholder="期间时间1" />
							<form:errors path="changeDateOne" cssClass="label label-warning" />
						</div>
						<div class="col-sm-4">
							<form:input path="changeJobOne" cssClass="form-control" />
						</div>
						<div class="col-sm-2">时间&职位名称</div>
					</div>
					<div class="col-sm-10" id="date_div">
						<div class="col-sm-4">
							<form:input path="changeDateTwo"
								cssClass="form-control datepicker" placeholder="期间时间2" />
						</div>
						<div class="col-sm-4">
							<form:input path="changeJobTwo" cssClass="form-control" />
							<form:errors path="changeDateTwo" cssClass="label label-warning" />
						</div>
						<div class="col-sm-2">时间&职位名称</div>
					</div>
					<div class="col-sm-10" id="date_div">
						<div class="col-sm-4">
							<form:input path="changeDateThree"
								cssClass="form-control datepicker" placeholder="期间时间3" />
						</div>
						<div class="col-sm-4">
							<form:input path="changeJobThree" cssClass="form-control" />
							<form:errors path="changeDateThree"
								cssClass="label label-warning" />
						</div>
						<div class="col-sm-2">时间&职位名称</div>
					</div>
				</div>
			</div>
		</div>
		<hr />


		<div class="form-group">
			<div class="col-sm-2"></div>
			<div class="col-sm-10">
				<button type="submit" name="finished" value="true"
					class="btn btn-primary">保存&返回</button>
				<button type="submit" name="finished" value="false"
					class="btn btn-success">保存&上一步</button>
				<a href="../list">返回</a>&nbsp; <span><em class="text-danger">*</em>
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
	<script src="${contextPath}/static/select2/select2.js"></script>
	<script>
		$(function() {
			new MemberFour();
			new TScAcnt('${formDto.memberUuid}');
		});
	</script>
</body>
</html>