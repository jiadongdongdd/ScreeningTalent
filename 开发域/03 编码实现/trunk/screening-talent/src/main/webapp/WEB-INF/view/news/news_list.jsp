<%--
 * 
 * @author Shengzhao Li
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../commons/taglib-header.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<title>职工信息</title>
</head>
<body>

	<ol class="breadcrumb">
		<li><a href="${contextPath}/admin/index">首页</a></li>
		<li><a href="">内容管理</a></li>
		<li class="active">新闻管理</li>
	</ol>
	<div class="row">
		<div class="col-lg-12 col-sm-12">
			<div class="pull-right">
				<a href="${contextPath}/admin/news/create" class="btn btn-success">新增</a>
			</div>
			
			<form action="" class="form-inline" id="filterForm">
				<div class="form-group">
					<input type="text" id="title" name="title" class="form-control" placeholder="新闻标题" value="${paginated.title }"/>
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
					<th>新闻标题</th>
					<th>类别</th>
					<th>日期</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty paginated.list}">
					<tr>
						<td colspan="4">暂无数据</td>
					</tr>
				</c:if>
				<c:forEach items="${paginated.list}" var="n">
					<tr>
						<td>${n.title }</td>
						<td>${n.category.label }</td>
						<td>${n.dateTime }</td>
						<td>
						<a href="edit/${n.uuid }">编辑</a>
						<a onclick="return confirm('删除新闻，确定操作？')"
							href="delete/${n.uuid }">删除</a>
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
