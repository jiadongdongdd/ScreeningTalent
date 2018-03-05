<%--
 * Copyright (c) 2015 MONKEYK Information Technology Co. Ltd
 * www.monkeyk.com
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * MONKEYK Information Technology Co. Ltd ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with MONKEYK Information Technology Co. Ltd.
--%>
<%--
 * 
 * @author Shengzhao Li
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../commons/taglib-header.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<title>职工详情</title>
<style type="text/css">
#formDto .form-group {
	white-space: nowrap;
}

#formDto .form-group div {
	padding-right: 5px;
	overflow: hidden;
}
</style>
</head>
<body>
	<ol class="breadcrumb">
		<li><a href="${contextPath}/enterprise/index">首页</a></li>
		<li><a href="${contextPath }/enterprise/company/detail">企业管理</a>
		</li>
		<li><a href="${contextPath }/enterprise/member/query_list">全站搜索</a>
		</li>
		<li><a class="active">职工详情</a></li>
	</ol>

	<div class="row">
		<div class="col-lg-12 col-sm-12">
			<form:form action="" commandName="paginated" cssClass="form-inline"
				id="filterForm" method="get">


			</form:form>
		</div>
	</div>


	<form:form commandName="formDto" class="form-horizontal">

		<!-- 基本信息 -->

		<div class="">
			<!-- 定义的布局必须添加到class定义row中 //-->

			<div class="row">
				<h3 class="media-heading">公司：${formDto.basicFormDto.companyName
					}</h3>

				<div class="media">
					<div class="media-body">

						<h3 class="media-heading">${formDto.basicFormDto.formOneDto.chName
							}</h3>


						<div class="form-group">
							<div class="col-lg-2 col-md-3 col-xs-12"
								title="${formDto.basicFormDto.formOneDto.mobile
								}">手机:&nbsp;${formDto.basicFormDto.formOneDto.mobile
								}</div>

							<div class="col-lg-4 col-md-5 col-xs-12"
								title="${formDto.basicFormDto.formOneDto.email
								}">电子邮箱:&nbsp;${formDto.basicFormDto.formOneDto.email
								}</div>

							<div class="col-lg-4 col-md-4 col-xs-12"
								title="${formDto.basicFormDto.formOneDto.idNumber
								}">身份证号:&nbsp;${formDto.basicFormDto.formOneDto.idNumber
								}</div>

						</div>

						<div class="form-group">
							<div class="col-lg-2 col-md-2 col-xs-12"
								title="${formDto.basicFormDto.formTwoDto.gender.label
								}">性别:&nbsp;${formDto.basicFormDto.formTwoDto.gender.label
								}</div>

							<div class="col-lg-4 col-md-4 col-xs-12"
								title="${formDto.basicFormDto.formOneDto.birthday
								}">出生日期:&nbsp;${formDto.basicFormDto.formOneDto.birthday
								}</div>

							<div class="col-lg-4 col-md-4 col-xs-12"
								title="${formDto.basicFormDto.formTwoDto.school
								}">毕业院校:&nbsp;${formDto.basicFormDto.formTwoDto.school
								}</div>
						</div>

						<div class="form-group">
							<div class="col-lg-2 col-md-2 col-xs-12"
								title="${formDto.basicFormDto.formTwoDto.nation.label
								}">民族:&nbsp;${formDto.basicFormDto.formTwoDto.nation.label
								}</div>

							<c:if
								test="${formDto.basicFormDto.formTwoDto.nation.label == '其他' }">
								<div class="col-lg-4 col-md-4 col-xs-12"
									title="${formDto.basicFormDto.formTwoDto.nationOther
									}">民族备注:&nbsp;${formDto.basicFormDto.formTwoDto.nationOther
									}</div>
							</c:if>

							<div class="col-lg-4 col-md-4 col-xs-12"
								title="${formDto.basicFormDto.formTwoDto.politicalStatus.label
								}">政治面貌:&nbsp;${formDto.basicFormDto.formTwoDto.politicalStatus.label
								}</div>

							<div class="col-lg-2 col-md-2 col-xs-12"
								title="${formDto.basicFormDto.formTwoDto.origin.label
								}">籍贯:&nbsp;${formDto.basicFormDto.formTwoDto.origin.label
								}</div>
						</div>
						<div class="form-group">
							<div class="col-lg-2 col-md-2 col-xs-12"
								title="${formDto.basicFormDto.formTwoDto.degree.label
									}">学历:&nbsp;${formDto.basicFormDto.formTwoDto.degree.label
								}</div>
						</div>
						<c:if
							test="${formDto.basicFormDto.formTwoDto.origin.label == '外籍' }">
							<div class="form-group">
								<div class="col-lg-2 col-md-2 col-xs-12"
									title="${formDto.basicFormDto.formTwoDto.foreignOrigin
									}">外籍备注:&nbsp;${formDto.basicFormDto.formTwoDto.foreignOrigin
									}</div>
							</div>
						</c:if>
					</div>
					<div class="media-left">
						<a> <c:if
								test="${formDto.basicFormDto.formTwoDto.gender.label == '男' }">

								<img class="media-object"
									src="${contextPath }/static/img/member/male1.png"
									alt="${formDto.basicFormDto.formOneDto.chName }" width="150"
									height="140">
							</c:if> <c:if
								test="${formDto.basicFormDto.formTwoDto.gender.label == '女' }">

								<img class="media-object"
									src="${contextPath }/static/img/member/female1.png"
									alt="${formDto.basicFormDto.formOneDto.chName }" width="150"
									height="140">
							</c:if> </a>
					</div>
				</div>

			</div>


			<div class="row">
				<c:if test="${formDto.partName == 'tel' }">
					<div class="page-header">
						<h1>
							<small>电话邀约</small>
						</h1>
					</div>


					<div class="form-group">
						<div class="col-lg-3 col-md-3 col-xs-12">邀约时间:&nbsp;${formDto.recruitmentFormDto.telInvitationTime
							}</div>

						<div class="col-lg-3 col-md-3 col-xs-12">面试职位:&nbsp;${formDto.recruitmentFormDto.telIntentionPosition
							}</div>

						<div class="col-lg-3 col-md-3 col-xs-12">达成面试意向:&nbsp;${formDto.recruitmentFormDto.telIntention.label
							}</div>

					</div>

					<c:if
						test="${formDto.recruitmentFormDto.telIntention.label == '否' }">

						<!-- 不参加面试原因 -->
						<div class="form-group">
							<div class="col-lg-3 col-md-3 col-xs-12">不参加面试原因:&nbsp;${formDto.recruitmentFormDto.notJoinIntentionReason.label
								}</div>

							<div class="col-lg-3 col-md-3 col-xs-12">企业自行填写原因:&nbsp;${formDto.recruitmentFormDto.notJoinIntentionReasonRemark
								}</div>

						</div>
					</c:if>
				</c:if>

				<br> <br>
				<c:if test="${formDto.partName == 'interview' }">
					<div class="page-header">
						<h1>
							<small>面试记录</small>
						</h1>
					</div>

					<c:if
						test="${formDto.recruitmentFormDto.telIntention.label == '是' }">
						<!-- 面试记录 -->
						<div class="form-group">
							<div class="col-lg-3 col-md-3 col-xs-12">面试时间:&nbsp;${formDto.recruitmentFormDto.invitationTime
								}</div>

							<div class="col-lg-3 col-md-3 col-xs-12">面试分数:&nbsp;${formDto.recruitmentFormDto.intentionScore
								}</div>

							<div class="col-lg-3 col-md-3 col-xs-12">是否同意入职:&nbsp;${formDto.recruitmentFormDto.agreeEntry.label
								}</div>

						</div>
					</c:if>

					<c:if test="${formDto.recruitmentFormDto.agreeEntry.label == '否' }">
						<div class="form-group">

							<div class="col-lg-3 col-md-3 col-xs-12">不同意入职的原因:&nbsp;${formDto.recruitmentFormDto.personalOrCompany
								== true ?'个人':'公司' }</div>
							<c:if
								test="${formDto.recruitmentFormDto.personalOrCompany == true }">
								<div class="col-lg-3 col-md-3 col-xs-12">不同意入职的原因(个人):&nbsp;${formDto.recruitmentFormDto.notEntryPersonalReason.label
									}</div>
							</c:if>
							<c:if
								test="${formDto.recruitmentFormDto.personalOrCompany == false }">
								<div class="col-lg-3 col-md-3 col-xs-12">不同意入职的原因
									(公司):&nbsp;${formDto.recruitmentFormDto.notEntryCompanyReason.label
									}</div>

							</c:if>
						</div>

						<c:if
							test="${formDto.recruitmentFormDto.personalOrCompany == false }">
							<div class="form-group">
								<div class="col-lg-12 col-md-12 col-xs-12">不同意入职的原因
									(公司备注):&nbsp;${formDto.recruitmentFormDto.notEntryCompanyReasonRemark
									}</div>
							</div>
						</c:if>

					</c:if>

				</c:if>


				<br> <br>

				<c:if test="${formDto.partName == 'entry' }">
					<div class="page-header">
						<h1>
							<small>入职记录</small>
						</h1>
					</div>


					<div class="form-group">
						<div class="col-lg-3 col-md-3 col-xs-12">入职时间:&nbsp;${formDto.entryFormDto.entryDateTime
							}</div>

						<div class="col-lg-3 col-md-3 col-xs-12">离职时间:&nbsp;${formDto.entryFormDto.turnoverFormDto.turnoverDate
							}</div>

						<div class="col-lg-3 col-md-3 col-xs-12">离职时职位:&nbsp;${formDto.entryFormDto.turnoverFormDto.turnoverPosition
							}</div>
						<div class="col-lg-3 col-md-3 col-xs-12">离职时收入:&nbsp;${formDto.entryFormDto.turnoverFormDto.turnoverIncome.label
							}</div>

					</div>

					<!-- 离职原因是否正常 -->
					<div class="form-group">
						<div class="col-lg-3 col-md-3 col-xs-12">离职原因是否正常:&nbsp;${formDto.entryFormDto.turnoverFormDto.reasonStatus
							== true ? '是' :'否' }</div>
					</div>
					<c:if
						test="${formDto.entryFormDto.turnoverFormDto.reasonStatus == false  }">

						<!-- 离职原因不正常 (劳动纠纷)-->
						<div class="form-group">
							<div class="col-lg-3 col-md-3 col-xs-12">离职原因:&nbsp;${formDto.entryFormDto.turnoverFormDto.turnoverReason.label
								}</div>
							<c:if
								test="${formDto.entryFormDto.turnoverFormDto.turnoverReason.label == '劳动纠纷' }">

								<div class="col-lg-5 col-md-5 col-xs-12">
									离职原因中 劳动纠纷的 附件:&nbsp;
									<c:if
										test="${not empty formDto.entryFormDto.turnoverFormDto.laborDisputeFileGuid }">
										<a
											href="${contextPath}/public/download/${formDto.entryFormDto.turnoverFormDto.laborDisputeFileGuid}">下载附件证件</a>
									</c:if>
								</div>
							</c:if>
						</div>

						<c:if
							test="${formDto.entryFormDto.turnoverFormDto.turnoverReason.label == '开除' }">
							<!-- 离职原因不正常 (开除)-->
							<div class="form-group">
								<div class="col-lg-12 col-md-12 col-xs-12">
									开除原因:&nbsp;
									<c:forEach
										items="${formDto.entryFormDto.turnoverFormDto.goOutReasonItems
									}"
										var="rItem">
									${rItem.label }
									</c:forEach>
								</div>
							</div>

							<!--开除 附件开始 -->
							<c:if
								test="${fun:contains(formDto.entryFormDto.turnoverFormDto.goOutReasonItems,'REVEAL_COMPANY_SECRETS') }">
								<div class="form-group">
									<div class="col-lg-3 col-md-3 col-xs-12">
										泄露公司机密附件证据:&nbsp;
										<c:if
											test="${not empty formDto.entryFormDto.turnoverFormDto.revealSecretsFileGuid }">
											<a
												href="${contextPath}/public/download/${formDto.entryFormDto.turnoverFormDto.revealSecretsFileGuid}">下载附件证件</a>
										</c:if>
									</div>
								</div>
							</c:if>
							<c:if
								test="${fun:contains(formDto.entryFormDto.turnoverFormDto.goOutReasonItems,'BRIBERY') }">
								<div class="form-group">
									<div class="col-lg-3 col-md-3 col-xs-12">
										贪污受贿挪用公款附件证据:&nbsp;
										<c:if
											test="${not empty formDto.entryFormDto.turnoverFormDto.briberyFileGuid }">
											<a
												href="${contextPath}/public/download/${formDto.entryFormDto.turnoverFormDto.briberyFileGuid}">下载附件证件</a>
										</c:if>
									</div>
								</div>
							</c:if>

							<c:if
								test="${fun:contains(formDto.entryFormDto.turnoverFormDto.goOutReasonItems,'RUDE') }">
								<div class="form-group">
									<div class="col-lg-3 col-md-3 col-xs-12">
										言行粗鲁附件证据:&nbsp;
										<c:if
											test="${not empty formDto.entryFormDto.turnoverFormDto.rudeFileGuid }">
											<a
												href="${contextPath}/public/download/${formDto.entryFormDto.turnoverFormDto.rudeFileGuid}">下载附件证件</a>
										</c:if>
									</div>
								</div>
							</c:if>

							<c:if
								test="${fun:contains(formDto.entryFormDto.turnoverFormDto.goOutReasonItems,'DESTROY_IMPORTANT_ASSETS') }">
								<div class="form-group">
									<div class="col-lg-3 col-md-3 col-xs-12">
										破坏公司重要资产附件证据:&nbsp;
										<c:if
											test="${not empty formDto.entryFormDto.turnoverFormDto.destroyImportantAssetsFileGuid }">
											<a
												href="${contextPath}/public/download/${formDto.entryFormDto.turnoverFormDto.destroyImportantAssetsFileGuid}">下载附件证件</a>
										</c:if>
									</div>
								</div>
							</c:if>
							<c:if
								test="${fun:contains(formDto.entryFormDto.turnoverFormDto.goOutReasonItems,'STEALING') }">
								<div class="form-group">
									<div class="col-lg-3 col-md-3 col-xs-12">
										偷盗公司财物附件证据:&nbsp;
										<c:if
											test="${not empty formDto.entryFormDto.turnoverFormDto.stealingFileGuid }">
											<a
												href="${contextPath}/public/download/${formDto.entryFormDto.turnoverFormDto.stealingFileGuid}">下载附件证件</a>
										</c:if>
									</div>
								</div>
							</c:if>
							<c:if
								test="${fun:contains(formDto.entryFormDto.turnoverFormDto.goOutReasonItems,'DEFAMATION') }">
								<div class="form-group">
									<div class="col-lg-3 col-md-3 col-xs-12">
										诋毁公司名誉附件证据:&nbsp;
										<c:if
											test="${not empty formDto.entryFormDto.turnoverFormDto.defamationFileGuid }">
											<a
												href="${contextPath}/public/download/${formDto.entryFormDto.turnoverFormDto.defamationFileGuid}">下载附件证件</a>
										</c:if>
									</div>
								</div>
							</c:if>
							<c:if
								test="${fun:contains(formDto.entryFormDto.turnoverFormDto.goOutReasonItems,'MISUSE_RESOURCES') }">
								<div class="form-group">
									<div class="col-lg-3 col-md-3 col-xs-12">
										滥用公司资源附件证据:&nbsp;
										<c:if
											test="${not empty formDto.entryFormDto.turnoverFormDto.misuseResourcesFileGuid }">
											<a
												href="${contextPath}/public/download/${formDto.entryFormDto.turnoverFormDto.misuseResourcesFileGuid}">下载附件证件</a>
										</c:if>
									</div>
								</div>
							</c:if>
							<!-- 开除附件结束 -->

							<div class="form-group">
								<div class="col-lg-12 col-md-12 col-xs-12">开除原因（其他）备注:&nbsp;${formDto.entryFormDto.turnoverFormDto.turnoverReasonRemark
									}</div>
							</div>
						</c:if>
					</c:if>

					<!-- 离职过程 -->

					<div class="form-group">
						<div class="col-lg-3 col-md-3 col-xs-12">离职过程是否正常:&nbsp;${formDto.entryFormDto.turnoverFormDto.processStatus
							== true ? '是':'否' }</div>
					</div>

					<c:if
						test="${formDto.entryFormDto.turnoverFormDto.processStatus == false }">
						<div class="form-group">
							<div class="col-lg-3 col-md-3 col-xs-12">离职过程:&nbsp;${formDto.entryFormDto.turnoverFormDto.turnoverProcess.label}</div>
						</div>
						<c:if
							test="${formDto.entryFormDto.turnoverFormDto.turnoverProcess.label == '带走公司财产信息等' }">
							<div class="form-group">
								<div class="col-lg-12 col-md-12 col-xs-12">
									带走公司财产的选项:&nbsp;
									<c:forEach
										items="${formDto.entryFormDto.turnoverFormDto.takeCompanyRsItems
									}"
										var="rItem">
									${rItem.label }
									</c:forEach>
								</div>
							</div>
						</c:if>


						<c:if
							test="${formDto.entryFormDto.turnoverFormDto.turnoverProcess.label == '其他' }">
							<div class="form-group">
								<div class="col-lg-3 col-md-3 col-xs-12">注明信息:&nbsp;${formDto.entryFormDto.turnoverFormDto.otherProcessRemark}</div>
							</div>
						</c:if>
					</c:if>


					<!-- 离职后状态 -->
					<div class="form-group">
						<div class="col-lg-3 col-md-3 col-xs-12">离职后状态是否正常:&nbsp;${formDto.entryFormDto.turnoverFormDto.processAfterStatus
							== true ? '是':'否'}</div>
					</div>
					
					<div class="form-group">
						<div class="col-lg-3 col-md-3 col-xs-12">是否再次雇佣:&nbsp;${formDto.entryFormDto.turnoverFormDto.hireAgain
							== true ? '是':'否' }</div>
					</div>

					<c:if
						test="${formDto.entryFormDto.turnoverFormDto.processAfterStatus
						== false }">
						<div class="form-group">
							<div class="col-lg-3 col-md-3 col-xs-12">是否违反培训协议:&nbsp;${formDto.entryFormDto.turnoverFormDto.breachTrainingAgreement
								== true ? '是':'否'}</div>
							<div class="col-lg-3 col-md-3 col-xs-12">
								附件证据:&nbsp;
								<c:if
									test="${not empty formDto.entryFormDto.turnoverFormDto.breachTrainingAgreementFileGuid }">
									<a
										href="${contextPath}/public/download/${formDto.entryFormDto.turnoverFormDto.breachTrainingAgreementFileGuid}">下载附件证件</a>
								</c:if>
							</div>
						</div>

						<div class="form-group">
							<div class="col-lg-3 col-md-3 col-xs-12">是否在竞业禁止期内:&nbsp;${formDto.entryFormDto.turnoverFormDto.outStopPeriod
								== true ? '是':'否'}</div>
							<div class="col-lg-3 col-md-3 col-xs-12">
								附件证据:&nbsp;
								<c:if
									test="${not empty formDto.entryFormDto.turnoverFormDto.outStopPeriodFileGuid }">
									<a
										href="${contextPath}/public/download/${formDto.entryFormDto.turnoverFormDto.outStopPeriodFileGuid}">下载附件证件</a>
								</c:if>
							</div>
						</div>

						<div class="form-group">
							<div class="col-lg-3 col-md-3 col-xs-12">是否恶意毁坏公司形象:&nbsp;${formDto.entryFormDto.turnoverFormDto.illegalDestroyCompanyFace
								== true ? '是':'否'}</div>
							<div class="col-lg-3 col-md-3 col-xs-12">
								附件证据:&nbsp;
								<c:if
									test="${not empty formDto.entryFormDto.turnoverFormDto.illegalDestroyCompanyFaceFileGuid }">
									<a
										href="${contextPath}/public/download/${formDto.entryFormDto.turnoverFormDto.illegalDestroyCompanyFaceFileGuid}">下载附件证件</a>
								</c:if>
							</div>
						</div>

						<div class="form-group">
							<div class="col-lg-5 col-md-5 col-xs-12">离职后和原单位发生劳动诉讼或法律纠纷:&nbsp;${formDto.entryFormDto.turnoverFormDto.legalDisputes
								== true ? '是':'否'}</div>
							<div class="col-lg-3 col-md-3 col-xs-12">
								附件证据:&nbsp;
								<c:if
									test="${not empty formDto.entryFormDto.turnoverFormDto.legalDisputesFileGuid }">
									<a
										href="${contextPath}/public/download/${formDto.entryFormDto.turnoverFormDto.legalDisputesFileGuid}">下载附件证件</a>
								</c:if>
							</div>
						</div>

						<div class="form-group">
							<div class="col-lg-5 col-md-5 col-xs-12">其他信息注明:&nbsp;${formDto.entryFormDto.turnoverFormDto.outOtherRemark
								}</div>
							<div class="col-lg-3 col-md-3 col-xs-12">
								其他信息注明 附件:&nbsp;
								<c:if
									test="${not empty formDto.entryFormDto.turnoverFormDto.outOtherFileGuid }">
									<a
										href="${contextPath}/public/download/${formDto.entryFormDto.turnoverFormDto.outOtherFileGuid}">下载附件证件</a>
								</c:if>
							</div>
						</div>
					</c:if>





					<div class="form-group">
						<div class="col-lg-5 col-md-5 col-xs-12">嘉奖类型:&nbsp;${formDto.entryFormDto.workedFormDto.awards.label
							}</div>
						<div class="col-lg-3 col-md-3 col-xs-12">嘉奖注明:&nbsp;${formDto.entryFormDto.workedFormDto.awardsRemark
							}</div>
					</div>


					<div class="form-group">
						<div class="col-lg-5 col-md-5 col-xs-12">处分类型:&nbsp;${formDto.entryFormDto.workedFormDto.punished.label
							}</div>
						<div class="col-lg-3 col-md-3 col-xs-12">处分注明:&nbsp;${formDto.entryFormDto.workedFormDto.punishedRemark
							}</div>
					</div>

					<div class="form-group">
						<div class="col-lg-5 col-md-5 col-xs-12">工作期间是否有职位变化:&nbsp;${formDto.entryFormDto.workedFormDto.workChange
							== true ? '是' : '否' }</div>
					</div>
					<c:if
						test="${formDto.entryFormDto.workedFormDto.workChange == true }">
						<div class="form-group">
							<div class="col-lg-5 col-md-5 col-xs-12">变化时间1:&nbsp;${formDto.entryFormDto.workedFormDto.changeDateOne
								}</div>
							<div class="col-lg-5 col-md-5 col-xs-12">变化职位1:&nbsp;${formDto.entryFormDto.workedFormDto.changeJobOne
								}</div>
						</div>
						<div class="form-group">
							<div class="col-lg-5 col-md-5 col-xs-12">变化时间2:&nbsp;${formDto.entryFormDto.workedFormDto.changeDateTwo
								}</div>
							<div class="col-lg-5 col-md-5 col-xs-12">变化职位2:&nbsp;${formDto.entryFormDto.workedFormDto.changeJobTwo
								}</div>
						</div>
						<div class="form-group">
							<div class="col-lg-5 col-md-5 col-xs-12">变化时间3:&nbsp;${formDto.entryFormDto.workedFormDto.changeDateThree
								}</div>
							<div class="col-lg-5 col-md-5 col-xs-12">变化职位3:&nbsp;${formDto.entryFormDto.workedFormDto.changeJobThree
								}</div>
						</div>
					</c:if>
				</c:if>




			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-offset-1 control-label col-lg-3"><a
				class="btn btn-default" href="javascript:history.back(-1)">返回</a> </label>
		</div>




	</form:form>

</body>
</html>
