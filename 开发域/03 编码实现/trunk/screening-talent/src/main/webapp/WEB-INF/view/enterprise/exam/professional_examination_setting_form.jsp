<%--
  Created by IntelliJ IDEA.
  User: LZW
  Date: 2016/9/26
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../commons/taglib-header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>专业试卷设置</title>
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
    <li><a href="${contextPath}/enterprise/index">首页</a>
    </li>
    <li><a href="${contextPath}/enterprise/company/detail">企业管理</a>
    </li>
    <li><a href="${contextPath}/enterprise/member/list">职工信息</a>
    </li>
    <li class="active">职工信息录入</li>
</ol>

<ul class="nav nav-tabs">
    <li role="presentation"><a
            href="${contextPath}/enterprise/exam/load/${formDto.positionUuid}/general_examination_setting_${formDto.memberUuid}">综合试题设置</a>
    </li>

    <li role="presentation" class="active"><a
            href="javaScript:void(0);">专业试题设置</a>
    </li>

    <li role="presentation"><a
            href="${contextPath}/enterprise/exam/load_${formDto.positionUuid}_general_examination_${formDto.memberUuid}">综合试卷</a>
    </li>

    <li role="presentation"><a
            href="${contextPath}/enterprise/exam/load_${formDto.positionUuid}_general_examination_result/${formDto.memberUuid}">综合试卷成绩</a>
    </li>
</ul>

<br>

