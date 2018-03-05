<%--
 * 
 * @author andy
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../commons/taglib-header.jsp"%>
<html>
<head>
<title>离职环节</title>
<link rel="stylesheet"
	href="${contextPath}/static/datepicker/datepicker3.css" />
<link rel="stylesheet" href="${contextPath}/static/select2/select2.css" />
</head>
<body>

	<ol class="breadcrumb">
		<li><a href="${contextPath}/enterprise/index">首页</a></li>
		<li><a href="${contextPath}/enterprise/company/detail">企业管理</a></li>
		<li><a href="${contextPath}/enterprise/member/list">职工信息</a></li>
		<li class="active">职工信息录入</li>
	</ol>


	<ul class="nav nav-tabs">
		<li role="presentation"><a href="../form_1/${formDto.memberUuid}">基本信息</a>
		</li>
		<li role="presentation"><a href="../form_2/${formDto.memberUuid}">基本信息</a>
		</li>
		<li role="presentation"><a href="../form_3/${formDto.memberUuid}">招聘环节</a>
		</li>
		<li role="presentation" class="active"><a
			href="../form_4/${formDto.memberUuid}">离职环节</a></li>
		<li role="presentation"><a href="../form_5/${formDto.memberUuid}">工作环节</a>
		</li>
	</ul>
	<br />


	<form:form commandName="formDto" cssClass="form-horizontal"
		enctype="multipart/form-data">
		<form:hidden path="memberUuid" />


		<h4 class="page-header text-primary">离职原因</h4>
		<%--离职原因是否正常--%>
		<div class="form-group">
			<label class="control-label col-sm-2">离职原因是否正常</label>

			<div class="col-sm-10">
				<label class="radio-inline"> <form:radiobutton
						path="reasonStatus" value="true" /> 是 </label> <label
					class="radio-inline"> <form:radiobutton path="reasonStatus"
						value="false" /> 否 </label>

				<p class="help-block">离职时离职原因是否正常</p>
			</div>
		</div>
		<%--离职原因--%>
		<div id="reasonStatus"
			class="${formDto.reasonStatus == 'true' ?'hidden':''}">
			<div class="form-group">
				<label class="control-label col-sm-2">离职原因</label>

				<div class="col-sm-10">
					<form:select path="turnoverReason" cssClass="form-control">
						<form:option value="" label="选择离职原因" />
						<form:options items="${formDto.turnoverReasons}" itemLabel="label" />
					</form:select>
					<form:errors path="turnoverReason" cssClass="label label-warning" />
					<p class="help-block">离职原因</p>
				</div>
			</div>
			<%--劳动纠纷附件--%>
			<div id="laborAttachment"
				class="${formDto.turnoverReason.laborDispute?'':'hidden'}">
				<div class="form-group">
					<label class="control-label col-sm-2">劳动纠纷附件<em
						class="text-danger">*</em> </label>

					<div class="col-sm-10">
						<input type="file" name="laborFile" />
						<c:if test="${not empty formDto.laborDisputeFileGuid}">
							<a
								href="${contextPath}/public/download/${formDto.laborDisputeFileGuid}">下载劳动纠纷附件</a>
						</c:if>
						<form:hidden path="laborDisputeFileGuid" />
						<form:errors path="laborDisputeFileGuid"
							cssClass="label label-warning" />
						<p class="help-block">文件应在大小20M以内</p>
					</div>
				</div>
			</div>

			<%-- 开除原因 --%>
			<div id="goOutOptions"
				class="${formDto.turnoverReason.goOut?'':'hidden'}">
				<div class="form-group">
					<label class="control-label col-sm-2">开除原因</label>

					<div class="col-sm-10">
						<c:forEach items="${formDto.outReasonItems}" var="ite">
							<label class="checkbox-inline"> <form:checkbox
									path="goOutReasonItems" value="${ite}" /> ${ite.label} </label>
						</c:forEach>
						<form:errors path="goOutReasonItems"
							cssClass="label label-warning" />
						<p class="help-block">选择开除的原因(可多选)</p>
					</div>
				</div>

				<div id="revealSecrets"
					class="${fun:contains(formDto.goOutReasonItems,'REVEAL_COMPANY_SECRETS') ?'':'hidden' }">
					<div class="form-group">
						<label class="control-label col-sm-2">泄露公司机密附件<em
							class="text-danger">*</em> </label>

						<div class="col-sm-10">
							<input type="file" name="revealSecretsFile"/>
							<c:if test="${not empty formDto.revealSecretsFileGuid}">
								<a
									href="${contextPath}/public/download/${formDto.revealSecretsFileGuid}">下载泄露公司机密附件</a>
							</c:if>
							<form:hidden path="revealSecretsFileGuid" />
							<form:errors path="revealSecretsFileGuid"
								cssClass="label label-warning" />
							<p class="help-block">若开除原因中勾选了'泄露公司机密
								'，则必须上传文件，文件应在大小20M以内</p>
						</div>
					</div>
				</div>

				<div id="bribery"
					class="${fun:contains(formDto.goOutReasonItems,'BRIBERY') ?'':'hidden' }">
					<div class="form-group">
						<label class="control-label col-sm-2">贪污受贿挪用公款附件<em
							class="text-danger">*</em> </label>

						<div class="col-sm-10">
							<input type="file" name="briberyFile" />
							<c:if test="${not empty formDto.briberyFileGuid}">
								<a
									href="${contextPath}/public/download/${formDto.briberyFileGuid}">下载贪污受贿挪用公款附件</a>
							</c:if>
							<form:hidden path="briberyFileGuid" />
							<form:errors path="briberyFileGuid"
								cssClass="label label-warning" />
							<p class="help-block">若开除原因中勾选了'贪污受贿挪用公款
								'，则必须上传文件，文件应在大小20M以内</p>
						</div>
					</div>
				</div>

				<div id="rude"
					class="${fun:contains(formDto.goOutReasonItems,'RUDE') ?'':'hidden' }">
					<div class="form-group">
						<label class="control-label col-sm-2">言行粗鲁附件<em
							class="text-danger">*</em> </label>

						<div class="col-sm-10">
							<input type="file" name="rudeFile" />
							<c:if test="${not empty formDto.rudeFileGuid}">
								<a href="${contextPath}/public/download/${formDto.rudeFileGuid}">下载劳动纠纷附件</a>
							</c:if>
							<form:hidden path="rudeFileGuid" />
							<form:errors path="rudeFileGuid" cssClass="label label-warning" />
							<p class="help-block">若开除原因中勾选了'言行粗鲁
								'，则必须上传文件，文件应在大小20M以内</p>
						</div>
					</div>
				</div>

				<div id="destroyImportantAssets"
					class="${fun:contains(formDto.goOutReasonItems,'DESTROY_IMPORTANT_ASSETS') ?'':'hidden' }">
					<div class="form-group">
						<label class="control-label col-sm-2">破坏公司重要资产附件<em
							class="text-danger">*</em> </label>

						<div class="col-sm-10">
							<input type="file" name="destroyImportantAssetsFile"
								/>
							<c:if test="${not empty formDto.destroyImportantAssetsFileGuid}">
								<a
									href="${contextPath}/public/download/${formDto.destroyImportantAssetsFileGuid}">下载破坏公司重要资产附件</a>
							</c:if>
							<form:hidden path="destroyImportantAssetsFileGuid" />
							<form:errors path="destroyImportantAssetsFileGuid"
								cssClass="label label-warning" />
							<p class="help-block">若开除原因中勾选了'破坏公司重要资产
								'，则必须上传文件，文件应在大小20M以内</p>
						</div>
					</div>
				</div>
				<div id="stealing"
					class="${fun:contains(formDto.goOutReasonItems,'STEALING') ?'':'hidden' }">
					<div class="form-group">
						<label class="control-label col-sm-2">偷盗公司财物附件<em
							class="text-danger">*</em> </label>

						<div class="col-sm-10">
							<input type="file" name="stealingFile"  />
							<c:if test="${not empty formDto.stealingFileGuid}">
								<a
									href="${contextPath}/public/download/${formDto.stealingFileGuid}">下载偷盗公司财物附件</a>
							</c:if>
							<form:hidden path="stealingFileGuid" />
							<form:errors path="stealingFileGuid"
								cssClass="label label-warning" />
							<p class="help-block">若开除原因中勾选了'偷盗公司财物
								'，则必须上传文件，文件应在大小20M以内</p>
						</div>
					</div>
				</div>
				<div id="defamation"
					class="${fun:contains(formDto.goOutReasonItems,'DEFAMATION') ?'':'hidden' }">
					<div class="form-group">
						<label class="control-label col-sm-2">诋毁公司名誉附件<em
							class="text-danger">*</em> </label>

						<div class="col-sm-10">
							<input type="file" name="defamationFile"  />
							<c:if test="${not empty formDto.defamationFileGuid}">
								<a
									href="${contextPath}/public/download/${formDto.defamationFileGuid}">下载诋毁公司名誉附件</a>
							</c:if>
							<form:hidden path="defamationFileGuid" />
							<form:errors path="defamationFileGuid"
								cssClass="label label-warning" />
							<p class="help-block">若开除原因中勾选了'诋毁公司名誉
								'，则必须上传文件，文件应在大小20M以内</p>
						</div>
					</div>
				</div>
				<div id="misuseResources"
					class="${fun:contains(formDto.goOutReasonItems,'MISUSE_RESOURCES') ?'':'hidden' }">
					<div class="form-group">
						<label class="control-label col-sm-2">滥用公司资源附件<em
							class="text-danger">*</em> </label>

						<div class="col-sm-10">
							<input type="file" name="misuseResourcesFile"  />
							<c:if test="${not empty formDto.misuseResourcesFileGuid}">
								<a
									href="${contextPath}/public/download/${formDto.misuseResourcesFileGuid}">下载滥用公司资源附件</a>
							</c:if>
							<form:hidden path="misuseResourcesFileGuid" />
							<form:errors path="misuseResourcesFileGuid"
								cssClass="label label-warning" />
							<p class="help-block">若开除原因中勾选了'滥用公司资源
								'，则必须上传文件，文件应在大小20M以内</p>
						</div>
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-sm-2">'其他' 开除注明</label>

					<div class="col-sm-10">
						<form:input path="turnoverReasonRemark" cssClass="form-control"
							placeholder="'其他' 注明原因" />
						<form:errors path="turnoverReasonRemark"
							cssClass="label label-warning" />
						<p class="help-block">若开除原因选择'其他' ,则注明原因</p>
					</div>
				</div>
			</div>
		</div>



		<h4 class="page-header text-primary">离职过程</h4>
		<div class="form-group">
			<label class="control-label col-sm-2">离职时间</label>

			<div class="col-sm-10">
				<form:input path="turnoverDate" cssClass="form-control datepicker"
					placeholder="离职时间" />
				<form:errors path="turnoverDate" cssClass="label label-warning" />
				<span id="turnError" class="label label-warning"
					style="display: none;">离职时间应&gt;=入职时间</span>
				<p class="help-block">离职时间</p>
			</div>
		</div>

		<div class="form-group">
			<label class="control-label col-sm-2">离职时职位</label>

			<div class="col-sm-10">
				<form:select id="telSelect" path="turnoverPosition" cssClass="form-control-select2">
					<form:option value="" label="选择职位" />
					<c:forEach items="${formDto.positionList }" var="p">
						<form:option value="${p.positionName }" label="${p.positionName }" />
					</c:forEach>
				</form:select>
				<form:errors path="turnoverPosition" cssClass="label label-warning" />
				<p class="help-block">离职时职位</p>
			</div>
		</div>

		<div class="form-group">
			<label class="control-label col-sm-2">离职收入</label>

			<div class="col-sm-10">
				<form:select path="turnoverIncome" cssClass="form-control">
					<form:option value="" label="选择离职收入" />
					<form:options items="${formDto.turnoverIncomes}" itemLabel="label" />
				</form:select>
				<form:errors path="turnoverIncome" cssClass="label label-warning" />
				<p class="help-block">离职收入</p>
			</div>
		</div>
		<%--离职过程是否正常--%>
		<div class="form-group">
			<label class="control-label col-sm-2">离职过程是否正常</label>

			<div class="col-sm-10">
				<label class="radio-inline"> <form:radiobutton
						path="processStatus" value="true" /> 是 </label> <label
					class="radio-inline"> <form:radiobutton
						path="processStatus" value="false" /> 否 </label>

				<p class="help-block">离职时过程是否正常</p>
			</div>
		</div>
		<%--离职过程不正常--%>
		<div id="processStatus"
			class="${formDto.processStatus == 'true' ? 'hidden':''}">
			<div class="form-group">
				<label class="control-label col-sm-2">离职过程</label>

				<div class="col-sm-10">
					<form:select path="turnoverProcess" cssClass="form-control">
						<form:option value="" label="选择离职过程" />
						<form:options items="${formDto.turnoverProcesses}"
							itemLabel="label" />
					</form:select>
					<form:errors path="turnoverProcess" cssClass="label label-warning" />
					<p class="help-block">离职过程</p>
				</div>
			</div>

			<%--带走公司财产的选项--%>
			<div id="takeCompanyRs"
				class="${formDto.turnoverProcess.takeResources?'':'hidden'}">
				<div class="form-group">
					<label class="control-label col-sm-2">带走公司财产的选项</label>

					<div class="col-sm-10">
						<c:forEach items="${formDto.takeRsProcessItems}" var="ite">
							<label class="checkbox-inline"> <form:checkbox
									path="takeCompanyRsItems" value="${ite}" /> ${ite.label} </label>
						</c:forEach>
						<form:errors path="takeCompanyRsItems"
							cssClass="label label-warning" />
						<p class="help-block">带走公司财产的选项</p>
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-sm-2">带走公司财产附件</label>

					<div class="col-sm-10">
						<input type="file" name="takeCompanyRsFile" />
						<c:if test="${not empty formDto.takeCompanyRsFileGuid}">
							<a
								href="${contextPath}/public/download/${formDto.takeCompanyRsFileGuid}">下载带走公司财产附件</a>
						</c:if>
						<form:hidden path="takeCompanyRsFileGuid" />
						<form:errors path="takeCompanyRsFileGuid"
							cssClass="label label-warning" />
						<p class="help-block">勾选'侵吞公司财产，欠款不还，盗取保密信息'时,必须上传文件证明,文件应在大小20M以内</p>
					</div>
				</div>

			</div>


			<%--离职过程中 OTHER 的注明信息--%>
			<div id="otherOutReason"
				class="${formDto.turnoverProcess eq 'OTHER'?'':'hidden'}">
				<div class="form-group">
					<label class="control-label col-sm-2">'其他' 离职过程注明</label>

					<div class="col-sm-10">
						<form:input path="otherProcessRemark" cssClass="form-control"
							placeholder="'其他' 注明原因" />
						<form:errors path="otherProcessRemark"
							cssClass="label label-warning" />
						<p class="help-block">'其他' 离职过程注明</p>
					</div>
				</div>
			</div>
		</div>



		<h4 class="page-header text-primary">离职后状态</h4>

		<%--离职后状态是否正常--%>
		<div class="form-group">
			<label class="control-label col-sm-2">离职后状态是否正常</label>

			<div class="col-sm-10">
				<label class="radio-inline"> <form:radiobutton
						path="processAfterStatus" value="true" /> 是 </label> <label
					class="radio-inline"> <form:radiobutton
						path="processAfterStatus" value="false" /> 否 </label>

				<p class="help-block">离职后状态是否正常</p>
			</div>
		</div>
		<!-- 离职后状态不正常 -->
		<div id="processAfterStatus"
			class="${formDto.processAfterStatus == 'true'?'hidden':' ' }">
			<div class="form-group">
				<label class="control-label col-sm-2">是否违反培训协议</label>

				<div class="col-sm-10">
					<label class="radio-inline"> <form:radiobutton
							path="breachTrainingAgreement" value="true" /> 是 </label> <label
						class="radio-inline"> <form:radiobutton
							path="breachTrainingAgreement" value="false" /> 否 </label>

					<p class="help-block">是否违反培训协议</p>
				</div>
			</div>

			<%--违反培训协议的 附件证据--%>
			<div id="brTrainAgree"
				class="${formDto.breachTrainingAgreement?'':'hidden'}">
				<div class="form-group">
					<label class="control-label col-sm-2">违反培训协议的 附件证据<em
						class="text-danger">*</em> </label>

					<div class="col-sm-10">
						<input type="file" name="brTrainFile" />
						<c:if test="${not empty formDto.breachTrainingAgreementFileGuid}">
							<a
								href="${contextPath}/public/download/${formDto.breachTrainingAgreementFileGuid}">下载附件证件</a>
						</c:if>
						<form:hidden path="breachTrainingAgreementFileGuid" />
						<form:errors path="breachTrainingAgreementFileGuid"
							cssClass="label label-warning" />
						<p class="help-block">文件应在大小20M以内</p>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2"> 是否在竞业禁止期内</label>

				<div class="col-sm-10">
					<label class="radio-inline"> <form:radiobutton
							path="outStopPeriod" value="true" /> 是 </label> <label
						class="radio-inline"> <form:radiobutton
							path="outStopPeriod" value="false" /> 否 </label>

					<p class="help-block">是否在竞业禁止期内</p>
				</div>
			</div>
			<%--在避业禁止期内 附件证据--%>
			<div id="outStopPeriFile"
				class="${formDto.outStopPeriod?'':'hidden'}">
				<div class="form-group">
					<label class="control-label col-sm-2">是否在竞业禁止期内 附件证据 <em
						class="text-danger">*</em> </label>

					<div class="col-sm-10">
						<input type="file" name="stopPeriFile" />
						<c:if test="${not empty formDto.outStopPeriodFileGuid}">
							<a
								href="${contextPath}/public/download/${formDto.outStopPeriodFileGuid}">下载附件证件</a>
						</c:if>
						<form:hidden path="outStopPeriodFileGuid" />
						<form:errors path="outStopPeriodFileGuid"
							cssClass="label label-warning" />
						<p class="help-block">文件应在大小20M以内</p>
					</div>
				</div>
			</div>


			<div class="form-group">
				<label class="control-label col-sm-2">离职后用非法手段恶意毁坏公司形象</label>

				<div class="col-sm-10">
					<label class="radio-inline"> <form:radiobutton
							path="illegalDestroyCompanyFace" value="true" /> 是 </label> <label
						class="radio-inline"> <form:radiobutton
							path="illegalDestroyCompanyFace" value="false" /> 否 </label>

					<p class="help-block">离职后用非法手段恶意毁坏公司形象</p>
				</div>
			</div>
			<%--用非法手段恶意毁坏公司形象 附件证据--%>
			<div id="illegalDestoryCFile"
				class="${formDto.illegalDestroyCompanyFace?'':'hidden'}">
				<div class="form-group">
					<label class="control-label col-sm-2">非法手段恶意毁坏公司形象 附件证据 <em
						class="text-danger">*</em> </label>

					<div class="col-sm-10">
						<input type="file" name="illegalDesCFFile" />
						<c:if
							test="${not empty formDto.illegalDestroyCompanyFaceFileGuid}">
							<a
								href="${contextPath}/public/download/${formDto.illegalDestroyCompanyFaceFileGuid}">下载附件证件</a>
						</c:if>
						<form:hidden path="illegalDestroyCompanyFaceFileGuid" />
						<form:errors path="illegalDestroyCompanyFaceFileGuid"
							cssClass="label label-warning" />
						<p class="help-block">文件应在大小20M以内</p>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2">离职后和原单位发生劳动诉讼或法律纠纷</label>

				<div class="col-sm-10">
					<label class="radio-inline"> <form:radiobutton
							path="legalDisputes" value="true" /> 是 </label> <label
						class="radio-inline"> <form:radiobutton
							path="legalDisputes" value="false" /> 否 </label>

					<p class="help-block">离职后和原单位发生劳动诉讼或法律纠纷</p>
				</div>
			</div>
			<%--离职后和原单位发生劳动诉讼或法律纠纷 附件证据--%>
			<div id="legalDispuFile" class="${formDto.legalDisputes?'':'hidden'}">
				<div class="form-group">
					<label class="control-label col-sm-2">离职后和原单位发生劳动诉讼或法律纠纷
						附件证据 <em class="text-danger">*</em> </label>

					<div class="col-sm-10">
						<input type="file" name="legalDisFile" />
						<c:if test="${not empty formDto.legalDisputesFileGuid}">
							<a
								href="${contextPath}/public/download/${formDto.legalDisputesFileGuid}">下载附件证件</a>
						</c:if>
						<form:hidden path="legalDisputesFileGuid" />
						<form:errors path="legalDisputesFileGuid"
							cssClass="label label-warning" />
						<p class="help-block">文件应在大小20M以内</p>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2">离职后 其他信息注明<em
					class="text-danger">*</em> </label>

				<div class="col-sm-10">
					<form:input path="outOtherRemark" cssClass="form-control"
						placeholder="其他信息注明" />
					<form:errors path="outOtherRemark" cssClass="label label-warning" />
					<p class="help-block">离职后如有其它损害公司利益的行为请注明，若没有，请填写“无"</p>
				</div>
			</div>
			<div id="outOtherFileOption"
				class="${formDto.outOtherRemark == '无' ?'hidden':''  }">
				<div class="form-group">
					<label class="control-label col-sm-2">离职后 其他信息注明 附件<em
						class="text-danger">*</em> </label>

					<div class="col-sm-10">
						<input type="file" name="outOtherFile" />
						<c:if test="${not empty formDto.outOtherFileGuid}">
							<a
								href="${contextPath}/public/download/${formDto.outOtherFileGuid}">下载附件证件</a>
						</c:if>
						<form:hidden path="outOtherFileGuid" />
						<form:errors path="outOtherFileGuid"
							cssClass="label label-warning" />
						<p class="help-block">文件应在大小20M以内</p>
					</div>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2">贵公司会再次雇佣此名员工吗<em
				class="text-danger">*</em> </label>

			<div class="col-sm-10">
				<label class="radio-inline"> 
					<form:radiobutton path="hireAgain"  id="hireAgain1" /> 是 </label> <label class="radio-inline">
					<form:radiobutton path="hireAgain"  id="hireAgain2" /> 否 </label>

				<p class="help-block">必填</p>
			</div>
		</div>
		<hr />


		<div class="form-group">
			<div class="col-sm-2"></div>
			<div class="col-sm-10">
				<button id="submitNext" type="submit" name="next" value="true"
					class="btn btn-primary">保存&下一步</button>
				<button id="submitPrev" type="submit" name="next" value="false"
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
			var add = "${formDto.add}"; 
			if(add == "false"){
				var hireAgain = "${formDto.hireAgain}";
				if(hireAgain == "true"){
					$("#hireAgain1").attr("checked","checked").val("true");
				}else{
					$("#hireAgain2").attr("checked","checked").val("false");
				}
			}
			new MemberFour('${formDto.memberUuid}');
		});
	</script>
</body>
</html>