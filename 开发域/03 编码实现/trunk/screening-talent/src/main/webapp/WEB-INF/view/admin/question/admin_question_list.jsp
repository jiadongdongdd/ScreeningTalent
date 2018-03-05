<%--
  Created by IntelliJ IDEA.
  User: LZW
  Date: 2016/9/20
  Time: 9:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../commons/taglib-header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>题目列表</title>
</head>
<body>

<ol class="breadcrumb">
    <li><a href="">首页</a>
    </li>
    <li><a href="${contextPath}/admin/tag/tree">题库管理</a>
    </li>
    <li class="active">题目列表</li>
</ol>

<div class="row">

    <h3 class="page-header text-primary">题目列表</h3>

    <div class="col-lg-12 col-sm-12">

        <div class="pull-right">
            <a href="form/create" class="btn btn-success">录入</a>
        </div>

        <form action="" class="form-inline" id="filterForm">

            <div class="form-group">
                <input type="text" class="form-control" placeholder="题目名称" name="title" value="${paginated.title}">
            </div>
            <select class="form-control" name="questionType">
                <option value="">全部题型</option>
                <c:forEach items="${paginated.questionTypes}" var="type">
                    <option value="${type}"
                            <c:if test="${type eq paginated.questionType}">selected="selected"</c:if> >${type.label}</option>
                </c:forEach>
            </select>
            <button type="submit" class="btn btn-default">
                <em class="glyphicon glyphicon-search"></em>
            </button>
            &nbsp;<span class="text-info">共${paginated.totalSize}条记录</span>
        </form>

    </div>

</div>

<div class="table-responsive">
    <dis:table list="${paginated}" id="d" form="filterForm" class="table table-striped">

        <dis:column property="number" title="题目编号"/>
        <dis:column property="title" title="题目名称"/>
        <dis:column title="类型">
            ${d.type.label}
        </dis:column>
        <dis:column title="标签">
            <c:forEach items="${d.tagDtos}" var="tag">
                <span class=" label label-default">${tag.name}</span> &nbsp;
            </c:forEach>
        </dis:column>
        <dis:column title="操作">
            <a href="form/edit_${d.uuid}">编辑</a>
            <a href="remove_${d.uuid}" onclick="return confirm('确认删除题目 ${d.title}' )">删除</a>
        </dis:column>

    </dis:table>
</div>

</body>
</html>
