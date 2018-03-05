<%--
  Created by IntelliJ IDEA.
  User: LZW
  Date: 2016/9/27
  Time: 10:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../commons/taglib-header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>综合试卷</title>
    <style type="text/css" media=print>
        .noprint {
            display: none
        }
    </style>
</head>
<body>

<ol class="breadcrumb noprint">
    <li><a href="${contextPath}/enterprise/index">首页</a>
    </li>
    <li><a href="${contextPath}/enterprise/company/detail">企业管理</a>
    </li>
    <li><a href="${contextPath}/enterprise/member/list">职工信息</a>
    </li>
    <li class="active">职工信息录入</li>
</ol>

<ul class="nav nav-tabs noprint">
    <li role="presentation"><a
            href="${contextPath}/enterprise/exam/load/${formDto.positionUuid}/general_examination_setting_${formDto.memberUuid}">综合试题设置</a>
    </li>

    <li role="presentation"><a
            href="${contextPath}/enterprise/exam/load/${formDto.positionUuid}/professional_examination_setting_${formDto.memberUuid}">专业试题设置</a>
    </li>

    <li role="presentation" class="active"><a
            href="javaScript:void(0);">综合试卷</a>
    </li>

    <li role="presentation"><a
            href="${contextPath}/enterprise/exam/load_${formDto.positionUuid}_general_examination_result/${formDto.memberUuid}">综合试卷成绩</a>
    </li>
</ul>

<br>


<div class="row">

    <c:if test="${not formDto.setting}" var="notSetting">

        <div class="alert alert-info">

            <p>您还未进行综合试题设置&nbsp;<span><a
                    href="${contextPath}/enterprise/exam/load/${formDto.positionUuid}/general_examination_setting_${formDto.memberUuid}"
                    class="label label-default">设置</a></span>
            </p>

        </div>

    </c:if>

    <c:if test="${not notSetting}">

        <div class="btn-group btn-group-sm noprint" style="float: right;">
            <a href="javaScript:window.print();" class="btn btn-default">打印试卷</a>
            <a href="javaScript:void(0);" id="show_answer_btn" class="btn btn-info">显示答案</a>
            <c:if test="${not formDto.existed}">
                <a href="${contextPath}/enterprise/exam/load_${formDto.positionUuid}_general_examination_${formDto.memberUuid}"
                   class="btn btn-primary">换一批</a>
            </c:if>
            <a href="javaScript:void(0);" id="type-answer-btn" class="btn  btn-success">录入答案</a>

        </div>


        <h3 class="page-header text-primary">综合试卷</h3>

        <div class="col-lg-12 col-sm-12">
            <form:form cssClass="form-horizontal" commandName="formDto">
                <form:hidden path="memberUuid"/>
                <form:hidden path="positionUuid"/>
                <div class="list-group">
                    <c:if test="${not empty formDto.singleQuestionDtos}">
                        <h4 class="text-primary">单选题</h4>
                        <c:forEach items="${formDto.singleQuestionDtos}" var="question" varStatus="vs">
                            <a  class="list-group-item">
                                <!--标题-->
                                <p><strong>${vs.index+1}.&nbsp;${question.title}</strong></p>
                                <!--选项-->

                                <p>
                                    <c:forEach items="${question.optionDtos}" var="option">
                                        <input type="radio"
                                               class="radio_answer type-answer ${formDto.existed ? '':'hidden'}"
                                               name="radio_${vs.index + 1}"
                                               value="${option.letter}" ${formDto.singleAnswers[vs.index] eq option.letter?'checked':''}
                                                />&nbsp;<strong>${option.letter}.&nbsp;${option.content}</strong>&nbsp;
                                    </c:forEach>
                                </p>
                                <input type="hidden" name="singleAnswers" value="" id="radio_${vs.index + 1}"/>
                                <!--答案-->
                                <p class="answer" style="display: none;"><strong
                                        style="color: #ff0000">答案：${question.answer}</strong></p>
                            </a>
                        </c:forEach>
                    </c:if>
                </div>

                <div class="list-group">
                    <c:if test="${not empty formDto.multipleQuestionDtos}">
                        <h4 class="text-primary">多选题</h4>
                        <c:forEach items="${formDto.multipleQuestionDtos}" var="question" varStatus="vs">
                            <a  class="list-group-item">
                                <!--标题-->
                                <p><strong>${vs.index+1}.&nbsp;${question.title}</strong></p>
                                <!--选项-->
                                <p>
                                    <c:forEach items="${question.optionDtos}" var="option">
                                        <input type="checkbox"
                                               class="checkbox_answer type-answer ${formDto.existed ? '':'hidden'}"
                                               name="checkbox_${vs.index + 1}"
                                               value="${option.letter}" ${fun:contains(formDto.multipleAnswers[vs.index],option.letter)?'checked':''}
                                                />&nbsp;<strong>${option.letter}.&nbsp;${option.content}</strong>&nbsp;
                                    </c:forEach>
                                </p>
                                <input type="hidden" name="multipleAnswers" value="" id="checkbox_${vs.index + 1}"/>
                                <!--答案-->
                                <p class="answer" style="display: none;"><strong
                                        style="color: #ff0000">答案：${question.answer}</strong></p>
                            </a>
                        </c:forEach>
                    </c:if>
                </div>

                <div class="list-group">
                    <c:if test="${not empty formDto.shortQuestionDtos}">
                        <h4 class="text-primary">简答题</h4>
                        <c:forEach items="${formDto.shortQuestionDtos}" var="question" varStatus="vs">
                            <a  class="list-group-item">
                                <!--标题-->
                                <p><strong>${vs.index+1}.&nbsp;${question.title}</strong></p>

                            <textarea name="shortAnswers" rows="5"
                                      class="form-control type-answer ${formDto.existed ? '':'hidden'}"
                                    >
                                    ${formDto.shortAnswers[vs.index]}
                            </textarea>

                                <br>
                                <input name="scores" class="form-control type-answer noprint ${formDto.existed ? '':'hidden'}"
                                       placeholder="分数,1~5分"
                                       value="${formDto.scores[vs.index]}"/>


                                <!--答案-->
                                <p class="answer" style="display: none;"><strong
                                        style="color: #ff0000">答案：${question.answer}</strong></p>
                            </a>
                        </c:forEach>
                    </c:if>
                </div>

                <c:forEach items="${formDto.singleQuestions}" var="question">
                    <input type="hidden" name="singleQuestions" value="${question}"/>
                </c:forEach>

                <c:forEach items="${formDto.multipleQuestions}" var="question">
                    <input type="hidden" name="multipleQuestions" value="${question}"/>
                </c:forEach>

                <c:forEach items="${formDto.shortQuestions}" var="question">
                    <input type="hidden" name="shortQuestions" value="${question}"/>
                </c:forEach>

                <div class="form-group noprint">

                    <div class="col-sm-2"></div>
                    <div class="col-sm-10">
                        <a href="${contextPath}/enterprise/exam/load/${formDto.positionUuid}/professional_examination_setting_${formDto.memberUuid}"
                           class="btn btn-primary">上一步</a>
                        <button type="submit" id="submitBtn" class="btn btn-success">下一步</button>
                    </div>

                </div>

            </form:form>

        </div>
    </c:if>


</div>

<script>

    $(function () {
        new GeneralExaminationForm();
    });

</script>


</body>
</html>
