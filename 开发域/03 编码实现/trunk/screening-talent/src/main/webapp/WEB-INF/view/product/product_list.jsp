<%--
 * 
 * @author Shengzhao Li
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../commons/taglib-header.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<title>商品</title>
</head>
<body>

	<ol class="breadcrumb">
		<li><a href="${contextPath}/admin/index">首页</a>
		</li>
		<li><a href="#">系统设置</a>
		</li>
		<li class="active">商品</li>
	</ol>
	<div class="row">
		<div class="col-lg-12 col-sm-12">
			<div class="pull-right">
				<a href="${contextPath}/admin/product/form/create"
					class="btn btn-success">新增</a>
			</div>
			<form action="" class="form-inline" id="filterForm">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="商品名称"
						name="productName" value="${paginated.productName}" />
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
					<th>商品名</th>
					<th>商品价格</th>
					<th>积分数量</th>
					<th>简单描述</th>
					<th>详细描述</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty paginated.list}">
					<tr>
						<td colspan="6">暂无数据</td>
					</tr>
				</c:if>
				<c:forEach items="${paginated.list}" var="p">
					<tr>
						<td>${p.productName }</td>
						<td>${p.productPrice }</td>
						<td>${p.credit }</td>
						<td>${p.productBody }</td>
						<td>${p.productDetail }</td>
						<td><a href="edit/${p.uuid }">编辑</a> <a
							onclick="return confirm('确定商品吗？')" href="delete/${p.uuid }">删除</a>
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
