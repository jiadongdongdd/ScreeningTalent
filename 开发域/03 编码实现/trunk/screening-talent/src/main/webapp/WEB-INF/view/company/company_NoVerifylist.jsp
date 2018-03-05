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
	<c:if
		test="${fun:contains(SPRING_SECURITY_CONTEXT.authentication.authorities,'[ROLE_ADMIN') }">
		<ol class="breadcrumb">
			<li><a id="indexPage" href="${contextPath}/admin/index">首页</a></li>
			<li><a id="companyMan"
				href="${contextPath}/admin/company/NoVerify/false">企业管理</a>
			</li>
			<li class="active">待审核企业</li>
		</ol>
	</c:if>

	<c:if
		test="${fun:contains(SPRING_SECURITY_CONTEXT.authentication.authorities,'[ROLE_ENTERPRISEADMIN') }">
		<ol class="breadcrumb">
			<li><a id="indexPage"
				href="${contextPath}/enterpriseAdmin/index">首页</a></li>
			<li><a id="companyMan"
				href="${contextPath}/enterpriseAdmin/company/NoVerify/false">企业管理</a>
			</li>
			<li class="active">待审核企业</li>
		</ol>
	</c:if>
	<div class="row">
		<div class="col-lg-12 col-sm-12">
			<div class="pull-right" style="height: 50px;">

				<%--<c:if test="${user.privileges eq '[ADMIN]' }">
					<a id="createCompany" href="${contextPath}/admin/company/create/verify"
						class="btn btn-success">新增企业</a>
				</c:if>

				<c:if test="${user.privileges eq '[ENTERPRISEADMIN]' }">
					<a id="createCompany"
						href="${contextPath}/enterpriseAdmin/company/create"
						class="btn btn-success">新增企业</a>
				</c:if>

			--%>
			</div>
			<form action="${contextPath}/admin/company/NoVerify/false"
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
					<th>企业名称</th>
					<th>企业邮箱</th>
					<th>联系人</th>
					<th>联系人电话</th>
					<th>操作</th>
				</tr>
			</thead>


			<tbody>

				<c:if test="${empty paginated.list}">
					<!-- 			<li class="list-group-item">
				<p class="text-muted"></p></li> -->
					<tr>
						<td colspan="5">暂无数据</td>
					</tr>
				</c:if>

				<c:forEach items="${paginated.list}" var="comp">
					<tr>
						<td>${comp.companyName}</td>
						<td>${comp.companyEmail}</td>
						<td>${comp.contacts}</td>
						<td>${comp.contactsPhone}</td>
						<td><a href="verify/${comp.guid}">查看</a></td>

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

	<script type="text/javascript">
		$(function() {
			var href = location.pathname;
			if (href.indexOf("enterpriseAdmin") > 0) {
				$('#createCompany').attr('href',
						'${contextPath}/enterpriseAdmin/company/create/verify');
				$('#indexPage').attr('href',
						'${contextPath}/enterpriseAdmin/index');
				$('#companyMan')
						.attr('href',
								'${contextPath}/enterpriseAdmin/company/NoVerify/false');
			}
		});
	</script>


</body>
</html>