<div class="row">

    <h3 class="page-header text-primary">专业试题设置</h3>

    <div class="col-lg-12 col-sm-12">


        <form:form cssClass="form-horizontal" commandName="formDto" action="">

            <form:hidden path="memberUuid"/>
            <form:hidden path="positionUuid"/>

            <div id="tagDiv">
                <div id="generalTagsDiv" class="form-group">
                    <label class="col-sm-2 control-label">专业标签<em class="text-danger">*</em>: </label>

                    <div id="tagsDiv" class="col-sm-10">

                        <a class="tags-selected" href="javaScript:void(0);"
                           title="${formDto.position.positionName}"><span>${formDto.position.positionName}</span></a>

                            <%--<c:forEach items="${formDto.professionalTagDtos}" var="tag">
                                <a class="tags-selected" value="${tag.id}" title="${tag.name}"
                                   href="javascript:void(0);"><span>${tag.name}</span></a>
                                <input type="hidden" class="general-tag" name="professionalTags" value="${tag.id}">
                            </c:forEach>

                            <button class="btn btn-primary-circle btn-circle btn-sm" id="tag-plus-btn" type="button"><i
                                    class="glyphicon glyphicon-plus"></i>
                            </button>
                            <form:errors path="professionalTags" cssClass="label label-warning"/>--%>
                            <%--<p class="help-block">最多选择 <strong>5</strong>项</p>--%>

                    </div>
                </div>
            </div>

            <div id="degreeDiv" class="form-group">
                <label class="col-sm-2 control-label">试卷难度<em class="text-danger">*</em>: </label>

                <div class="col-sm-10">

                    <c:forEach items="${formDto.degrees}" var="degree" varStatus="vs">
                        <c:if test="${empty formDto.degree}" var="emptyDegree">
                            <input type="radio" name="degree" ${vs.index eq 1 ?'checked':''}
                                   value="${degree}"/>${degree.label}&nbsp;
                        </c:if>

                        <c:if test="${not emptyDegree}">
                            <input type="radio"
                                   name="degree" ${formDto.degree eq degree ?'checked':''}
                                   value="${degree}"/>${degree.label}&nbsp;
                        </c:if>


                    </c:forEach>
                    <form:errors path="degree" cssClass="label label-warning"/>
                    <p class="help-block">试卷难度，必填</p>
                </div>

            </div>


            <div id="selectedTypesDiv" class="form-group">
                <label class="col-sm-2 control-label">题型&数量<em class="text-danger">*</em>: </label>

                <div class="col-sm-10">
                    <c:forEach items="${formDto.types}" var="type">

                        <c:if test="${empty formDto.selectedTypes}" var="emptyTypes">
                            <input type="checkbox" name="selectedTypes" checked value="${type}"/>${type.label}&nbsp;
                        </c:if>

                        <c:if test="${not emptyTypes}">
                            <input type="checkbox"
                                   name="selectedTypes" ${fun:contains(formDto.selectedTypes, type)? 'checked':''}
                                   value="${type}"/>${type.label}&nbsp;
                        </c:if>
                        <!--单选题-->
                        <c:if test="${type eq 'SINGLE_CHOICE'}">
                            <input type="text" name="singleNum" class="form-control"
                                   style="width:25%;display: inline-block;"
                                   placeholder="单选题数量" value="${formDto.singleNum}">
                            &nbsp;
                            <span id="singleQuestion" class="text-primary">单选题总数：${formDto.totalSingle}</span>
                            <input type="hidden" name="totalSingle" value="${formDto.totalSingle}"/>
                        </c:if>
                        <!--多选题-->
                        <c:if test="${type eq 'MULTIPLE_CHOICE'}">
                            <input type="text" name="multipleNum" class="form-control"
                                   style="width:25%;display: inline-block;"
                                   placeholder="多选题数量" value="${formDto.multipleNum}">
                            &nbsp;
                            <span id="multipleQuestion" class="text-primary">多选题总数：${formDto.totalMultiple}</span>
                            <input type="hidden" name="totalMultiple" value="${formDto.totalMultiple}"/>
                        </c:if>
                        <!--简答题-->
                        <c:if test="${type eq 'SHORT_ANSWER'}">
                            <input type="text" name="shortNum" class="form-control"
                                   style="width:25%;display: inline-block;"
                                   placeholder="简答题数量" value="${formDto.shortNum}">
                            &nbsp;
                            <span id="shortQuestion" class="text-primary">简答题总数：${formDto.totalShort}</span>
                            <input type="hidden" name="totalShort" value="${formDto.totalShort}"/>
                        </c:if>
                        <br><br>

                    </c:forEach>
                    <form:errors path="selectedTypes" cssClass="label label-warning"/>
                    <p class="help-block">题型至少选择1项,选择题型后，应填入相应数量</p>
                </div>
            </div>


            <div class="form-group">
                <label class="col-sm-2 control-label">生成试卷: </label>

                <div class="col-sm-10">

                    <form:checkbox path="create" label="生成试卷"/>

                    <p class="help-block">若勾选此选项，则会生成试卷</p>
                </div>

            </div>

            <div class="form-group">

                <div class="col-sm-2"></div>
                <div class="col-sm-10">
                    <button type="button" class="btn btn-primary" id="preBtn">上一步</button>
                    <button type="button" class="btn btn-success" id="submitBtn">下一步</button>
                    &nbsp; <span><em class="text-danger">*</em>
					代表必填值</span>

                </div>

            </div>


        </form:form>

        <!--标签form-->
        <form:form commandName="formDto" cssClass="hidden" id="tagForm">

            <%--<c:forEach items="${formDto.professionalTagDtos}" var="tag">

                <input type="hidden" class="general-tag" name="tags" value="${tag.id}"/>

            </c:forEach>--%>

            <input type="hidden" name="positionUuid" value="${formDto.positionUuid}"/>

            <c:if test="${not empty formDto.degree}" var="emptyDegree">
                <input class="question-tag-degree" name="degree" type="hidden"
                       value="${formDto.degree}"/>
            </c:if>

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
                                            <div class="data-result aui-titlespan"><em>最多选择 <strong>5</strong>
                                                项</em>
                                                <c:forEach items="${formDto.professionalTagDtos}" var="tag">

                                                    <span class="svae_box aui-titlespan" data-code="${tag.id}"
                                                          data-name="${tag.name}" onclick="removespan(this)">${tag.name}<i>×</i></span>

                                                </c:forEach>
                                            </div>
                                            <div class="data-tabs">
                                                <ul>
                                                    <li onclick="removenode1(this,3)" data-selector="tab-all"
                                                        class="active"><a
                                                            href="javascript:;"><span>专业题型</span><em></em></a></li>
                                                </ul>
                                            </div>

                                            <div id="tag-content">


                                            </div>


                                            <div class="data-error" style="display: none;">最多只能选择 5 项</div>
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
<script src="${contextPath}/static/jq22/js/jq22.js"></script>
<script src="${contextPath}/static/js/jquery.form.js"></script>

<script>

    $(function () {
        new ProfessionalExaminationSettingForm();
    });

</script>


</body>
</html>
