<%--
  Created by IntelliJ IDEA.
  User: LZW
  Date: 2016/9/18
  Time: 11:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../commons/taglib-header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>标签设置</title>
    <link rel="stylesheet" href="${contextPath}/static/ztree/zTreeStyle.css">
    <style>
        .ztree li span.button.add {
            margin-left: 2px;
            margin-right: -1px;
            background-position: -144px 0;
            vertical-align: top;
            *vertical-align: middle
        }
    </style>
</head>
<body>

<ol class="breadcrumb">
    <li><a href="${contextPath}/admin/index">首页</a>
    </li>
    <li><a href="${contextPath}/admin/tag/tree">题库管理</a>
    </li>
    <li class="active">标签设置</li>
</ol>


<h4 class="page-header text-primary">标签设置</h4>

<br>

<c:if test="${empty paginated.list}">
    <div class="alert alert-info">

        <p>
            您还未进行标签初始化，请点击右侧按钮进行初始化。

        <form action="${contextPath}/admin/tag/initial_tags" method="post">
            <tags:csrf_hidden/>
            <button type="submit" class="btn btn-primary">初始化标签</button>
        </form>

        </p>


    </div>

</c:if>


<c:if test="${not empty paginated.list}">
    <tags:csrf_hidden/>
    <div class="zTreeDemoBackground left">
        <ul id="treeDemo" class="ztree"></ul>
    </div>

    <div class="hidden">

        <input type="checkbox" id="remove" class="checkbox" checked/>
        <input type="checkbox" id="rename" class="checkbox " checked/>
        <input type="text" id="removeTitle" value="删除"/><br/>
        <input type="text" id="renameTitle" value="重命名"/>

    </div>
</c:if>


<script src="${contextPath}/static/ztree/jquery.ztree.all.min.js"></script>
<script>

    $(function () {
        new QuestionTagTree();
    });

</script>

</body>
</html>
