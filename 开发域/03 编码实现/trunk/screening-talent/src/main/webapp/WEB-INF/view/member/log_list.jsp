<%--
 * 
 * @author Shengzhao Li
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../commons/taglib-header.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<title>日志</title>
</head>
<body>

	<ol class="breadcrumb">
		<li><a href="${contextPath}/enterprise/index">首页</a>
		</li>
		<li><a href="${contextPath}/enterprise/company/detail">企业管理</a>
		</li>
		<li><a href="${contextPath}/enterprise/member/list">职工信息</a>
		</li>
		<li class="active">日志</li>
	</ol>
	<div class="row">
		<div class="col-lg-12 col-sm-12">
			<div class="pull-right" style="height: 50px;">
				<a href="../list" class="btn btn-success">返回</a>
			</div>
			<form action="" class="form-inline" id="filterForm">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="操作人"
						name="operator" value="${paginated.operator}" />
				</div>
				<div class="form-group">
					<input type="text" class="form-control" placeholder="操作详情"
						name="operateDetail" value="${paginated.operateDetail}" />
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
					<%--<th>被操作人姓名</th>
					--%>
					<th>操作详情</th>
					<th>操作时间</th>
					<th>操作人</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty paginated.list}">
					<tr>
						<td colspan="3">暂无数据</td>
					</tr>
				</c:if>
				<c:forEach items="${paginated.list}" var="l">
					<tr>
						<%--<td>${l.entityName }</td>
						--%>
						<td>${l.operateDetail }</td>
						<td>${l.createTime }</td>
						<td>${l.operator }</td>
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

</body>
</html>
