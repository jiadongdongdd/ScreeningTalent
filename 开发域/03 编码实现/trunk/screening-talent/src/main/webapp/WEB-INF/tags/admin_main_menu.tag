<%@tag pageEncoding="UTF-8" %>
<li role="presentation"><a
        href="${contextPath}/admin/company/NoVerify/false" style="">企业管理<i
        class="glyphicon glyphicon-menu-down"></i> </a>
    <ol class="nav nav-pills nav-stacked">
        <li role="presentation" id="adminCompanyNoVerifyMenu"><a
                href="${contextPath}/admin/company/NoVerify/false"> 待审核企业</a>
        </li>
        <li role="presentation" id="adminCompanyPassVerifyMenu"><a
                href="${contextPath}/admin/company/passVerify/true">已审核企业</a>
        </li>
        <li role="presentation" id="adminCompanyDelete"><a
                href="${contextPath}/admin/company/deleteList">删除记录</a>
        </li>
        <li role="presentation" id="adminInviteListMenu"><a
                href="${contextPath}/admin/enterprise/list">邀请记录</a>
        </li>
        <li role="presentation" id="adminMemberListMenu"><a
                href="${contextPath}/admin/member/list">职工信息</a>
        </li>
        <li role="presentation" id="excepMemberMenu"><a
                href="${contextPath}/admin/memberExcep/list">异常职工</a>
        </li>
    </ol>
</li>

<!--题库管理-->
<li role="presentation"><a
        href="${contextPath}/admin/tag/tree" style="">题库管理<i
        class="glyphicon glyphicon-menu-down"></i> </a>
    <ol class="nav nav-pills nav-stacked">
        <li role="presentation" id="adminTagMenu"><a
                href="${contextPath}/admin/tag/tree">标签设置</a>
        </li>

        <li role="presentation" id="adminQuestionMenu"><a
                href="${contextPath}/admin/question/list">题目列表</a>
        </li>
    </ol>
</li>


<li role="presentation"><a
        href="${contextPath }/admin/credit/memberlevel/list" class="sub-menu">积分管理<i
        class="glyphicon glyphicon-menu-down"></i> </a>
    <ol class="nav nav-pills nav-stacked">
        <li role="presentation" id="adminMemberLevelListMenu"><a
                href="${contextPath }/admin/credit/memberlevel/list" class="sub-menu">会员级别</a>
        <li role="presentation" id="adminCreditruleListMenu"><a
                href="${contextPath }/admin/credit/creditrule/list">积分规则</a>
        <li role="presentation" id="adminHandworkListMenu"><a
                href="${contextPath }/admin/credit/handwork/list">积分调整</a>
        </li>
        <li role="presentation" id="adminStreamListMenu"><a
                href="${contextPath }/admin/credit/stream/list">积分流水</a>
        </li>
        <li role="presentation" id="adminCreditRecordMenu"><a
                href="${contextPath }/admin/credit/record/query">购买记录</a>
        </li>
    </ol>
</li>
<li role="presentation"><a href="javascript:void(0);"
                           class="sub-menu">内容管理<i class="glyphicon glyphicon-menu-down"></i>
</a>
    <ol class="nav nav-pills nav-stacked">
        <li role="presentation" id="adminNewsListMenu"><a
                href="${contextPath }/admin/news/list"> 新闻管理</a>
        </li>
        <li role="presentation" id="adminPositionListMenu"><a
                href="${contextPath }/admin/position/list"> 职位管理</a>
        </li>
    </ol>
</li>
<li role="presentation"><a href="${contextPath }/admin/list"
                           class="sub-menu">系统设置<i class="glyphicon glyphicon-menu-down"></i>
</a>
    <ol class="nav nav-pills nav-stacked">
        <li role="presentation" id="adminListMenu"><a
                href="${contextPath }/admin/list">admin用户</a>
        </li>
        <li role="presentation" id="adminModifyPasswordMenu"><a
                href="${contextPath }/admin/password/modify">密码修改</a>
        </li>
        <li role="presentation" id="adminAuditMenu"><a
                href="${contextPath }/admin/audit/list">操作审计</a>
        </li>
        <li role="presentation" id="adminWxMerchantMenu"><a
                href="${contextPath }/admin/wx/merchant/list">商户设置</a>
        </li>
        <li role="presentation" id="adminProductMenu"><a
                href="${contextPath }/admin/product/list">商品中心</a>
        </li>
    </ol>
</li>

