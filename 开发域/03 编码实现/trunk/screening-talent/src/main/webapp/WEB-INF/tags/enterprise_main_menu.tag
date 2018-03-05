<%@tag pageEncoding="UTF-8"%>
<li role="presentation"><a
	href="${contextPath}/enterprise/company/detail" style="">企业管理<i
		class="glyphicon glyphicon-menu-down"></i> </a>
	<ol class="nav nav-pills nav-stacked">
		<li class="presentation" id="enterpriseCompanyDetailMenu"><a
			href="${contextPath}/enterprise/company/detail">企业详情</a>
		</li>
		<li role="presentation" id="enterpriseMemberMenu"><a
			href="${contextPath}/enterprise/member/list">职工信息</a>
		</li>
		<li role="presentation" id="enterpriseMemberQueryMenu"><a
			href="${contextPath}/enterprise/member/query_list">全站搜索</a>
		</li>
		<li role="presentation" id="enterpriseMemberQueryHistoryMenu"><a
			href="${contextPath}/enterprise/member/query/history">搜索历史</a>
		</li>
		<li class="presentation" id="enterpriseInviteListMenu"><a
			href="${contextPath}/enterprise/invite/List">邀请记录</a>
		</li>
	</ol></li>
<li role="presentation"><a
	href="${contextPath}/enterprise/company/stream" class="sub-menu">积分管理<i
		class="glyphicon glyphicon-menu-down"></i> </a>
	<ol class="nav nav-pills nav-stacked">
		<li role="presentation" id="enterpriseCompanyStreamMenu"><a
			href="${contextPath}/enterprise/company/stream">积分流水</a></li>
		<li role="presentation" id="enterpriseBuyCreditMenu"><a
			href="${contextPath}/enterprise/company/creditbuy">购买积分</a></li>
		<li role="presentation" id="enterpriseCreditQueryMenu"><a
			href="${contextPath}/enterprise/company/credit/query">充值记录</a></li>
	</ol></li>

<li role="presentation"><a
	href="${contextPath}/enterprise/company/modify_password"
	class="sub-menu">系统设置<i class="glyphicon glyphicon-menu-down"></i>
</a>
	<ol class="nav nav-pills nav-stacked">
		<li role="presentation" id="enterpriseCompanyModifyPasswordMenu"><a
			href="${contextPath}/enterprise/company/modify_password">密码修改</a></li>
		<li role="presentation" id="enterpriseAuditMenu"><a
			href="${contextPath}/enterprise/audit/list">操作审计</a></li>
	</ol></li>

