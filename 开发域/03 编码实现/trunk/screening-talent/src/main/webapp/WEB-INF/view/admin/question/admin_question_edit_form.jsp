<%--
  Created by IntelliJ IDEA.
  User: LZW
  Date: 2016/9/21
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../commons/taglib-header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>题目编辑</title>
    <link rel="stylesheet" href="${contextPath}/static/js/select2/select2.css">
    <link rel="stylesheet" href="${contextPath}/static/js/select2/select2-bootstrap.css">
    <link rel="stylesheet" href="${contextPath}/static/css/zyzn_1.css">
    <style>

        .btn-circle.btn-lg {
            width: 50px;
            height: 50px;
            padding: 10px 16px;
            border-radius: 25px;
            font-size: 18px;
            line-height: 1.33;
        }

        .btn-circle {
            width: 30px;
            height: 30px;
            padding: 6px 0;
            border-radius: 15px;
            text-align: center;
            font-size: 12px;
            line-height: 1.428571429;
        }

        .btn-primary-circle {
            background-color: #1ab394;
            border-color: #1ab394;
            color: #FFFFFF;
        }

        .tags-selected span {
            display: inline-block;
            vertical-align: middle;
            background-color: #5297cc;
            border-radius: 15px;
            padding: 0 14px 2px;
            color: #fff;
            margin: 2px 5px 2px 0;
            line-height: 26px;
            white-space: nowrap;
        }

    </style>
</head>
<body>

<ol class="breadcrumb">
    <li><a href="${contextPath}/admin/index">首页</a>
    </li>
    <li><a href="${contextPath}/admin/tag/tree">题库管理</a>
    </li>
    <li><a href="${contextPath}/admin/question/list">题目列表</a>
    </li>
    <li class="active">题目编辑</li>
</ol>

