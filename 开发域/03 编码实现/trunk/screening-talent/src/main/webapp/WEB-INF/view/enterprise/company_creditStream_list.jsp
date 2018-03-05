
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../commons/taglib-header.jsp"%>

<html>
<head>
<title>积分流水</title>
<link rel="stylesheet" href="${contextPath}/static/datepicker/datepicker3.css"/>
</head>
<body>
	<ol class="breadcrumb">
		<li><a href="${contextPath}/enterprise/index">首页</a></li>
		<li><a href="#">积分管理</a></li>
		<li class="active">积分流水</li>
	</ol>

	<form action="" class="form-inline" id="filterForm">
		<div class="form-group">
			<input type="text" class="form-control" placeholder="操作人"
				name="operator" value="${paginated.operator}" />
		</div>
		<div class="form-group">
			<input type="text" class="form-control datepicker" placeholder="操作起始时间"
				name="start" value="${paginated.start}" />
		</div>
		<div class="form-group">
			<input type="text" class="form-control datepicker" placeholder="操作截止时间"
				name="end" value="${paginated.end}" />
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
					<th>用户名</th>
					<th>企业名称</th>
					<th>积分</th>
					<th>备注</th>
					<th>操作人</th>
					<th>操作时间</th>
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

				<c:forEach items="${paginated.list}" var="c">
					<tr>

						<td>${c.companyUsername}</td>
						<td>${c.companyName}</td>
						<td>${c.creditDo}</td>
						<td>${c.remarks}</td>
						<td>${c.operator}</td>
						<td>${c.createTime}</td>

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
			new CompanyCreditStream();
		});
	</script>

</body>
</html>
