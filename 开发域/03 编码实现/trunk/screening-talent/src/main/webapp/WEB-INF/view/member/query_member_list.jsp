<%--
 * 
 * @author Shengzhao Li
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../commons/taglib-header.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<title>全站搜索</title>
</head>
<body>

	<ol class="breadcrumb">
		<li><a href="${contextPath}/enterprise/index">首页</a>
		</li>
		<li><a href="${contextPath}/enterprise/company/detail">企业管理</a>
		</li>
		<li class="active">全站搜索</li>
	</ol>
	<div class="row">
		<div class="col-lg-12 col-sm-12">
			<div class="pull-right" style="height: 70px;">
				<%--<a href="${contextPath}/enterprise/excel" class="btn btn-success">导出Excel</a>
			--%>
			</div>
			<form action="" name="QueryForm" id="filterForm" class="form-inline">
				<div class="form-group">
					<input class="form-control" id="chNameQuery" name="chNameQuery"
						placeholder="职工姓名" value="${paginated.chNameQuery }" />
				</div>
				<div class="form-group">
					<input class="form-control" id="idNumberQuery" name="idNumberQuery"
						placeholder="身份证号" value="${paginated.idNumberQuery }" />
				</div>
				<div class="form-group">
					<input class="form-control" id="mobileQuery" name="mobileQuery"
						placeholder="手机号" value="${paginated.mobileQuery }" />
				</div>
				<div class="form-group">
					<input class="form-control" id="emailQuery" name="emailQuery"
						placeholder="邮箱" value="${paginated.emailQuery }" />
				</div>
				<c:if test="${enough == false }">
					<button type="button" id="btnSearch" class="btn btn-default"
						disabled="disabled">
						<em class="glyphicon glyphicon-search"></em>
					</button>
				</c:if>
				<c:if test="${enough == true }">
					<button type="button" id="btnSearch" class="btn btn-default">
						<em class="glyphicon glyphicon-search"></em>
					</button>
				</c:if>
				&nbsp;<span class="text-info">共${paginated.totalSize}条记录</span> <a
					href="../company/creditbuy" class="label label-warning ${enough == false?'':'hidden' }">积分不足，购买积分</a>
				<span id="chNameError" class="label label-warning hidden">姓名为必填项</span>
				<span id="optionError" class="label label-warning hidden">身份证号、手机号、邮箱三者至少填写一项</span>
				<p class="help-block">姓名为必填项，身份证号、手机号、邮箱三者至少填写一项</p>

			</form>
		</div>
	</div>
	<div class="table-responsive">
		<table class="table table-striped">
			<thead>
				<tr>
					<th id="chName" name="chName">姓名</th>
					<th id="gender" name="gender">性别</th>
					<th id="birthday" name="birthday">出生日期</th>
					<th id="workPosition" name="workPosition">工作职位</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty paginated.list}">
					<!-- 			<li class="list-group-item">
				<p class="text-muted"></p></li> -->
					<tr>
						<td colspan="5">暂无数据</td>
					</tr>
				</c:if>
				<c:forEach items="${paginated.list}" var="m">
					<tr>
						<%--<td><label><input type="checkbox"> </label>
                    </td>
                    --%>
						<td>${m.chName }</td>
						<td>${m.gender.label }</td>
						<td>${m.birthday }</td>
						<td>${m.workPosition }</td>
						<td><a href="wide_detail_${m.uuid }">查看</a>
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

	<script type="text/javascript">
		$(function() {
			new QueryMember("${userRole}");
		});
	</script>

</body>
</html>
