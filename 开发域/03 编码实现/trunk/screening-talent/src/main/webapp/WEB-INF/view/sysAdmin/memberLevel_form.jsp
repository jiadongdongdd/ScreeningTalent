
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../commons/taglib-header.jsp" %>
<html>
<head>
    <title>增加会员级别</title>

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
    <li><a href="${contextPath }/admin/credit/memberlevel/list">会员级别</a></li>
    <li class="active">新增</li>
</ol>

<h2 class="page-header">增加会员级别</h2>

 <form:form commandName="formDto" cssClass="form-horizontal" method="post">
            <div class="form-group">
                <label for="level" class="col-sm-2 control-label">会员级别<em class="text-danger">*</em></label>
	
				<form:hidden path="uuid"/>
                <div class="col-sm-10">
                    <form:input path="level" cssClass="form-control"
                                placeholder="会员级别"
                                required="required" maxlength="255"/>
                    <form:errors path="level" cssClass="label label-warning"/>
                    <p class="help-block">必填值</p>
                </div>
            </div>

		<div class="form-group">
			<label for="mixNumber" class="col-sm-2 control-label">累计增加职工数最小值<em
				class="text-danger">*</em>
			</label>

			<div class="col-sm-10">
				<form:input path="mixNumber" cssClass="form-control"
					placeholder="累计增加职工数最小值" required="required" maxlength="255" />
				<form:errors path="mixNumber" cssClass="label label-warning" />
				<p class="help-block">必填值</p>
			</div>
		</div>

		<div class="form-group">
			<label for="maxNumber" class="col-sm-2 control-label">累计增加职工数最大值<em
				class="text-danger">*</em> </label>

			<div class="col-sm-10">
				<form:input path="maxNumber" cssClass="form-control"
					placeholder="累计增加职工数最大值" required="required" maxlength="255" />
				<form:errors path="maxNumber" cssClass="label label-warning" />
				<p class="help-block">必填值</p>
			</div>
		</div>

		<div class="form-group">
                <label for="rewardCoefficient" class="col-sm-2 control-label">奖励系数<em
                        class="text-danger">*</em></label>

                <div class="col-sm-10">
                    <form:input path="rewardCoefficient" cssClass="form-control"
                                placeholder="奖励系数"
                                required="required" maxlength="255"/>
                    <form:errors path="rewardCoefficient" cssClass="label label-warning"/>
                    <p class="help-block">奖励系数</p>
                </div>
            </div>
            
                      <div class="form-group">
                <label for="remarks" class="col-sm-2 control-label">备注</label>

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
                    <a href="${contextPath}/admin/credit/memberlevel/list">Back</a>&nbsp;
                </div>
            </div>
        </form:form>
</body>
</html>
