
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../commons/taglib-header.jsp"%>

<html>
<head>
<title>积分查询</title>
<link rel="stylesheet"
	href="${contextPath}/static/datepicker/datepicker3.css" />
</head>
<body>
	<ol class="breadcrumb">
		<li><a href="${contextPath}/admin/index">首页</a></li>
		<li><a href="#">积分管理</a></li>
		<li class="active">购买记录</li>
	</ol>

	<form action="" class="form-inline" id="filterForm">
		<div class="form-group">
			<input type="text" class="form-control" placeholder="公司名称"
				name="companyName" value="${paginated.companyName}" />
		</div>
		<div class="form-group">
			<input type="text" class="form-control datepicker"
				placeholder="交易起始时间" name="start" value="${paginated.start}" />
		</div>
		<div class="form-group">
			<input type="text" class="form-control datepicker"
				placeholder="交易截止时间" name="end" value="${paginated.end}" />
		</div>
		<button type="submit" class="btn btn-default">
			<em class="glyphicon glyphicon-search"></em>
		</button>
		&nbsp;<span class="text-info">共${paginated.totalSize}条记录</span>
	</form>
	<br />

	<div class="table-responsive">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>公司名称</th>
					<th>交易日期</th>
					<th>充值金额</th>
					<th>充值前积分</th>
					<th>充值后积分</th>
					<th>原因</th>
				</tr>
			</thead>

			<tbody>

				<c:if test="${empty paginated.list}">
					<!-- 			<li class="list-group-item">
				<p class="text-muted"></p></li> -->
					<tr>
						<td colspan="6">暂无数据</td>
					</tr>
				</c:if>

				<c:forEach items="${paginated.list}" var="o">
					<tr>

						<td>${o.customerName}</td>
						<td>${o.orderDate}</td>
						<td>${o.price}</td>
						<td>${o.beforeCredit}</td>
						<td>${o.afterCredit}</td>
						<td>${o.detail}</td>

					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>

	<%--paginated--%>
	<dis:table list="${paginated}" id="d" form="filterForm"
		class="table hidden table-striped table-hover">
		<dis:column property="uuid" />
	</dis:table>

	<script src="${contextPath}/static/datepicker/bootstrap-datepicker.js"></script>
	<script
		src="${contextPath}/static/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>

	<script>
		$(function() {
			new CreditQuery();
		});
	</script>

</body>
</html>
