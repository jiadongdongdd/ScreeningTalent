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
		<li><a href="${contextPath}/admin/company/NoVerify/false">企业管理</a></li>
		<li class="active">职工信息</li>
	</ol>
	<div class="row">
		<div class="col-lg-12 col-sm-12">
			<div class="pull-right">
				<a href="${contextPath}/enterprise/excel" class="btn btn-success">导出Excel</a>
			</div>
			<form action=""  class="form-inline"
				id="filterForm">

				<div class="form-group">
					<input type="text" id="idNumber" name="idNumber" class="form-control"
						placeholder="身份证号" value="${paginated.idNumber }" />
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
					<th>邮箱</th>
					<th>手机号</th>
					<th>身份证号</th>
					<th>入职时间</th>
					<th>离职时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty paginated.list}">
					<!-- 			<li class="list-group-item">
				<p class="text-muted"></p></li> -->
					<tr>
						<td colspan="9">暂无数据</td>
					</tr>
				</c:if>
				<c:forEach items="${paginated.list}" var="m">
					<tr>
						<%--<td><label><input type="checkbox"> </label>
                    </td>
                    --%>
						<td>${m.chName }</td>
						<td>${m.email }</td>
						<td>${m.mobile }</td>
						<td>${m.idNumber }</td>
						<td>
							<fmt:formatDate value="${m.entryDate }" type="both"/>
						</td>
						<td>
							<fmt:formatDate value="${m.turnoverDate }" type="both"/>
						</td>
						<td>  <a
							href="detail/${m.uuid }">查看</a></td>
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

	<script type="text/javascript">
		$(function() {
			new AdminMember();
		});
	</script>

</body>
</html>
