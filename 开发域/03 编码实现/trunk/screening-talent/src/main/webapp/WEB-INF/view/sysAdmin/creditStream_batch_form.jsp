
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../commons/taglib-header.jsp" %>
<html>
<head>
    <title>增加积分</title>

    <style>
        .list-group li:hover {
            background-color: #f9f9f9;
        }
    </style>

</head>
<body>

<ol class="breadcrumb">
    <li><a href="${contextPath}/admin/index">首页</a></li>
    <li><a href="#">积分管理</a></li>
    <li ><a href="${contextPath}/admin/credit/handwork/list">积分调整</a></li>
     <li class="active">增减积分</li>
</ol>

<h2 class="page-header">增减积分</h2>

 <form:form commandName="formDto" cssClass="form-horizontal" method="post" action="${contextPath}/admin/credit/handwork/credit/batch/create/form">
            <div class="form-group">
                <label for=creditDo class="col-sm-2 control-label">积分<em class="text-danger">*</em></label>
	
				<form:hidden path="companyUuids"/>
                <div class="col-sm-10">
                    <form:input path="creditDo" cssClass="form-control"
                                placeholder="积分"
                                required="required" maxlength="255"/>
                    <form:errors path="creditDo" cssClass="label label-warning"/>
                    <p class="help-block">必填值</p>
                </div>
            </div>

		<div class="form-group">
			<label for="remarks" class="col-sm-2 control-label">备注<em
				class="text-danger">*</em> </label>

			<div class="col-sm-10">
				<form:input path="remarks" cssClass="form-control"
					placeholder="备注" required="required" maxlength="255" />
				<form:errors path="remarks" cssClass="label label-warning" />
				<p class="help-block">必填值</p>
			</div>
		</div>

            
            <div class="form-group">
                <div class="col-sm-2"></div>
                <div class="col-sm-10">
                    <button type="submit" class="btn btn-primary">保存</button>
                    <a href="${contextPath}/admin/credit/handwork/list">返回</a>&nbsp;
                    <span><em class="text-danger">*</em> 代表必填值</span>
                </div>
            </div>
        </form:form>
</body>
</html>
