<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../commons/taglib-header.jsp" %>
<html>
<head>
    <title>职工信息</title>
</head>
<body>

<ol class="breadcrumb">
    <li><a href="${contextPath}/enterprise/index">首页</a></li>
    <li><a href="${contextPath}/enterprise/company/detail">企业管理</a></li>
    <li><a href="${contextPath}/enterprise/member/list">职工信息</a></li>
    <li class="active">职工信息录入</li>
</ol>

<ul class="nav nav-tabs">
    <li role="presentation" class="active"><a href="">基本信息</a></li>
    <c:if test="${empty formDto.uuid}">
        <li role="presentation" class="disabled"><a href="javascript:void(0);">基本信息</a></li>
        <li role="presentation" class="disabled"><a href="javascript:void(0);">招聘环节</a></li>
        <li role="presentation" class="disabled"><a href="javascript:void(0);">离职环节</a></li>
        <li role="presentation" class="disabled"><a href="javascript:void(0);">工作环节</a></li>
    </c:if>
    <c:if test="${not empty formDto.uuid}">
        <li role="presentation"><a href="../form_2/${formDto.uuid}">基本信息</a></li>
        <li role="presentation"><a href="../form_3/${formDto.uuid}">招聘环节</a></li>
        <li role="presentation"><a href="../form_4/${formDto.uuid}">离职环节</a></li>
        <li role="presentation"><a href="../form_5/${formDto.uuid}">工作环节</a></li>
    </c:if>
</ul>
<br/>

<form:form commandName="formDto" cssClass="form-horizontal">
    <div class="form-group">
        <label for="chName" class="col-sm-2 control-label">中文姓名<em
                class="text-danger">*</em> </label>

        <div class="col-sm-10">
            <form:input path="chName" cssClass="form-control"
                        placeholder="中文姓名" required="required" maxlength="255"/>
            <form:errors path="chName" cssClass="label label-warning"/>
            <p class="help-block">2-5位中文字, 必填</p>
        </div>
    </div>

    <div class="form-group">
        <label for="enName" class="col-sm-2 control-label">英文姓名</label>

        <div class="col-sm-10">
            <form:input path="enName" cssClass="form-control"
                        placeholder="英文姓名" maxlength="255"/>
            <form:errors path="enName" cssClass="label label-warning"/>
            <p class="help-block">格式: 名前姓后中间空格或就是名字, 如: Peter, 可选</p>
        </div>
    </div>


    <div class="form-group">
        <label for="mobile" class="col-sm-2 control-label">手机<em
                class="text-danger">*</em> </label>

        <div class="col-sm-10">
            <form:input path="mobile" cssClass="form-control"
                        placeholder="手机" required="required" maxlength="255"/>
            <form:errors path="mobile" cssClass="label label-warning"/>
            <p class="help-block">11位数字（请务必准确）</p>
        </div>
    </div>

    <div class="form-group">
        <label for="idNumber" class="col-sm-2 control-label">身份证号</label>

        <div class="col-sm-10">
            <form:input path="idNumber" cssClass="form-control"
                        placeholder="身份证号" maxlength="255"/>
            <form:errors path="idNumber" cssClass="label label-warning"/>
            <p class="help-block">如果有尽量填写18位, 可选</p>
        </div>
    </div>

    <div class="form-group">
        <label for="email" class="col-sm-2 control-label">电子邮箱 </label>

        <div class="col-sm-10">
            <form:input path="email" cssClass="form-control"
                        placeholder="电子邮箱" maxlength="255"/>
            <form:errors path="email" cssClass="label label-warning"/>
            <p class="help-block">可选</p>
        </div>
    </div>


    <div class="form-group">
        <div class="col-sm-2"></div>
        <div class="col-sm-10">
            <button type="submit" name="next" value="true" class="btn btn-primary">下一步</button>
            <a href="../list" class="btn btn-success">返回</a>
            &nbsp; <span><em
                class="text-danger">*</em> 代表必填值</span>
            <c:if test="${param.alert eq 'saveOK'}"><span class="label label-success">保存完成</span></c:if>
        </div>
    </div>
</form:form>
</body>
</html>
