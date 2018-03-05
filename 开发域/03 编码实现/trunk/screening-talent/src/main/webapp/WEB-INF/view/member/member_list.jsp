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
		<li><a href="${contextPath}/enterprise/index">首页</a></li>
		<li><a href="${contextPath}/enterprise/company/detail">企业管理</a></li>
		<li class="active">职工信息</li>
	</ol>
	<div class="row">
		<div class="col-lg-12 col-sm-12">
			<div class="pull-right" style="height: 70px;">
				<a href="${contextPath}/enterprise/member/form_1/create"
					class="btn btn-success">新增</a> <a
					href="${contextPath}/enterprise/excel" class="btn btn-success">导出Excel</a>
				<a href="form/import_excel" class="btn btn-success">导入Excel</a>
			</div>
			<form action="${contextPath}/enterprise/member/list"
				class="form-inline" name="QueryForm" id="filterForm">
				<input type="hidden" name="direction" id="direction"
					value="${paginated.direction }"> <input type="hidden"
					name="propertyName" id="propertyName"
					value="${paginated.propertyName }">

				<div class="form-group">
					<input value="${paginated.chNameQuery }" type="text"
						id="chNameQuery" name="chNameQuery" class="form-control"
						placeholder="职工姓名" />
				</div>

				<div class="form-group">
					<input type="text" id="idNumberQuery" name="idNumberQuery"
						class="form-control" placeholder="身份证号"
						value="${paginated.idNumberQuery }" />
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
					<th id="chName" name="chName">姓名 &nbsp;<i
						class="glyphicon glyphicon-chevron-up"></i>
					</th>
					<th id="gender" name="gender">性别&nbsp;<i
						class="glyphicon glyphicon-chevron-up"></i>
					</th>
					<th id="idNumber" name="idNumber">身份证号&nbsp;<i
						class="glyphicon glyphicon-chevron-up"></i>
					</th>
					<th id="mobile" name="mobile">手机号&nbsp;<i
						class="glyphicon glyphicon-chevron-up"></i>
					</th>
					<th id="email" name="email">邮箱&nbsp;<i
						class="glyphicon glyphicon-chevron-up"></i>
					</th>
					<th>信息完整度</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty paginated.list}">
					<!-- 			<li class="list-group-item">
				<p class="text-muted"></p></li> -->
					<tr>
						<td colspan="7">暂无数据</td>
					</tr>
				</c:if>
				<c:forEach items="${paginated.list}" var="m">
					<tr>
						<%--<td><label><input type="checkbox"> </label>
                    </td>
                    --%>
						<td>${m.chName }</td>
						<td>${m.gender.label }</td>
						<td>${m.idNumber }</td>
						<td>${m.mobile }</td>
						<td>${m.email }</td>
						<td>
							<p style="color:#666;">
								职工资料完整度：<span id="showSpan"
									style="color:#ff9933;font-size:20px;font-weight:bold;">${m.integrity
									}% </span>
							</p>
							<div class="progress progress-striped"
								style="width:200px;height:15px;">
								<span style="white-space:pre"> </span>
								<div id="showBar" style="width:${m.integrity * 2}px;"
									class="progress-bar progress-bar-info" role="progressbar"
									aria-valuenow="60" aria-valuemin="0" aria-valuemax="100">
									<span style="white-space:pre"> </span><span id="showPercent"
										class="sr-only"> </span> <span style="white-space:pre">
									</span>
								</div>
							</div>
						</td>
						<td><a
							href="${contextPath }/enterprise/member/form_1/${m.uuid }">编辑</a>
							<a href="${contextPath }/enterprise/member/detail/${m.uuid }">查看</a>
							<a href="${contextPath }/enterprise/member/log/${m.uuid }">日志</a>
							<c:if test="${userRole == 'admin' }">
								<a onclick="return confirm('你确定删除职工[${m.chName}]')"
									href="${contextPath }/enterprise/member/delete/${m.uuid }">删除</a>
							</c:if> <c:if test="${userRole != 'admin' }">
								<a onclick="return confirm('删除职工会扣除积分，你确定删除职工[${m.chName}]?')"
									href="${contextPath }/enterprise/member/delete/${m.uuid }">删除</a>
							</c:if>
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
			new AdminMember();
			var href = location.pathname;
			if(href.indexOf("form/import_excel") >= 0) {
				$("#formDto").attr("action","");
			}
			if("${hasError}" == "Y") {
				alert('请使用系统提供的模板导入');
			}
		});
	</script>

	<div id="myModal" class="modal fade ">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<!-- style=" width:840px; "-->

				<!-- dialog body -->
				<div class=" modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 align="center">
						<strong>导入Excel</strong>
					</h4>
				</div>
				<div class=" modal-body">
					<div class="row">
						<div class="col-sm-12">
							<h5 align="center">
								<a id="exportModel" href="#">下载模板</a>
							</h5>
							<form:form commandName="formDto" action="form/import_excel"
								cssClass="form-horizontal" enctype="multipart/form-data"
								name="excelForm">
								<div class="form-group">
									<label for="excelFile" class="col-sm-2 control-label">导入文件<em
										class="text-danger">*</em> </label>

									<div class="col-sm-10">
										<input type="file" id="excelFile" name="excelFile"
											accept=".xls" required="required" />
										<p class="help-block">请导入.xls文件</p>
									</div>
								</div>
								<div class="form-group">
									<label for="" class="col-sm-2 control-label"> </label>
									<div class="col-sm-10">
										<p class="help-block">
											<strong>系统只能导入特定的格式的excel文件，请下载模版并填充数据，然后导入</strong>
										</p>
									</div>
								</div>
							</form:form>
						</div>
					</div>
				</div>
				<!-- dialog buttons -->
				<div class="modal-footer">
					<input class="btn bg-blue-zdy" id="import" type="button" value="导入" />
					<button type="button" class="btn btn-danger font-color-btn"
						data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
