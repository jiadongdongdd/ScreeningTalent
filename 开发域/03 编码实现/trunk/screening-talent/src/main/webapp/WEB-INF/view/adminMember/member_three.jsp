<%--
 * 
 * @author andy
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../commons/taglib-header.jsp"%>
<html>
<head>
<title>招聘环节</title>
<link rel="stylesheet"
	href="${contextPath}/static/datepicker/datepicker3.css" />

<link rel="stylesheet" href="${contextPath}/static/select2/select2.css" />

</head>
<body>

	<ol class="breadcrumb">
		<li><a href="${contextPath}/admin/index">首页</a></li>
		<li><a href="${contextPath}/admin/company/NoVerify/false">企业管理</a>
		</li>
		<li><a href="${contextPath}/admin/member/list">职工信息</a></li>
		<li class="active">职工信息录入</li>
	</ol>


	<ul class="nav nav-tabs">
		<li role="presentation"><a href="../form_1/${formDto.memberUuid}">基本信息</a>
		</li>
		<li role="presentation"><a href="../form_2/${formDto.memberUuid}">基本信息</a>
		</li>
		<li role="presentation" class="active"><a
			href="../form_3/${formDto.memberUuid}">招聘环节</a> <jsp:include
				page="../tsca/tsca.jsp"></jsp:include></li>
		<li role="presentation"><a href="../form_4/${formDto.memberUuid}">离职环节</a>
		</li>
		<li role="presentation"><a href="../form_5/${formDto.memberUuid}">工作环节</a>
		</li>
	</ul>
	<br />


	<form:form commandName="formDto" cssClass="form-horizontal">
		<form:hidden path="memberUuid" />
		<input type="hidden" id="enterEntryDate"
			value="${param.fillEntryDate }" />
		<input type="hidden" id="entryDateEmpty" value="${entryDateEmpty }" />


		<h4 class="page-header text-primary">电话邀约</h4>

		<div class="form-group">
			<label class="control-label col-sm-2">电话邀约时间<em
				class="text-danger">*</em> </label>

			<div class="col-sm-10">
				<form:input path="telInvitationTime"
					cssClass="form-control datepicker" placeholder="电话邀约时间"
					maxlength="255" required="required" />
				<form:errors path="telInvitationTime" cssClass="label label-warning" />
				<p class="help-block">电话邀约时间</p>
			</div>
		</div>

		<div class="form-group">
			<label class="control-label col-sm-2">电话面试职位<em
				class="text-danger">*</em> </label>

			<div class="col-sm-10">
				<form:select id="telSelect" path="telIntentionPosition"
					cssClass="form-control-select2">
					<c:forEach items="${formDto.positionList }" var="p">
						<form:option value="${p.positionName }" label="${p.positionName }" />
					</c:forEach>
				</form:select>
				<form:errors path="telIntentionPosition"
					cssClass="label label-warning" />
				<p class="help-block">电话面试职位, 请务必填写</p>
			</div>
		</div>

		<div class="form-group">
			<label class="control-label col-sm-2">达成面试意向?<em
				class="text-danger">*</em> </label>

			<div class="col-sm-10">
				<label class="radio-inline"> <form:radiobutton
						path="telIntention" value="ENABLED" /> 是 </label> <label
					class="radio-inline"> <form:radiobutton path="telIntention"
						value="DISABLED" /> 否 </label>
				<form:errors path="telIntention" cssClass="label label-warning" />
				<p class="help-block">是否达成面试意向</p>
			</div>
		</div>

		<%--未达成面试意向--%>
		<div id="noIntention"
			class="${formDto.telIntention.disabled?'':'hidden'}">
			<div class="form-group">
				<label class="control-label col-sm-2">不参加面试原因 </label>

				<div class="col-sm-10">
					<form:select path="notJoinIntentionReason" cssClass="form-control">
						<form:option value="" label="请选择不参加面试原因" />
						<form:options items="${formDto.notJoinIntentionReasons}"
							itemLabel="label" />
					</form:select>
					<form:errors path="notJoinIntentionReason"
						cssClass="label label-warning" />
					<p class="help-block">不参加面试原因</p>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2">不参加面试的原因备注</label>

				<div class="col-sm-10">
					<form:input path="notJoinIntentionReasonRemark"
						cssClass="form-control" placeholder="不参加面试的原因备注" maxlength="255" />
					<form:errors path="notJoinIntentionReasonRemark"
						cssClass="label label-warning" />
					<p class="help-block">当不参加的原因为"其他", 企业自行填写原因</p>
				</div>
			</div>

		</div>

		<%--面试环节--%>
		<div id="intention"
			class="${formDto.telIntention.enabled?'':'hidden'}">
			<h4 class="page-header text-primary">面试环节</h4>

			<div class="form-group">
				<label class="control-label col-sm-2">面试时间<em
					class="text-danger">*</em> </label>

				<div class="col-sm-10">
					<form:input path="invitationTime"
						cssClass="form-control datepicker" placeholder="面试时间"
						maxlength="255" />
					<form:errors path="invitationTime" cssClass="label label-warning" />
					<p class="help-block">面试时间</p>
				</div>
			</div>

			<%-- 是否参加面试 --%>
			<div class="form-group">
				<label class="control-label col-sm-2">是否参加面试?<em
					class="text-danger">*</em> </label>

				<div class="col-sm-10">
					<label class="radio-inline"> <form:radiobutton
							path="joinInterview" value="true" /> 是 </label> <label
						class="radio-inline"> <form:radiobutton
							path="joinInterview" value="false" /> 否 </label>
					<form:errors path="joinInterview" cssClass="label label-warning" />
					<p class="help-block">是否参加面试</p>
				</div>
			</div>

			<!-- 参加面试 -->
			<div id="joinInterview"
				class="${formDto.joinInterview == true?'':'hidden'}">


				<div class="form-group">
					<label class="control-label col-sm-2">面试分数 </label>

					<div class="col-sm-10">
						<form:input path="intentionScore" cssClass="form-control"
							placeholder="面试分数" maxlength="255" />
						<form:errors path="intentionScore" cssClass="label label-warning" />
						<p class="help-block">若有面试分数则填写，按百分制</p>
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-sm-2">同意入职吗?</label>

					<div class="col-sm-10">
						<label class="radio-inline"> <form:radiobutton
								path="agreeEntry" value="ENABLED" /> 是 </label> <label
							class="radio-inline"> <form:radiobutton path="agreeEntry"
								value="DISABLED" /> 否 </label>
						<form:errors path="agreeEntry" cssClass="label label-warning" />
						<p class="help-block">是否同意入职</p>
					</div>
				</div>






				<%--不同意入职--%>
				<div id="disagreeEntry"
					class="${formDto.agreeEntry.disabled?'':'hidden'}">
					<div class="form-group">
						<label class="control-label col-sm-2">不同意入职原因?</label>

						<div class="col-sm-10">
							<label class="radio-inline"> <form:radiobutton
									path="personalOrCompany" value="true" /> 个人原因 </label> <label
								class="radio-inline"> <form:radiobutton
									path="personalOrCompany" value="false" /> 公司原因 </label>
							<form:errors path="entrySuccess" cssClass="label label-warning" />
							<p class="help-block">不同意入职原因是个人还是公司</p>
						</div>
					</div>

					<%--个人不同意入职原因--%>
					<div id="personalNotEntry"
						class="${formDto.personalOrCompany?'':'hidden'}">
						<div class="form-group">
							<label class="control-label col-sm-2">个人不同意入职原因 </label>

							<div class="col-sm-10">
								<form:select path="notEntryPersonalReason"
									cssClass="form-control">
									<form:option value="" label="请选择个人不同意原因" />
									<form:options items="${formDto.notEntryPersonalReasons}"
										itemLabel="label" />
								</form:select>
								<form:errors path="notEntryPersonalReason"
									cssClass="label label-warning" />
								<p class="help-block">个人不同意入职原因</p>
							</div>
						</div>

					</div>
					<%--公司不同意入职原因--%>
					<div id="companyNotEntry"
						class="${not formDto.personalOrCompany?'':'hidden'}">
						<div class="form-group">
							<label class="control-label col-sm-2">公司不同意入职原因 </label>

							<div class="col-sm-10">
								<form:select path="notEntryCompanyReason"
									cssClass="form-control">
									<form:option value="" label="请选择公司不同意原因" />
									<form:options items="${formDto.notEntryCompanyReasons}"
										itemLabel="label" />
								</form:select>
								<form:errors path="notEntryCompanyReason"
									cssClass="label label-warning" />
								<p class="help-block">公司不同意入职原因</p>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2">公司不同意入职原因备注 </label>

							<div class="col-sm-10">
								<form:input path="notEntryCompanyReasonRemark"
									cssClass="form-control" placeholder="公司不同意入职原因备注, 若有请填写"
									maxlength="255" />
								<form:errors path="notEntryCompanyReasonRemark"
									cssClass="label label-warning" />
								<p class="help-block">公司不同意入职原因备注</p>
							</div>
						</div>

					</div>
				</div>


				<%--入职环节, 同意入职--%>
				<div id="agreeEntry"
					class="${formDto.agreeEntry.enabled?'':'hidden'}">
					<h4 class="page-header text-primary">入职环节</h4>

					<div class="form-group">
						<label class="control-label col-sm-2">成功入职吗?</label>

						<div class="col-sm-10">
							<label class="radio-inline"> <form:radiobutton
									path="entrySuccess" value="ENABLED" /> 是 </label> <label
								class="radio-inline"> <form:radiobutton
									path="entrySuccess" value="DISABLED" /> 否 </label>
							<form:errors path="entrySuccess" cssClass="label label-warning" />
							<p class="help-block">是否成功入职</p>
						</div>
					</div>

				</div>

				<div id="entrySuccess"
					class="${formDto.entrySuccess.enabled?'':'hidden'}">
					<div class="form-group">
						<label class="control-label col-sm-2">入职时间<em
							class="text-danger">*</em> </label>

						<div class="col-sm-10">
							<form:input path="entryDate" cssClass="form-control datepicker"
								placeholder="入职时间" maxlength="255" />
							<form:errors path="entryDate" cssClass="label label-warning" />
							<p class="help-block">若成功则填写入职时间</p>
						</div>
					</div>

				</div>
			</div>
		</div>



		<hr />


		<div class="form-group">
			<div class="col-sm-2"></div>
			<div class="col-sm-10">
				<button type="submit" name="next" value="true"
					class="btn btn-primary">保存&下一步</button>
				<button type="submit" name="next" value="false"
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
	<script src="${contextPath}/static/select2/select2.js"></script>
	<script
		src="${contextPath}/static/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
	<script>
		$(function() {
			var enterEntryDate = $("#enterEntryDate").val();
			var entryDate = $('#entryDate').val();
			if (enterEntryDate == 'true' && entryDate == "") {
				alert("由于您已填写离职时间，请填写入职时间！");
			}
			new MemberThree();

			if (enterEntryDate == "") {
				var entryDateEmpty = $("#entryDateEmpty").val();
				if (entryDateEmpty == 'true' && entryDate == "") {
					alert("由于您已填写离职时间，请填写入职时间！");
				}

			}

			new TScAcnt('${formDto.memberUuid}');
			
		});
	</script>
</body>
</html>