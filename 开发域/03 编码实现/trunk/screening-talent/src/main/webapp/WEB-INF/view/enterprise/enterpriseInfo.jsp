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
    <title>企业详情</title>
</head>
<body>
<ol class="breadcrumb">
    <li><a href="${contextPath}/enterprise/index">首页</a></li>
    <li class="#">企业管理</li>
    <li class="active">企业详情</li>
</ol>
 <div id="indexnavbar"  >
 	 <span class="icon-bar">本次登录IP:${ip} &nbsp;</span>
 	 <span class="icon-bar">上次登录IP:${log.ip } &nbsp;</span>
 	 <span class="icon-bar">上次登录时间:<fmt:formatDate value="${log.date }" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></span>
 </div>
<ul class="list-group">
        <li class="list-group-item">
            <div class="list-group-item-text text-muted">
            	<div class="row">
            		<div class="col-xs-6 col-sm-6">
        				用户名: <span class="text-info">${formDto.username}</span>
      				</div>
      				<div class="col-xs-6 col-sm-6">
        				当前剩余积分:
        				<c:if test="${empty formDto.creditNumber}">
	        				<span class="text-info">0</span> 
        				</c:if>
        				<c:if test="${ not empty formDto.creditNumber}">
	        				<span class="text-info">${formDto.creditNumber}</span> 
        				</c:if>
      				</div>
            	</div>
            	<div class="row">
            		<div class="col-xs-6 col-sm-6">
        				企业名称: <span class="text-info">${formDto.companyName}</span>
      				</div>
            	</div>
            	<div class="row">
            		<div class="col-xs-6 col-sm-6">
        				企业邮箱: <span class="text-info">${formDto.companyEmail}</span>
      				</div>
            	</div>
            	<div class="row">
            		<div class="col-xs-6 col-sm-6">
         				企业联系人: <span class="text-info">${formDto.contacts}</span>
      				</div>
            	</div>
            	<div class="row">
            		<div class="col-xs-6 col-sm-6">
         				联系人电话: <span class="text-info">${formDto.contactsPhone}</span>
      				</div>
            	</div>
            	<div class="row">
            		<div class="col-xs-6 col-sm-6">
        				本网注册时间: <span class="text-info"><fmt:formatDate value="${formDto.createTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></span>
      				</div>
            	</div>
            	<div class="row">
            		<div class="col-xs-6 col-sm-6">
         				企业营业执照: <span class="text-info"><img src="${contextPath}/public/image/${formDto.businessPath}?w=120" alt="Img"/></span>
      				</div>
            	</div>
            	<div class="row">
            		<div class="col-xs-6 col-sm-6">
        				企业机构证书: <span class="text-info"><img src="${contextPath}/public/image/${formDto.authorityPath}?w=120" alt="Img"/></span>
      				</div>
            	</div>
            	<div class="row">
            		<div class="col-xs-6 col-sm-6">
        				企业录入人数：
        				<c:if test="${empty mumberNumber}">
	        				<span class="text-info">0</span> 
        				</c:if>
        				<c:if test="${ not empty mumberNumber}">
	        				<span class="text-info">${mumberNumber}</span> 
        				</c:if>
      				</div>
            	</div>
            </div>
        </li>

</ul>
</body>
</html>
