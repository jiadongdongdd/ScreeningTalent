
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../commons/taglib-header.jsp"%>

<html>
<head>
<title>手工添加积分</title>
</head>
<body>
	<ol class="breadcrumb">
		<li><a href="${contextPath}/admin/index">首页</a>
		</li>
		<li><a href="${contextPath }/admin/credit/memberlevel/list">积分管理</a>
		</li>
		<li class="active">积分调整</li>
	</ol>
	<div class="row">
		<div class="col-lg-12 col-sm-12">
			<div class="pull-right">
				<a href="javascript:void(0)" id="batchBtn"
					class="btn btn-success">批量增减积分</a>
			</div>
			<form action="" class="form-inline" id="filterForm">
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
	<br />
	<form action="${contextPath}/admin/credit/handwork/credit/batch/create"
		method="post" name="batchForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
		<div class="table-responsive">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>选择</th>
						<th>用户</th>
						<th>企业名称</th>
						<th>企业邮箱</th>
						<th>联系人</th>
						<th>联系人电话</th>
						<th>积分</th>
						<th>操作</th>
					</tr>
				</thead>

				<tbody>
					<c:if test="${empty paginated.list}">
						<!-- 			<li class="list-group-item">
				<p class="text-muted"></p></li> -->
						<tr>
							<td colspan="8">暂无数据</td>
						</tr>
					</c:if>
					<c:forEach items="${paginated.list}" var="comp">

						<tr>
							<td><input type="checkbox" name="companyCheckIds"
								value="${comp.guid}"></td>
							<td>${comp.username}</td>
							<td>${comp.companyName}</td>
							<td>${comp.companyEmail}</td>
							<td>${comp.contacts}</td>
							<td>${comp.contactsPhone}</td>
							<td>${comp.creditNumber}</td>
							<td><a href="company/stream/${comp.guid }">积分流水</a>||<a
								href="credit/create/${comp.guid }">增减积分</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

	</form>
	<%--paginated--%>
	<dis:table list="${paginated}" id="d" form="filterForm"
		class="table hidden table-striped table-hover">
		<dis:column property="guid" />
	</dis:table>
	
	<script type="text/javascript">
		$(function() {
			new CompanyCredit();
		});
	</script>

</body>
</html>
