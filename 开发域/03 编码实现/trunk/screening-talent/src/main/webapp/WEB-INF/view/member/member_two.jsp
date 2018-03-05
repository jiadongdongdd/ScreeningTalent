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
		<li class="active">职工信息录入</li>
	</ol>

	<ul class="nav nav-tabs">
		<li role="presentation"><a href="../form_1/${formDto.uuid}">基本信息</a>
		</li>
		<li role="presentation" class="active"><a
			href="../form_2/${formDto.uuid}">基本信息</a>
		</li>
		<li role="presentation"><a href="../form_3/${formDto.uuid}">招聘环节</a>
		</li>
		<li role="presentation"><a href="../form_4/${formDto.uuid}">离职环节</a>
		</li>
		<li role="presentation"><a href="../form_5/${formDto.uuid}">工作环节</a>
		</li>
	</ul>
	<br />

	<form:form commandName="formDto" cssClass="form-horizontal">
		<div class="form-group">
			<label class="col-sm-2 control-label">性别<em
				class="text-danger">*</em> </label>

			<div class="col-sm-10">
				<div class="radio-inline">
					<label> <form:radiobutton path="gender" value="MALE"
							required="required" /> 男 </label>
				</div>
				<div class="radio-inline">
					<label> <form:radiobutton path="gender" value="FEMALE"
							required="required" /> 女 </label>
				</div>
				<form:errors path="gender" cssClass="label label-warning" />
				<p class="help-block">可选</p>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">年龄</label>

			<div class="col-sm-10">
				<form:input path="age" cssClass="form-control" placeholder="年龄"
					maxlength="3" />
				<form:errors path="age" cssClass="label label-warning" />
				<p class="help-block">可选</p>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">毕业院校</label>

			<div class="col-sm-10">
				<form:input path="school" cssClass="form-control" placeholder="毕业院校"
					maxlength="255" />
				<form:errors path="school" cssClass="label label-warning" />
				<p class="help-block">可选</p>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label">专业</label>

			<div class="col-sm-10">
				<form:input path="major" placeholder="专业" cssClass="form-control"
					maxlength="255" />
				<form:errors path="major" cssClass="label label-warning" />

				<p class="help-block">可选</p>
			</div>
		</div>

		<div class="form-group">
			<label class="control-label col-sm-2">学历</label>

			<div class="col-sm-10">
				<form:select path="degree" cssClass="form-control">
					<form:option value="" label="选择学历" />
					<form:options items="${formDto.degrees}" itemLabel="label" />
				</form:select>
				<form:errors path="degree" cssClass="label label-warning" />
				<p class="help-block">学历</p>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label">民族</label>

			<div class="col-sm-10">
				<form:select path="nation" cssClass="form-control">
					<form:option value="" label="请选择民族" />
					<form:options items="${formDto.nations}" itemLabel="label" />
				</form:select>
				<form:errors path="nation" cssClass="label label-warning" />
				<p class="help-block">可选</p>
			</div>
		</div>
		<div id="nationRemark"
			class="${formDto.nation.label == '其他' ? '' : 'hidden' }">
			<div class="form-group">
				<label class="col-sm-2 control-label">其他民族备注<em
					class="text-danger">*</em> </label>

				<div class="col-sm-10">
					<form:input path="nationOther" placeholder="其他民族备注"
						cssClass="form-control" maxlength="255" />
					<form:errors path="nationOther" cssClass="label label-warning" />
					<p class="help-block">当民族选择'其他'选项时，其他民族备注必须填写，内容应为中文或英文字母</p>
				</div>
			</div>
		</div>

		<div class="form-group">
			<label class="control-label col-sm-2">政治面貌</label>

			<div class="col-sm-10">
				<form:select path="politicalStatus" cssClass="form-control">
					<form:option value="" label="选择政治面貌" />
					<form:options items="${formDto.politicalStatuses}"
						itemLabel="label" />
				</form:select>
				<form:errors path="politicalStatus" cssClass="label label-warning" />
				<p class="help-block">政治面貌</p>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label">籍贯</label>

			<div class="col-sm-10">
				<form:select path="origin" cssClass="form-control">
					<form:option value="" label="请选择籍贯" />
					<form:options items="${formDto.origins}" itemLabel="label" />
				</form:select>
				<form:errors path="origin" cssClass="label label-warning" />
				<p class="help-block">可选</p>
			</div>
		</div>

		<div id="originRemark"
			class="${formDto.origin.label == '外籍' ? '' : 'hidden' }">
			<div class="form-group">
				<label class="col-sm-2 control-label">外籍备注<em
					class="text-danger">*</em> </label>

				<div class="col-sm-10">
					<form:input path="foreignOrigin" placeholder="外籍备注"
						cssClass="form-control" maxlength="255" />
					<form:errors path="foreignOrigin" cssClass="label label-warning" />
					<p class="help-block">当籍贯选择'外籍'选项时，外籍备注必须填写，内容应为中文或英文字母</p>
				</div>
			</div>
		</div>

		<div class="form-group">
			<div class="col-sm-2"></div>
			<div class="col-sm-10">
				<button type="submit" name="next" value="true"
					class="btn btn-primary">下一步</button>
				<button type="submit" name="next" value="false"
					class="btn btn-success">上一步</button>
				<a href="../list">返回</a> &nbsp; <span><em class="text-danger">*</em>
					代表必填值</span>
				<c:if test="${param.alert eq 'saveOK'}">
					<span class="label label-success">保存完成</span>
				</c:if>
			</div>
		</div>
	</form:form>

	<script type="text/javascript">
		$(function() {

			$("select[name='nation']").change(function() {
				var value = $(this).val();
				if (value == 'OTHER') {
					$('div#nationRemark').removeClass('hidden');
				} else {
					$('div#nationRemark').addClass('hidden');
				}
			});

			$("select[name='origin']").change(function() {
				var value = $(this).val();
				if (value == 'WAIJI') {
					$('div#originRemark').removeClass('hidden');
				} else {
					$('div#originRemark').addClass('hidden');
				}
			});

		});
	</script>
</body>
</html>
