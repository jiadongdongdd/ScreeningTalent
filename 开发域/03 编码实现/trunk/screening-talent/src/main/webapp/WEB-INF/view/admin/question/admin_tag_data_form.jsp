<%--
  Created by IntelliJ IDEA.
  User: LZW
  Date: 2016/9/20
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../commons/taglib-header.jsp" %>

<div class="data-container data-container-0">
    <div class="view-all">
        <div class="cate-list-normal">
            <tags:csrf_hidden/>
            <div class="cate-list-title"><em id="tagPName">${dto.pName}</em></div>
            <ul class="clearfix" id="znhy-table">
                <c:forEach items="${dto.tags}" var="tag" varStatus="vs">
                    <li><a href="javascript:;" data-code="${tag.id}"
                           data-name="${tag.name}" class="d-cate" data-ditem="${tag.id}"
                           onclick="tagClick(this,${tag.parentTag})">${tag.name}</a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>
