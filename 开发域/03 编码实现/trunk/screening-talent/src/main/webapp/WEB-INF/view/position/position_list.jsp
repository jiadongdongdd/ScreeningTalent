<%--
 * 
 * @author Shengzhao Li
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../commons/taglib-header.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<title>职位列表</title>
</head>
<body>

	<ol class="breadcrumb">
		<li><a href="${contextPath}/admin/index">首页</a>
		</li>
		<li><a href="">内容管理</a>
		</li>
		<li class="active">职位管理</li>
	</ol>
	<div class="row">
		<div class="col-lg-12 col-sm-12">
			<div class="pull-right">
				<a href="${contextPath}/admin/position/form/create"
					class="btn btn-success">新增</a>
			</div>
			<form action="" class="form-inline" id="filterForm">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="职位名称"
						name="positionName" value="${paginated.positionName}" />
				</div>
				<div class="form-group">
					<input type="text" class="form-control" placeholder="职位级别"
						name="level" value="${paginated.level}" />
				</div>
				<div class="form-group">
					<input type="text" class="form-control" placeholder="行业"
						name="industry" value="${paginated.industry}" />
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
					<th>职位名称</th>
					<th>职位级别</th>
					<th>行业</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty paginated.list}">
					<tr>
						<td colspan="4">暂无数据</td>
					</tr>
				</c:if>
				<c:forEach items="${paginated.list}" var="p">
					<tr>
						<td>${p.positionName }</td>
						<td>${p.level }</td>
						<td>${p.industry }</td>
						<td><a href="form/edit_${p.uuid }">编辑</a> <a
							onclick="return confirm('删除职位，确定操作？')" href="delete/${p.uuid }">删除</a>
						</td>
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