<div class="row">

    <h3 class="page-header text-primary">题目编辑</h3>

    <div class="col-lg-12 col-sm-12">

        <form:form cssClass="form-horizontal" commandName="formDto" action="">

            <form:hidden path="uuid"/>

            <div class="form-group">
                <label class="col-sm-2 control-label">题目<em class="text-danger">*</em> </label>

                <div id="titleDiv" class="col-sm-10">
                    <form:textarea path="title" cssClass="form-control" rows="5"/>
                    <form:errors path="title" cssClass="label label-warning"/>

                    <p class="help-block">题目,必填</p>
                </div>

            </div>

            <div class="form-group">
                <label class="col-sm-2 control-label">类型<em class="text-danger">*</em> </label>

                <div id="typeDiv" class="col-sm-10">

                    <select class="form-control" name="type">
                        <c:forEach items="${formDto.types}" var="t">
                            <option value="${t}" ${t eq formDto.type ?'selected':''}>${t.label}</option>
                        </c:forEach>
                    </select>
                    <form:errors path="type" cssClass="label label-warning"/>

                    <p class="help-block">题目类型,必选</p>
                </div>
            </div>

            <div id="optionsDiv" class="${formDto.type eq 'SHORT_ANSWER' ? 'hidden':''}">

                <c:if test="${empty formDto.optionDtos}">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">选项&答案<em class="text-danger">*</em> </label>

                        <div class="col-sm-6">
                            <div class="input-group">
                                <span class="input-group-addon"><input name="answer" type="radio" value="A">A</span>
                                <input type="text" name="options" class="form-control">
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">&nbsp;</label>

                        <div class="col-sm-6">
                            <div class="input-group">
                                <span class="input-group-addon"><input name="answer" type="radio" value="B">B</span>
                                <input type="text" name="options" class="form-control">
                            </div>
                        </div>

                        <div class="col-sm-4">
                            <button type="button" class="btn btn-primary btn-sm addButton"
                                    name="optionAdd">增加
                            </button>
                        </div>
                    </div>


                </c:if>

                <c:if test="${not empty formDto.optionDtos}">
                    <c:forEach items="${formDto.optionDtos}" var="option" varStatus="vs">

                        <c:if test="${vs.index eq 0}" var="first">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">选项&答案<em class="text-danger">*</em> </label>

                                <div class="col-sm-6">
                                    <div class="input-group">
                                    <span class="input-group-addon">
                                        <c:if test="${formDto.type eq 'SINGLE_CHOICE'}">
                                            <input name="answer" type="radio"
                                                   value="${option.letter }" ${option.letter eq formDto.answer?'checked':''}>${option.letter}
                                        </c:if>

                                        <c:if test="${formDto.type eq 'MULTIPLE_CHOICE'}">
                                            <input name="answer" type="checkbox"
                                                   value="${option.letter }" ${fun:contains(formDto.answer,option.letter)  ?'checked':''}>${option.letter}
                                        </c:if>
                                    </span>
                                        <input type="text" name="options" class="form-control"
                                               value="${option.content}">
                                    </div>
                                </div>
                            </div>
                        </c:if>

                        <c:if test="${vs.index eq 1}" var="second">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">&nbsp;</label>

                                <div class="col-sm-6">
                                    <div class="input-group">
                                    <span class="input-group-addon">
                                        <c:if test="${formDto.type eq 'SINGLE_CHOICE'}">
                                            <input name="answer" type="radio"
                                                   value="${option.letter }" ${option.letter eq formDto.answer?'checked':''}>${option.letter}
                                        </c:if>

                                        <c:if test="${formDto.type eq 'MULTIPLE_CHOICE'}">
                                            <input name="answer" type="checkbox"
                                                   value="${option.letter }" ${fun:contains(formDto.answer,option.letter)  ?'checked':''}>${option.letter}
                                        </c:if>
                                    </span>
                                        <input type="text" name="options" class="form-control"
                                               value="${option.content}">
                                    </div>
                                </div>


                                <div class="col-sm-4">
                                    <button type="button" class="btn btn-primary btn-sm addButton"
                                            name="optionAdd">增加
                                    </button>
                                </div>
                            </div>
                        </c:if>

                        <c:if test="${not first and not second}">

                            <div class="form-group">
                                <label class="control-label col-sm-2">&nbsp;</label>

                                <div class="col-sm-6">
                                    <div class="input-group">
                                    <span class="input-group-addon">
                                        <c:if test="${formDto.type eq 'SINGLE_CHOICE'}">
                                            <input name="answer" type="radio"
                                                   value="${option.letter }" ${option.letter eq formDto.answer?'checked':''}>${option.letter}
                                        </c:if>

                                        <c:if test="${formDto.type eq 'MULTIPLE_CHOICE'}">
                                            <input name="answer" type="checkbox"
                                                   value="${option.letter }" ${fun:contains(formDto.answer,option.letter)  ?'checked':''}>${option.letter}
                                        </c:if>
                                    </span>
                                        <input type="text" class="form-control" name="options"
                                               value="${option.content}">
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <button type="button" class="btn btn-default btn-sm removeButton">删除
                                    </button>
                                </div>
                            </div>

                        </c:if>

                    </c:forEach>
                </c:if>


                <div class="form-group hide" id="optionAddTemplate">
                    <label class="control-label col-sm-2">&nbsp;</label>

                    <div class="col-sm-6">
                        <div class="input-group">
                            <span class="input-group-addon"></span>
                            <input type="text" class="form-control">
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <button type="button" class="btn btn-default btn-sm removeButton">删除
                        </button>
                    </div>
                </div>

                <div class="form-group">

                    <label class="col-sm-2 control-label">&nbsp;</label>

                    <div id="options1Div" class="col-sm-10">
                        <form:errors path="options" cssClass="label label-warning"/>
                        <form:errors path="answer" cssClass="label label-warning"/>

                        <p class="help-block">选项&答案，选项必填，答案必选</p>
                    </div>

                </div>

            </div>

            <div id="non-standard-answer" class="form-group ${formDto.type eq 'SHORT_ANSWER' ? '':'hidden'}">
                <label class="col-sm-2 control-label">简答题答案<em class="text-danger">*</em> </label>

                <div id="answerDiv" class="col-sm-10">

                    <textarea name="answer" class="form-control" rows="5" ${formDto.type eq 'SHORT_ANSWER'
                            ?'':'disabled'}>${formDto.answer}</textarea>
                    <form:errors path="answer" cssClass="label label-warning"/>

                    <p class="help-block">简答题答案,必填</p>
                </div>
            </div>

            <div class="form-group">

                <label class="col-sm-2 control-label">适合场景<em class="text-danger">*</em> </label>

                <div id="suitableTypeDiv" class="col-sm-10">

                    <select class="form-control" name="suitableType">

                        <c:forEach items="${formDto.suitableTypes}" var="t">
                            <option value="${t}" ${t eq formDto.suitableType ? 'selected':''}>${t.label}</option>
                        </c:forEach>

                    </select>

                    <p class="help-block">请选择适合场景</p>

                </div>

            </div>

            <div id="professionalDiv" class="form-group ${formDto.suitableType eq 'GENERAL' ?'hidden':''}">
                <label class="col-sm-2 control-label">适合职位<em class="text-danger">*</em></label>

                <div class="col-sm-10">
                    <select class="form-control" name="positionUuid" style="width: 100%;height: 34px;">
                        <c:forEach items="${formDto.positions}" var="p">
                            <option value="${p.uuid}" ${p.uuid eq formDto.positionUuid ?'selected':''}>${p.positionName}</option>
                        </c:forEach>
                    </select>

                    <p class="help-block">适合职位，必选</p>
                </div>
                <form:errors path="positionUuid" cssClass="label label-warning"/>


            </div>
            <div id="generalDiv" class="${formDto.suitableType eq 'GENERAL' ?'':'hidden'}">
                <div id="tagDiv">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">标签<em class="text-danger">*</em> </label>

                        <div id="tagsDiv" class="col-sm-10">

                            <c:forEach items="${formDto.selectedTags}" var="tag">

                                <a class="tags-selected" href="javaScript:void(0);" value="${tag.id}"
                                   title="${tag.name}"><span>${tag.name}</span></a>
                                <input type="hidden" name="tags" value="${tag.id}"/>

                            </c:forEach>

                            <button class="btn btn-primary-circle btn-circle btn-sm" id="tag-plus-btn" type="button"><i
                                    class="glyphicon glyphicon-plus"></i>
                            </button>
                            <form:errors path="tags" cssClass="label label-warning"/>

                            <p class="help-block">请选择一项综合标签</p>

                        </div>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-2 control-label">难度<em class="text-danger">*</em> </label>

                <div id="degreeDiv" class="col-sm-10">
                    <c:forEach items="${formDto.degrees}" var="d">
                        <input name="degree" type="radio"
                               value="${d}" ${d eq formDto.degree ? 'checked':''}/>${d.label}&nbsp;
                    </c:forEach>
                    <form:errors path="degree" cssClass="label label-warning"/>

                    <p class="help-block">试题难度，必选</p>

                </div>

            </div>

            <div class="form-group">
                <div class="col-sm-2"></div>
                <div class="col-sm-10">
                    <button type="button" class="btn btn-primary" id="submitBtn">保存</button>
                    <a href="../list">返回</a> &nbsp; <span><em class="text-danger">*</em>
					代表必填值</span>

                </div>
            </div>


        </form:form>


    </div>


