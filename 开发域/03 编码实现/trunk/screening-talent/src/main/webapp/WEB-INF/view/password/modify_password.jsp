<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../commons/taglib-header.jsp"%>
<html>
<head>
<title>密码修改</title>
</head>
<body>

	<ol class="breadcrumb">
		<li><a href="${contextPath}/enterprise/index">首页</a></li>
		<li><a href="#">系统设置</a></li>
		<li class="active">密码修改</li>
	</ol>

	<h3 class="page-header">密码修改</h3>
	<form:form commandName="formDto" cssClass="form-horizontal">
		<%--<div class="form-group">
			<label for="chName" class="col-sm-2 control-label">中文姓名<em
				class="text-danger">*</em> </label>

			<div class="col-sm-10">
				<form:input path="chName" cssClass="form-control" placeholder="中文姓名"
					required="required" maxlength="255" />
				<form:errors path="chName" cssClass="label label-warning" />
				<p class="help-block">2-5位中文字, 必填</p>
			</div>
		</div>

		--%>

		<div class="form-group">
			<label for="originalPassword" class="col-sm-2 control-label">原密码<em
				class="text-danger">*</em> </label>

			<div class="col-sm-10">
				<form:password path="originalPassword" cssClass="form-control"
					id="originalPassword" placeholder="原密码" required="required"
					maxlength="255" />
				<form:errors path="originalPassword" cssClass="label label-warning" />
				<p class="help-block">原密码是必需的</p>
			</div>
		</div>

		<div class="form-group">
			<label for="newPassword" class="col-sm-2 control-label">新密码<em
				class="text-danger">*</em> </label>

			<div class="col-sm-10">
				<form:password path="newPassword" cssClass="form-control"
					id="newPassword" placeholder="新密码，长度2-16位" required="required"
					maxlength="255" />
				<form:errors path="newPassword" cssClass="label label-warning" />
				<p class="help-block">新密码是必需的, 长度2-16位</p>
			</div>
		</div>
		<div class="form-group">
			<label for="reNewPassword" class="col-sm-2 control-label">确认密码<em
				class="text-danger">*</em> </label>

			<div class="col-sm-10">
				<form:password path="reNewPassword" cssClass="form-control"
					id="reNewPassword" placeholder="确认密码" required="required"
					maxlength="255" />
				<form:errors path="reNewPassword" cssClass="label label-warning" />
				<p class="help-block">确认密码不能为空，两次密码必须一致</p>
			</div>
		</div>


		<div class="form-group">
			<div class="col-sm-2"></div>
			<div class="col-sm-10">
				<button type="submit" class="btn btn-primary"
					onclick="return confirm('确认修改密码?')">修改</button>
			</div>
		</div>
	</form:form>

	<script type="text/javascript">
		$(function() {
			$('#originalPassword').val("${formDto.originalPassword}");
			$('#newPassword').val("${formDto.newPassword}");
			$('#reNewPassword').val("${formDto.reNewPassword}");
		});
	</script>
</body>
</html>
