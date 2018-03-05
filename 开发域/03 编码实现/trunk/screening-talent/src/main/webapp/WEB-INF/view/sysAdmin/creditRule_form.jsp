
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../commons/taglib-header.jsp" %>
<html>
<head>
    <title>增加积分规则</title>

    <style>
        .list-group li:hover {
            background-color: #f9f9f9;
        }
    </style>

</head>
<body>

<ol class="breadcrumb">
    <li><a href="${contextPath}/admin/index">首页</a></li>
    <li><a href="${contextPath }/admin/credit/memberlevel/list">积分管理</a></li>
    <li><a href="${contextPath }/admin/credit/creditrule/list">积分规则</a></li>
    <li class="active">新增</li>
</ol>

<h2 class="page-header">增加积分规则</h2>

 <form:form commandName="formDto" cssClass="form-horizontal" method="post">
            <div class="form-group">
                <label for="ruleName" class="col-sm-2 control-label">规则项<em class="text-danger">*</em></label>
	
				<form:hidden path="uuid"/>
                <div class="col-sm-10">
                    <form:input path="ruleName" cssClass="form-control"
                                placeholder="规则项"
                                required="required" maxlength="255"/>
                    <form:errors path="ruleName" cssClass="label label-warning"/>
                    <p class="help-block">必填值</p>
                </div>
            </div>

		<div class="form-group">
			<label for="creditNumber" class="col-sm-2 control-label">分值<em
				class="text-danger">*</em> </label>

			<div class="col-sm-10">
				<form:input path="creditNumber" cssClass="form-control"
					placeholder="分值" required="required" maxlength="255" />
				<form:errors path="creditNumber" cssClass="label label-warning" />
				<p class="help-block">必填值</p>
			</div>
		</div>
	
	
            <div class="form-group">
                <label class="col-sm-2 control-label">操作项<em class="text-danger">*</em></label>

                <div class="col-sm-10">
                    <c:forEach items="${formDto.allActions}" var="g">
                        <label class="radio-inline">
                            <input type="radio"  name="action" <c:if test="${g eq formDto.action }">checked</c:if> value="${g}"/> ${g}
                        </label>
                    </c:forEach><br/>
	
                    <form:errors path="action" cssClass="label label-warning"/>
                    <p class="help-block">积分规则的操作项, 必选！</p>
                </div>
            </div>
            
                      <div class="form-group">
                <label for="remarks" class="col-sm-2 control-label">备注<em
                        class="text-danger">*</em></label>

                <div class="col-sm-10">
                    <form:input path="remarks" cssClass="form-control"
                                placeholder="备注"
                                 maxlength="255"/>
                    <form:errors path="remarks" cssClass="label label-warning"/>
                </div>
            </div>

            
            <div class="form-group">
                <div class="col-sm-2"></div>
                <div class="col-sm-10">
                    <button type="submit" class="btn btn-primary">Save</button>
                    <a href="${contextPath}/admin/credit/creditrule/list">Back</a>&nbsp;
                    <span><em class="text-danger">*</em> 代表必填值</span>
                </div>
            </div>
        </form:form>
</body>
</html>
