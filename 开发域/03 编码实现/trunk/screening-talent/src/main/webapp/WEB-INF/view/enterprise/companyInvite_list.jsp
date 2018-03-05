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

<html>
<head>
<title>企业列表</title>
</head>
<body>
	<ol class="breadcrumb">
		<li><a href="${contextPath}/enterprise/index">首页</a></li>
		<li><a href="${contextPath}/enterprise/company/detail">企业管理</a></li>
		<li class="active">邀请记录</li>
	</ol>
	<div class="row">
		<div class="col-lg-12 col-sm-12">
			<div class="pull-right" style="height:50px;"></div>
			<form action="${contextPath}/admin/enterprise/list"
				class="form-inline" id="filterForm">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="企业名称"
						name="companyName" value="${paginated.companyName}" />
				</div>
				<button type="submit" class="btn btn-default">
					<em class="glyphicon glyphicon-search"></em>
				</button>
				&nbsp;<span class="text-info">共${paginated.totalSize}个企业</span>
			</form>
		</div>
	</div>
	<div class="table-responsive">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>用户名</th>
					<th>被邀请企业名称</th>
					<th>企业邮箱</th>
					<th>邀请状态</th>
					<th>操作</th>
				</tr>
			</thead>


			<tbody>

				<c:if test="${empty paginated.list}">
					<tr>
						<td colspan="5">暂无数据</td>
					</tr>
				</c:if>

				<c:forEach items="${paginated.list}" var="invite">
					<tr>
						<td>${invite.username}</td>
						<td>${invite.companyName}</td>
						<td>${invite.companyEmail}</td>
						<c:if test="${invite.inviteState == false }">
							<td>未接受</td>
						</c:if>
						<c:if test="${invite.inviteState == true }">
							<td>已接受</td>
						</c:if>
						<td><a
							href="${contextPath }/enterprise/companyInvite/detail/${invite.guid}">明细</a>
						</td>
					</tr>
				</c:forEach>



			</tbody>
		</table>
	</div>
	<%--paginated--%>
	<dis:table list="${paginated}" id="d" form="filterForm"
		class="table hidden table-striped table-hover">
		<dis:column property="guid" />
	</dis:table>

</body>
</html>