</div>

<!--弹出的标签选择页面-->
<div class="aui_state_box" style="display: none">
    <div class="aui_state_box_bg"></div>
    <div class="aui_alert_zn aui_outer" id="drag_con">
        <table class="aui_border" style="border:2px solid #8a9499;">
            <tbody>
            <tr>
                <td class="aui_w"></td>
                <td class="aui_c">
                    <div class="aui_inner">
                        <table class="aui_dialog">
                            <tbody>
                            <tr>
                                <td class="aui_header" colspan="2">
                                    <div class="aui_titleBar">
                                        <div class="aui_title" style="cursor: move;">选择标签</div>
                                        <a href="javascript:;" class="aui_close close-btn">×</a></div>
                                </td>
                            </tr>
                            <tr>
                                <td class="aui_icon" style="display: none;">
                                    <div class="aui_iconBg"
                                         style="background: transparent none repeat scroll 0% 0%;"></div>
                                </td>
                                <td class="aui_main" style="width: auto; height: auto;">
                                    <div class="aui_content" style="padding: 0px;">
                                        <div style="width: 900px; position:relative ">
                                            <div class="data-result aui-titlespan">
                                                <em>最多选择 <strong>1</strong>项</em>
                                                <c:forEach items="${formDto.selectedTags}" var="tag">
                                                    <span class="svae_box aui-titlespan" data-code="${tag.id}"
                                                          data-name="${tag.name}"
                                                          onclick="removespan(this)">${tag.name}<i>×</i></span>
                                                </c:forEach>
                                            </div>
                                            <div class="data-tabs">
                                                <ul>
                                                    <li onclick="removenode(this,2)" data-selector="tab-all"
                                                        class="active"><a
                                                            href="javascript:;"><span>综合标签</span><em></em></a></li>
                                                </ul>
                                            </div>

                                            <div id="tag-content">


                                            </div>


                                            <div class="data-error" style="display: none;">最多只能选择 1 项</div>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td class="aui_footer" colspan="2">
                                    <div class="aui_buttons">
                                        <button class="aui-btn aui-btn-primary" type="button" id="submitTagBtn">确定
                                        </button>
                                        <button class="aui-btn aui-btn-light close-btn" type="button">取消
                                        </button>
                                    </div>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </td>
                <td class="aui_e"></td>
            </tr>
            </tbody>
        </table>
    </div>
</div>

<script src="${contextPath}/static/js/select2/select2.js"></script>
<script src="${contextPath}/static/js/select2/zh-CN.js"></script>
<script src="${contextPath}/static/js/jquery.form.js"></script>

<script>

    $(function () {
        new AdminQuestionEditForm();
    });

</script>

</body>
</html>
