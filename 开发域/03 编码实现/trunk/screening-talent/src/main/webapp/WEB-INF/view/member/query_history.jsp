<%--
 * 
 * @author Shengzhao Li
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../commons/taglib-header.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<title>搜索历史</title>
<link rel="stylesheet"
	href="${contextPath}/static/datepicker/datepicker3.css" />
</head>
<body>

	<ol class="breadcrumb">
		<li><a href="${contextPath}/enterprise/index">首页</a></li>
		<li><a href="${contextPath}/enterprise/company/detail">企业管理</a></li>
		<li class="active">搜索历史</li>
	</ol>
	<div class="row">
		<div class="col-lg-12 col-sm-12">
			<div class="pull-right" style="height: 50px;">
			</div>
			<form action="" class="form-inline" id="filterForm">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="职工姓名"
						name="chName" value="${paginated.chName}" />
				</div>
				
				<div class="form-group">
					<input type="text" class="form-control" placeholder="公司名称"
						name="companyName" value="${paginated.companyName}" />
				</div>
				
				<div class="form-group">
					<input type="text" class="form-control datepicker" placeholder="操作时间"
						name="createTime" value="${paginated.createTime}" />
				</div>
				<button type="submit" class="btn btn-default">
					<em class="glyphicon glyphicon-search"></em>
				</button>

				&nbsp;<span class="text-info">共${paginated.totalSize}条记录</span>
			</form>
		</div>
	</div>

	<div class="table-responsive">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>职工姓名</th>
					<th>所属公司</th>
					<th>操作时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty paginated.list}">
					<tr>
						<td colspan="4">暂无数据</td>
					</tr>
				</c:if>
				<c:forEach items="${paginated.list}" var="l">
					<tr>
						<td>${l.entityName }</td>
						<td>${l.entity.companyName }</td>
						<td>${l.createTime }</td>
						<td><a href="wide_detail_${l.entityUuid }">查看</a></td>
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
	
	<script type="text/javascript">
		$(function() {
			new QueryHistory();
		});
	</script>

</body>
</html>
