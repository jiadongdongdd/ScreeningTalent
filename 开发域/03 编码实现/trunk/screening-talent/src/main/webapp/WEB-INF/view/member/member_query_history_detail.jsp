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
		<li><a href="${contextPath }/enterprise/member/query_list">搜索历史</a>
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

				<div class="media">
					<div class="media-body">
						<h3 class="media-heading">${formDto.basicFormDto.formOneDto.chName
							}</h3>


						<div class="form-group">
							<div class="col-lg-3 col-md-3 col-xs-12"
								title="${formDto.basicFormDto.formOneDto.mobile
								}">手机:&nbsp;${formDto.basicFormDto.formOneDto.mobile
								}</div>

							<div class="col-lg-5 col-md-5 col-xs-12"
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

							<div class="col-lg-2 col-md-2 col-xs-12"
								title="${formDto.basicFormDto.formTwoDto.degree.label
								}">学历:&nbsp;${formDto.basicFormDto.formTwoDto.degree.label
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
				<div class="page-header">
					<h1>
						<small>电话邀约</small>
					</h1>
				</div>
				<div class="table-responsive">
					<table class="table table-striped">
						<caption>
							电话邀约列表&nbsp;<span class="text-info">共${formDto.telPaginated.totalSize}条记录</span>
						</caption>
						<thead>
							<tr>
								<th>公司名称</th>
								<th>面试职位</th>
								<th>邀约时间</th>
								<th>面试地点</th>
								<th>达成面试意向</th>
								<th>原因</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${formDto.telPaginated.list }" var="t">
								<tr>
									<td>${t.companyName }</td>
									<td>${t.telIntentionPosition }</td>
									<td>${t.telInvitationTimeText }</td>
									<td>${t.companyName }</td>
									<td>${t.telIntention.label}</td>
									<td>${t.telIntention.label == '否' ?
										t.notJoinIntentionReason.label : '' }</td>
									<td><a href="tel_detail_${t.uuid }">查看</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

				</div>

				<br> <br>

				<div class="page-header">
					<h1>
						<small>面试记录</small>
					</h1>
				</div>

				<div class="table-responsive">
					<table class="table table-striped">
						<caption>
							面试记录列表&nbsp;<span class="text-info">共${formDto.interviewPaginated.totalSize}条记录</span>
						</caption>
						<thead>
							<tr>
								<th>公司名称</th>
								<th>面试职位</th>
								<th>面试时间</th>
								<th>面试地点</th>
								<th>面试结果</th>
								<th>原因</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${formDto.interviewPaginated.list }" var="i">
								<tr>
									<td>${i.companyName }</td>
									<td>${i.telIntentionPosition }</td>
									<td>${i.invitationTimeText }</td>
									<td>${i.companyName }</td>
									<td>${i.agreeEntry.label == '是' ? '同意入职' : '不同意入职'}</td>
									<c:if test="${i.agreeEntry.label == '是' }">
										<td></td>
									</c:if>
									<c:if test="${i.agreeEntry.label == '否' }">
										<td>${i.personalOrCompany == true ? '个人' : '公司' }</td>
									</c:if>
									<td><a href="interview_detail_${i.uuid }">查看</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

				</div>

				<br> <br>
				<div class="page-header">
					<h1>
						<small>入职记录</small>
					</h1>
				</div>

				<div class="table-responsive">
					<table class="table table-striped">
						<caption>
							入职记录列表&nbsp;<span class="text-info">共${formDto.entryPaginated.totalSize}条记录</span>
						</caption>
						<thead>
							<tr>
								<th>公司名称</th>
								<th>入职时间</th>
								<th>离职时间</th>
								<th>离职时工作职位</th>
								<th>离职时薪酬范围</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${formDto.entryPaginated.list }" var="e">
								<tr>
									<td>${e.companyName }</td>
									<td>${e.entryDateTime }</td>
									<td>${e.turnover.turnoverDateTime }</td>
									<td>${e.turnover.turnoverPosition}</td>
									<td>${e.turnover.turnoverIncome.label }</td>
									<td><a href="entry_detail_${e.uuid }">查看</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

				</div>



			</div>
		</div>


		<div class="form-group">
			<label class="col-sm-offset-1 control-label col-lg-3"><a
				class="btn btn-default" href="javascript:history.back(-1)">返回</a> </label>
		</div>



	</form:form>

</body>
</html>
