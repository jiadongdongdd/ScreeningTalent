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
<title>admin管理员列表</title>
</head>
<body>
	<ol class="breadcrumb">
		<li><a href="${contextPath}/admin/index">首页</a></li>
		<li><a href="#">系统设置</a></li>
		<li class="active">admin用户</li>
	</ol>
	<div class="row">
		<div class="col-lg-12 col-sm-12">
			<div class="pull-right">
				<a href="${contextPath}/admin/add" class="btn btn-success">新增管理员</a>
			</div>
			<form action="${contextPath}/admin/list" class="form-inline"
				id="filterForm">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="用户名"
						name="username" value="${paginated.username}" />
				</div>
				<button type="submit" class="btn btn-default">
					<em class="glyphicon glyphicon-search"></em>
				</button>
				&nbsp;<span class="text-info">共${paginated.totalSize}个用户</span>
			</form>
		</div>
	</div>
	<div class="table-responsive">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>用户名</th>
					<th>权限</th>
					<th>创建时间</th>
					<th>操作</th>
				</tr>
			</thead>


			<tbody>

				<c:if test="${empty paginated.list}">
					<!-- 			<li class="list-group-item">
				<p class="text-muted"></p></li> -->
					<tr>
						<td colspan="4">暂无数据</td>
					</tr>
				</c:if>

				<c:forEach items="${paginated.list}" var="comp">
					<tr>
						<td>${comp.username}</td>
						<td>${comp.privileges}</td>
						<td>${comp.createTime}</td>
						<td><c:if test="${comp.disabled eq false }">
								<a href="${contextPath}/admin/logOff/${comp.username}"
									onclick="return confirm('此账户将不再能登录,你确定注销吗?')">注销</a>
							</c:if> <c:if test="${comp.disabled eq true }">
								<a href="${contextPath}/admin/logOpen/${comp.username}"
									onclick="return confirm('还原后将能正常使用此账户,你确定还原吗?')">还原</a>
							</c:if></td>
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
