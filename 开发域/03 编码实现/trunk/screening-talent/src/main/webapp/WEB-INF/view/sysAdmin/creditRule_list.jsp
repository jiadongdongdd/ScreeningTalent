
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../commons/taglib-header.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<title>积分规则</title>
</head>
<body>

	<ol class="breadcrumb">
		<li><a href="${contextPath}/admin/index">首页</a>
		</li>
		<li><a href="${contextPath }/admin/credit/memberlevel/list">积分管理</a>
		</li>
		<li class="active">积分规则</li>
	</ol>
	<div class="row">
		<div class="col-lg-12 col-sm-12">
			<div class="pull-right">
				<a href="form/create" class="btn btn-success">新增</a> <a
					href="javascript:void(0)" id="deleteBtn"
					class="btn label-danger">删除</a>
			</div>
			<%-- <form:form action="" class="form-inline" commandName="list" id="filterForm">
            <div class="form-group col-lg-4">
            <form:input path="uuid" cssClass="form-control"
						placeholder="请输入您要搜索的内容" />
              <button type="submit" class="btn btn-default">
					<em class="glyphicon glyphicon-search"></em>
				</button>
            </div>
        </form:form> --%>
		</div>
	</div>
	<form action="${contextPath}/admin/credit/creditrule/delete"
		method="post" name="deleteForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
		<div class="table-responsive">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>选择</th>
						<th>规则项</th>
						<th>分值</th>
						<th>操作项</th>
						<th>备注</th>
						<th>操作</th>
					</tr>
				</thead>


				<tbody>

					<c:if test="${empty list}">
						<!-- 			<li class="list-group-item">
				<p class="text-muted"></p></li> -->
						<tr>
							<td colspan="6">暂无数据</td>
						</tr>
					</c:if>
					<c:forEach items="${list }" var="a">
						<tr>
							<td><label><input type="checkbox" name="deleteIds"
									value="${a.uuid }"> </label>
							</td>
							<td>${a.ruleName }</td>
							<td>${a. creditNumber}</td>
							<td>${a.action}</td>
							<td>${a. remarks}</td>
							<td><a href="form/edit/${a.uuid}">编辑</a></td>

						</tr>
					</c:forEach>



				</tbody>
			</table>
		</div>

	</form>
	
	<script type="text/javascript">
		$(function() {
			new CreditRule();
		});
	</script>

</body>
</html>
