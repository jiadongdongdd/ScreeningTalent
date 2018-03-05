<%--
 * Copyright (c) 2015 MONKEYK Information Technology Co. Ltd
 * www.monkeyk.com
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * MONKEYK Information Technology Co. Ltd ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with MONKEYK Information Technology Co. Ltd.
--%>
<%--
 * 
 * @author Shengzhao Li
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../commons/taglib-header.jsp" %>

<html>
<head>
    <title>应用列表</title>
</head>
<body>
<ol class="breadcrumb">
    <li><a href="${contextPath}/">Dashboard</a></li>
    <li class="active">应用列表</li>
</ol>
<div class="pull-right">
    <a href="form/create" class="btn btn-primary"><em
            class="glyphicon glyphicon-plus"></em>企业</a>
</div>
<form action="" class="form-inline" id="filterForm">
    <div class="form-group">
        <input type="text" class="form-control" placeholder="企业名称" name="companyName" value="${paginated.companyName}"/>
    </div>
    <button type="submit" class="btn btn-default"><em class="glyphicon glyphicon-search"></em></button>
    &nbsp;<span class="text-info">共${paginated.totalSize}个应用</span>
</form>
<br/>

<ul class="list-group">

    <c:if test="${empty paginated.list}">
        <li class="list-group-item">
            <p class="text-muted">暂无数据</p>
        </li>
    </c:if>
    <c:forEach items="${paginated.list}" var="comp">
        <li class="list-group-item">
            <div class="list-group-item-text text-muted">
			                用户名: <span class="text-info">${comp.username}</span>
			                <br/>
			                企业名称: <span class="text-info">${comp.companyName}</span>
			                <br/>
			                企业邮箱: <span class="text-info">${comp.companyEmail}</span>
			                <br/>
			                联系人: <span class="text-info">${comp.contacts}</span>
			                <br/>
			                联系人电话: <span class="text-info">${comp.contactsPhone}</span>
			                <br/>
                <img src="${contextPath}/public/image/${comp.authorityPath}?w=150" alt="Img"/>
			      <a href="form/${comp.guid}">编辑</a>
            </div>
        </li>
    </c:forEach>

</ul>

<%--paginated--%>
<dis:table list="${paginated}" id="d" form="filterForm" class="table hidden table-striped table-hover">
    <dis:column property="guid"/>
</dis:table>

</body>
</html>
