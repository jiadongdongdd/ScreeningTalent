<%--
 * 
 * @author Shengzhao Li
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../commons/taglib-header.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<title>商户设置</title>
</head>
<body>

	<ol class="breadcrumb">
		<li><a href="${contextPath}/admin/index">首页</a></li>
		<li><a href="#">系统设置</a></li>
		<li class="active">商户设置</li>
	</ol>
	<div class="row">
		<div class="col-lg-12 col-sm-12">
			<c:if test="${paginated.totalSize == 0 }">
				<div class="pull-right">
					<a href="${contextPath}/admin/wx/merchant/form/create"
						class="btn btn-success">新增</a>
				</div>
			</c:if>
			<c:if test="${paginated.totalSize > 0 }">
				<div class="pull-right" style="height: 50px;"></div>
			</c:if>
			<form action="" class="form-inline" id="filterForm">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="公众号ID"
						name="appId" value="${paginated.appId}" />
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
					<th>公众账号ID</th>
					<th>商户号</th>
					<th>商户KEY</th>
					<th>机器IP</th>
					<th>端口号</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty paginated.list}">
					<tr>
						<td colspan="6">暂无数据</td>
					</tr>
				</c:if>
				<c:forEach items="${paginated.list}" var="m">
					<tr>
						<td>${m.appId }</td>
						<td>${m.mchId }</td>
						<td>${m.mchKey }</td>
						<td>${m.ip }</td>
						<td>${m.port}</td>
						<td><a href="edit/${m.uuid }">编辑</a> <a onclick="return confirm('确定删除公众号信息吗？')"
							href="delete/${m.uuid }">删除</a></td>
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
