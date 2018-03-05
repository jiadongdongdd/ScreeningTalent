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
		<li><a href="${contextPath }/enterprise/member/list">职工信息</a></li>
		<li><a class="active">职工详情</a></li>
	</ol>




	<form:form commandName="formDto" class="form-horizontal">

		<div class="">
			<!-- 定义的布局必须添加到class定义row中 //-->

			<div class="row">

				<div class="media">
					<div class="media-body">
						<h3 class="media-heading">${formDto.formOneDto.chName }</h3>


						<div class="form-group">
							<div class="col-lg-2 col-md-3 col-xs-12"
								title="${formDto.formOneDto.mobile
								}">手机:&nbsp;${formDto.formOneDto.mobile
								}</div>

							<div class="col-lg-4 col-md-5 col-xs-12"
								title="${formDto.formOneDto.email
								}">电子邮箱:&nbsp;${formDto.formOneDto.email
								}</div>

							<div class="col-lg-4 col-md-4 col-xs-12"
								title="${formDto.formOneDto.idNumber
								}">身份证号:&nbsp;${formDto.formOneDto.idNumber
								}</div>

						</div>

						<div class="form-group">
							<div class="col-lg-2 col-md-2 col-xs-12"
								title="${formDto.formTwoDto.gender.label
								}">性别:&nbsp;${formDto.formTwoDto.gender.label
								}</div>

							<div class="col-lg-4 col-md-4 col-xs-12"
								title="${formDto.formOneDto.birthday
								}">出生日期:&nbsp;${formDto.formOneDto.birthday
								}</div>

							<div class="col-lg-4 col-md-4 col-xs-12"
								title="${formDto.formTwoDto.school
								}">毕业院校:&nbsp;${formDto.formTwoDto.school
								}</div>
						</div>

						<div class="form-group">
							<div class="col-lg-2 col-md-2 col-xs-12"
								title="${formDto.formTwoDto.nation.label
								}">民族:&nbsp;${formDto.formTwoDto.nation.label
								}</div>

							<c:if test="${formDto.formTwoDto.nation.label == '其他' }">
								<div class="col-lg-4 col-md-4 col-xs-12"
									title="${formDto.formTwoDto.nationOther
									}">民族备注:&nbsp;${formDto.formTwoDto.nationOther
									}</div>
							</c:if>

							<div class="col-lg-4 col-md-4 col-xs-12"
								title="${formDto.formTwoDto.politicalStatus.label
								}">政治面貌:&nbsp;${formDto.formTwoDto.politicalStatus.label
								}</div>

							<div class="col-lg-2 col-md-2 col-xs-12"
								title="${formDto.formTwoDto.origin.label
								}">籍贯:&nbsp;${formDto.formTwoDto.origin.label
								}</div>

						</div>
						<div class="form-group">
							<div class="col-lg-2 col-md-2 col-xs-12"
								title="${formDto.formTwoDto.degree.label
									}">学历:&nbsp;${formDto.formTwoDto.degree.label
								}</div>
						</div>


						<c:if test="${formDto.formTwoDto.origin.label == '外籍' }">
							<div class="form-group">
								<div class="col-lg-2 col-md-2 col-xs-12"
									title="${formDto.formTwoDto.foreignOrigin
									}">外籍备注:&nbsp;${formDto.formTwoDto.foreignOrigin
									}</div>
							</div>
						</c:if>
					</div>
					<div class="media-left">
						<a> <c:if test="${formDto.formTwoDto.gender.label == '男' }">

								<img class="media-object"
									src="${contextPath }/static/img/member/male1.png"
									alt="${formDto.formOneDto.chName }" width="150" height="140">
							</c:if> <c:if test="${formDto.formTwoDto.gender.label == '女' }">

								<img class="media-object"
									src="${contextPath }/static/img/member/female1.png"
									alt="${formDto.formOneDto.chName }" width="150" height="140">
							</c:if> </a>
					</div>
				</div>

			</div>


			<div class="row">
				<c:if test="${not empty formDto.recruitmentFormDto  }">

					<!-- 招聘环节 -->
					<div class="page-header">
						<h1>
							<small>招聘环节</small>
						</h1>
					</div>

					<h4 class="text-primary">电话邀约</h4>

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
							<div class="col-lg-12 col-md-12 col-xs-12">不参加面试原因:&nbsp;${formDto.recruitmentFormDto.notJoinIntentionReason.label
								}</div>

						</div>
						<div class="form-group">

							<div class="col-lg-12 col-md-12 col-xs-12">企业自行填写原因:&nbsp;${formDto.recruitmentFormDto.notJoinIntentionReasonRemark
								}</div>

						</div>
					</c:if>

					<c:if
						test="${formDto.recruitmentFormDto.telIntention.label == '是' }">
						<h4 class="text-primary">面试环节</h4>
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

					<c:if test="${formDto.recruitmentFormDto.agreeEntry.label == '是' }">
						<!-- 不同意入职的原因 -->
						<h4 class="text-primary">入职环节</h4>
						<!-- 是否入职 -->
						<div class="form-group">
							<div class="col-lg-3 col-md-3 col-xs-12">是否入职成功:&nbsp;${formDto.recruitmentFormDto.entrySuccess.label
								}</div>
							<c:if
								test="${formDto.recruitmentFormDto.entrySuccess.label == '是' }">
								<div class="col-lg-3 col-md-3 col-xs-12">入职时间:&nbsp;${formDto.recruitmentFormDto.entryDate
									}</div>
							</c:if>
						</div>
					</c:if>
				</c:if>


				<c:if test="${not empty formDto.turnoverFormDto }">
					<!-- 离职环节 -->
					<div class="page-header">
						<h1>
							<small>离职环节</small>
						</h1>
					</div>

					<h4 class="text-primary">离职原因</h4>

					<!-- 离职原因是否正常 -->
					<div class="form-group">
						<div class="col-lg-3 col-md-3 col-xs-12">离职原因是否正常:&nbsp;${formDto.turnoverFormDto.reasonStatus
							== true ? '是' :'否' }</div>
					</div>
					<c:if test="${formDto.turnoverFormDto.reasonStatus == false  }">

						<!-- 离职原因不正常 (劳动纠纷)-->
						<div class="form-group">
							<div class="col-lg-3 col-md-3 col-xs-12">离职原因:&nbsp;${formDto.turnoverFormDto.turnoverReason.label
								}</div>
							<c:if
								test="${formDto.turnoverFormDto.turnoverReason.label == '劳动纠纷' }">

								<div class="col-lg-5 col-md-5 col-xs-12">
									离职原因中 劳动纠纷的 附件:&nbsp;
									<c:if
										test="${not empty formDto.turnoverFormDto.laborDisputeFileGuid }">
										<a
											href="${contextPath}/public/download/${formDto.turnoverFormDto.laborDisputeFileGuid}">下载附件证件</a>
									</c:if>
								</div>
							</c:if>
						</div>

						<c:if
							test="${formDto.turnoverFormDto.turnoverReason.label == '开除' }">
							<!-- 离职原因不正常 (开除)-->
							<div class="form-group">
								<div class="col-lg-12 col-md-12 col-xs-12">
									开除原因:&nbsp;
									<c:forEach
										items="${formDto.turnoverFormDto.goOutReasonItems
									}"
										var="rItem">
									${rItem.label }
									</c:forEach>
								</div>
							</div>

							<!--开除 附件开始 -->
							<c:if
								test="${fun:contains(formDto.turnoverFormDto.goOutReasonItems,'REVEAL_COMPANY_SECRETS') }">
								<div class="form-group">
									<div class="col-lg-3 col-md-3 col-xs-12">
										泄露公司机密附件证据:&nbsp;
										<c:if
											test="${not empty formDto.turnoverFormDto.revealSecretsFileGuid }">
											<a
												href="${contextPath}/public/download/${formDto.turnoverFormDto.revealSecretsFileGuid}">下载附件证件</a>
										</c:if>
									</div>
								</div>
							</c:if>
							<c:if
								test="${fun:contains(formDto.turnoverFormDto.goOutReasonItems,'BRIBERY') }">
								<div class="form-group">
									<div class="col-lg-3 col-md-3 col-xs-12">
										贪污受贿挪用公款附件证据:&nbsp;
										<c:if
											test="${not empty formDto.turnoverFormDto.briberyFileGuid }">
											<a
												href="${contextPath}/public/download/${formDto.turnoverFormDto.briberyFileGuid}">下载附件证件</a>
										</c:if>
									</div>
								</div>
							</c:if>

							<c:if
								test="${fun:contains(formDto.turnoverFormDto.goOutReasonItems,'RUDE') }">
								<div class="form-group">
									<div class="col-lg-3 col-md-3 col-xs-12">
										言行粗鲁附件证据:&nbsp;
										<c:if
											test="${not empty formDto.turnoverFormDto.rudeFileGuid }">
											<a
												href="${contextPath}/public/download/${formDto.turnoverFormDto.rudeFileGuid}">下载附件证件</a>
										</c:if>
									</div>
								</div>
							</c:if>

							<c:if
								test="${fun:contains(formDto.turnoverFormDto.goOutReasonItems,'DESTROY_IMPORTANT_ASSETS') }">
								<div class="form-group">
									<div class="col-lg-3 col-md-3 col-xs-12">
										破坏公司重要资产附件证据:&nbsp;
										<c:if
											test="${not empty formDto.turnoverFormDto.destroyImportantAssetsFileGuid }">
											<a
												href="${contextPath}/public/download/${formDto.turnoverFormDto.destroyImportantAssetsFileGuid}">下载附件证件</a>
										</c:if>
									</div>
								</div>
							</c:if>
							<c:if
								test="${fun:contains(formDto.turnoverFormDto.goOutReasonItems,'STEALING') }">
								<div class="form-group">
									<div class="col-lg-3 col-md-3 col-xs-12">
										偷盗公司财物附件证据:&nbsp;
										<c:if
											test="${not empty formDto.turnoverFormDto.stealingFileGuid }">
											<a
												href="${contextPath}/public/download/${formDto.turnoverFormDto.stealingFileGuid}">下载附件证件</a>
										</c:if>
									</div>
								</div>
							</c:if>
							<c:if
								test="${fun:contains(formDto.turnoverFormDto.goOutReasonItems,'DEFAMATION') }">
								<div class="form-group">
									<div class="col-lg-3 col-md-3 col-xs-12">
										诋毁公司名誉附件证据:&nbsp;
										<c:if
											test="${not empty formDto.turnoverFormDto.defamationFileGuid }">
											<a
												href="${contextPath}/public/download/${formDto.turnoverFormDto.defamationFileGuid}">下载附件证件</a>
										</c:if>
									</div>
								</div>
							</c:if>
							<c:if
								test="${fun:contains(formDto.turnoverFormDto.goOutReasonItems,'MISUSE_RESOURCES') }">
								<div class="form-group">
									<div class="col-lg-3 col-md-3 col-xs-12">
										滥用公司资源附件证据:&nbsp;
										<c:if
											test="${not empty formDto.turnoverFormDto.misuseResourcesFileGuid }">
											<a
												href="${contextPath}/public/download/${formDto.turnoverFormDto.misuseResourcesFileGuid}">下载附件证件</a>
										</c:if>
									</div>
								</div>
							</c:if>
							<!-- 开除附件结束 -->

							<div class="form-group">
								<div class="col-lg-12 col-md-12 col-xs-12">开除原因（其他）备注:&nbsp;${formDto.turnoverFormDto.turnoverReasonRemark
									}</div>
							</div>
						</c:if>
					</c:if>

					<h4 class="text-primary">离职过程</h4>
					<!-- 离职过程 -->
					<div class="form-group">
						<div class="col-lg-3 col-md-3 col-xs-12">离职时间:&nbsp;${formDto.turnoverFormDto.turnoverDate
							}</div>

						<div class="col-lg-3 col-md-3 col-xs-12">离职时职位:&nbsp;${formDto.turnoverFormDto.turnoverPosition
							}</div>

						<div class="col-lg-3 col-md-3 col-xs-12">离职收入:&nbsp;${formDto.turnoverFormDto.turnoverIncome.label
							}</div>
					</div>

					<div class="form-group">
						<div class="col-lg-3 col-md-3 col-xs-12">离职过程是否正常:&nbsp;${formDto.turnoverFormDto.processStatus
							== true ? '是':'否' }</div>
					</div>

					<c:if test="${formDto.turnoverFormDto.processStatus == false }">
						<div class="form-group">
							<div class="col-lg-3 col-md-3 col-xs-12">离职过程:&nbsp;${formDto.turnoverFormDto.turnoverProcess.label}</div>
						</div>
						<c:if
							test="${formDto.turnoverFormDto.turnoverProcess.label == '带走公司财产信息等' }">
							<div class="form-group">
								<div class="col-lg-12 col-md-12 col-xs-12">
									带走公司财产的选项:&nbsp;
									<c:forEach
										items="${formDto.turnoverFormDto.takeCompanyRsItems
									}"
										var="rItem">
									${rItem.label }
									</c:forEach>
								</div>
							</div>
						</c:if>

						<c:if
							test="${formDto.turnoverFormDto.turnoverProcess.label == '其他' }">
							<div class="form-group">
								<div class="col-lg-3 col-md-3 col-xs-12">注明信息:&nbsp;${formDto.turnoverFormDto.otherProcessRemark}</div>
							</div>
						</c:if>
					</c:if>


					<h4 class="text-primary">离职后状态</h4>
					<!-- 离职后状态 -->
					<div class="form-group">
						<div class="col-lg-3 col-md-3 col-xs-12">离职后状态是否正常:&nbsp;${formDto.turnoverFormDto.processAfterStatus
							== true ? '是':'否'}</div>
					</div>

					<div class="form-group">
						<div class="col-lg-3 col-md-3 col-xs-12">是否再次雇佣:&nbsp;${formDto.turnoverFormDto.hireAgain
							== true ? '是':'否' }</div>
					</div>

					<c:if
						test="${formDto.turnoverFormDto.processAfterStatus
						== false }">
						<div class="form-group">
							<div class="col-lg-3 col-md-3 col-xs-12">是否违反培训协议:&nbsp;${formDto.turnoverFormDto.breachTrainingAgreement
								== true ? '是':'否'}</div>
							<div class="col-lg-3 col-md-3 col-xs-12">
								附件证据:&nbsp;
								<c:if
									test="${not empty formDto.turnoverFormDto.breachTrainingAgreementFileGuid }">
									<a
										href="${contextPath}/public/download/${formDto.turnoverFormDto.breachTrainingAgreementFileGuid}">下载附件证件</a>
								</c:if>
							</div>
						</div>

						<div class="form-group">
							<div class="col-lg-3 col-md-3 col-xs-12">是否在竞业禁止期内:&nbsp;${formDto.turnoverFormDto.outStopPeriod
								== true ? '是':'否'}</div>
							<div class="col-lg-3 col-md-3 col-xs-12">
								附件证据:&nbsp;
								<c:if
									test="${not empty formDto.turnoverFormDto.outStopPeriodFileGuid }">
									<a
										href="${contextPath}/public/download/${formDto.turnoverFormDto.outStopPeriodFileGuid}">下载附件证件</a>
								</c:if>
							</div>
						</div>

						<div class="form-group">
							<div class="col-lg-3 col-md-3 col-xs-12">是否恶意毁坏公司形象:&nbsp;${formDto.turnoverFormDto.illegalDestroyCompanyFace
								== true ? '是':'否'}</div>
							<div class="col-lg-3 col-md-3 col-xs-12">
								附件证据:&nbsp;
								<c:if
									test="${not empty formDto.turnoverFormDto.illegalDestroyCompanyFaceFileGuid }">
									<a
										href="${contextPath}/public/download/${formDto.turnoverFormDto.illegalDestroyCompanyFaceFileGuid}">下载附件证件</a>
								</c:if>
							</div>
						</div>

						<div class="form-group">
							<div class="col-lg-5 col-md-5 col-xs-12">离职后和原单位发生劳动诉讼或法律纠纷:&nbsp;${formDto.turnoverFormDto.legalDisputes
								== true ? '是':'否'}</div>
							<div class="col-lg-3 col-md-3 col-xs-12">
								附件证据:&nbsp;
								<c:if
									test="${not empty formDto.turnoverFormDto.legalDisputesFileGuid }">
									<a
										href="${contextPath}/public/download/${formDto.turnoverFormDto.legalDisputesFileGuid}">下载附件证件</a>
								</c:if>
							</div>
						</div>
						<div class="form-group">
							<div class="col-lg-5 col-md-5 col-xs-12">其他信息注明:&nbsp;${formDto.turnoverFormDto.outOtherRemark
								}</div>
							<div class="col-lg-3 col-md-3 col-xs-12">
								其他信息注明 附件:&nbsp;
								<c:if
									test="${not empty formDto.turnoverFormDto.outOtherFileGuid }">
									<a
										href="${contextPath}/public/download/${formDto.turnoverFormDto.outOtherFileGuid}">下载附件证件</a>
								</c:if>
							</div>
						</div>
					</c:if>
				</c:if>



				<!-- 工作环节 -->
				<c:if test="${not empty formDto.workedFormDto }">
					<div class="page-header">
						<h1>
							<small>工作环节</small>
						</h1>
					</div>

					<h4 class="text-primary">嘉奖</h4>

					<div class="form-group">
						<div class="col-lg-5 col-md-5 col-xs-12">嘉奖类型:&nbsp;${formDto.workedFormDto.awards.label
							}</div>
						<div class="col-lg-3 col-md-3 col-xs-12">嘉奖注明:&nbsp;${formDto.workedFormDto.awardsRemark
							}</div>
					</div>

					<h4 class="text-primary">处分</h4>

					<div class="form-group">
						<div class="col-lg-5 col-md-5 col-xs-12">处分类型:&nbsp;${formDto.workedFormDto.punished.label
							}</div>
						<div class="col-lg-3 col-md-3 col-xs-12">处分注明:&nbsp;${formDto.workedFormDto.punishedRemark
							}</div>
					</div>

					<h4 class="text-primary">升迁</h4>
					<div class="form-group">
						<div class="col-lg-5 col-md-5 col-xs-12">工作期间是否有职位变化:&nbsp;${formDto.workedFormDto.workChange
							== true ? '是' : '否' }</div>
					</div>
					<c:if test="${formDto.workedFormDto.workChange == true }">
						<div class="form-group">
							<div class="col-lg-5 col-md-5 col-xs-12">变化时间1:&nbsp;${formDto.workedFormDto.changeDateOne
								}</div>
							<div class="col-lg-5 col-md-5 col-xs-12">变化职位1:&nbsp;${formDto.workedFormDto.changeJobOne
								}</div>
						</div>
						<div class="form-group">
							<div class="col-lg-5 col-md-5 col-xs-12">变化时间2:&nbsp;${formDto.workedFormDto.changeDateTwo
								}</div>
							<div class="col-lg-5 col-md-5 col-xs-12">变化职位2:&nbsp;${formDto.workedFormDto.changeJobTwo
								}</div>
						</div>
						<div class="form-group">
							<div class="col-lg-5 col-md-5 col-xs-12">变化时间3:&nbsp;${formDto.workedFormDto.changeDateThree
								}</div>
							<div class="col-lg-5 col-md-5 col-xs-12">变化职位3:&nbsp;${formDto.workedFormDto.changeJobThree
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
