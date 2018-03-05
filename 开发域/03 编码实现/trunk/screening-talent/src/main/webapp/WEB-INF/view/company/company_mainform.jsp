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
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../commons/taglib-header.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<title>应用账号</title>
<style type="text/css">
#passStrength {
	height: 8px;
	width: 120px;
	border: 1px solid #ccc;
}

.strengthLv1 {
	background: red;
	height: 6px;
	width: 40px;
}

.strengthLv2 {
	background: orange;
	height: 6px;
	width: 80px;
}

.strengthLv3 {
	background: green;
	height: 6px;
	width: 120px;
}
</style>
</head>
<body>
	<c:if
		test="${fun:contains(SPRING_SECURITY_CONTEXT.authentication.authorities,'[ROLE_ADMIN') }">
		<ol class="breadcrumb">
			<li><a href="${contextPath}/admin/index">首页</a></li>
			<li><a href="${contextPath }/admin/company/NoVerify/false">企业管理</a>
			</li>
			<li><a href="${contextPath }/admin/company/passVerify/false">企业列表</a>
			</li>
			<li class="active"><a href="#">添加企业</a></li>
		</ol>
	</c:if>

	<c:if
		test="${fun:contains(SPRING_SECURITY_CONTEXT.authentication.authorities,'[ROLE_ENTERPRISEADMIN') }">
		<ol class="breadcrumb">
			<li><a href="${contextPath}/enterpriseAdmin/index">首页</a></li>
			<li><a
				href="${contextPath }/enterpriseAdmin/company/NoVerify/false">企业管理</a>
			</li>
			<li><a
				href="${contextPath }/enterpriseAdmin/company/passVerify/false">企业列表</a>
			</li>
			<li class="active"><a href="#">添加企业</a></li>
		</ol>
	</c:if>

	<form:form commandName="formDto" class="form-horizontal"
		enctype='multipart/form-data' accept=".jpg">

		<div class="form-group">
			<label class="control-label col-lg-3">用户名<em
				class="text-danger">*</em> </label>
			<div class="col-lg-8">
				<form:hidden path="operator" value="A" />
				<form:input path="username" cssClass="form-control"
					placeholder="用户名" maxlength="16" required="true" />
				<form:errors path="username" cssClass="label label-warning" />
				<span id="usernameError" class="label label-warning"
					style="display:none;">用户名长度在2-16位</span> <span
					id="usernameExistError" class="label label-warning"
					style="display:none;">该用户名已经存在</span>
				<p class="help-block">用户名长度2-16位</p>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-lg-3">密码<em
				class="text-danger">*</em> </label>
			<div class="col-lg-8">
				<form:password path="password" id="pass" cssClass="form-control"
					placeholder="密码" maxlength="16" required="true" />
				<form:errors path="password" cssClass="label label-warning" />
				<span id="passwordError" class="label label-warning"
					style="display:none;">密码长度在2-16位</span> <span id="passwordStrError"
					class="label label-warning" style="display:none;">密码至少包含一个字母</span>
				<p class="help-block">密码长度2-16位,密码至少包含一个字母</p>
				<div class="pass-wrap">
					<em>密码强度：</em>
					<div id="passStrength"></div>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-lg-3">确认密码<em
				class="text-danger">*</em> </label>
			<div class="col-lg-8">
				<form:password path="rePassword" cssClass="form-control"
					placeholder="确认密码" maxlength="30" required="true" />
				<form:errors path="rePassword" cssClass="label label-warning" />
				<span id="repasswordError" class="label label-warning"
					style="display:none;">两次密码不一致</span>
				<p class="help-block">确认密码必须与密码一致</p>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-lg-3">企业全称<em
				class="text-danger">*</em> </label>
			<div class="col-lg-8">
				<form:input path="companyName" cssClass="form-control"
					placeholder="请与营业执照名称一致" maxlength="30" required="true" />
				<form:errors path="companyName" cssClass="label label-warning" />
				<span id="companyNameError" class="label label-warning"
					style="display:none;">企业名称长度2-25位</span> <span
					id="companyNameExistError" class="label label-warning"
					style="display:none;">企业名称已经存在</span>
				<p class="help-block">必需项</p>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-lg-3">企业邮箱<em
				class="text-danger">*</em> </label>
			<div class="col-lg-8">
				<form:input path="companyEmail" cssClass="form-control"
					placeholder="企业邮箱" maxlength="255" required="true" />
				<%--
			    <label class="label label-warning" style="display: none;" id="emailLable">企业邮箱不合法</label>
				--%>
				<form:errors path="companyEmail" cssClass="label label-warning" />
				<span id="companyEmailError" class="label label-warning"
					style="display:none;">企业邮箱不合法</span> <span
					id="companyEmailExistError" class="label label-warning"
					style="display:none;">企业邮箱已经存在</span>
				<p class="help-block">必需项</p>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-lg-3">联系人<em
				class="text-danger">*</em> </label>
			<div class="col-lg-8">
				<form:input path="contacts" cssClass="form-control"
					placeholder="联系人" maxlength="30" required="true" />
				<form:errors path="contacts" cssClass="label label-warning" />
				<span id="contactsError" class="label label-warning"
					style="display:none;">联系人长度应为2-15位</span>
				<p class="help-block">必需项</p>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-lg-3">联系人电话<em
				class="text-danger">*</em> </label>
			<div class="col-lg-8">
				<form:input path="contactsPhone" cssClass="form-control"
					placeholder="联系人电话" maxlength="30" required="true" />
				<form:errors path="contactsPhone" cssClass="label label-warning" />
				<span id="contactsPhoneError" class="label label-warning"
					style="display:none;">请输入正确的手机号或座机号</span>
				<p class="help-block">手机或座机均可</p>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-lg-3">区域<em
				class="text-danger">*</em> </label>
			<div id="city_4" class="col-lg-8">
				<select class="form-control prov" id="prov_wid" name="prov"
					style="width:100%;"></select>
				<p class="help-block">选择区域</p>
			</div>
		</div>

		<div class="form-group">
			<label class="control-label col-lg-3">行业<em
				class="text-danger">*</em> </label>
			<div id="city_4" class="col-lg-8">
				<form:select path="industry" id="industry_wid" style="width:100%;"
					cssClass="form-control">
					<%-- <c:forEach items="${indus }" var="indu">
						<form:option  cssClass="form-control" value="${indu }">${indu.label }</form:option>
					</c:forEach> --%>
					<form:option value="计算机|互联网|通信|电子">计算机|互联网|通信|电子</form:option>
					<form:option value="销售|客服|技术支持">销售|客服|技术支持</form:option>
					<form:option value="会计|金融|银行|保险">会计|金融|银行|保险</form:option>
					<form:option value="生产|营运|采购|物流">生产|营运|采购|物流</form:option>
					<form:option value="生物|制药|医疗|护理">生物|制药|医疗|护理</form:option>
					<form:option value="广告|市场|媒体艺术">广告|市场|媒体艺术</form:option>
					<form:option value="建筑|房地产">建筑|房地产</form:option>
					<form:option value="人事|行政|高级管理">人事|行政|高级管理</form:option>
					<form:option value="咨询|法律|教育|科研">咨询|法律|教育|科研</form:option>
					<form:option value="服务业">服务业</form:option>
					<form:option value="公务员|翻译|其他">公务员|翻译|其他</form:option>
				</form:select>
			</div>
		</div>
		<div class="form-group ">
			<label class="control-label col-lg-3">上传公司营业执照（副本）<em
				class="text-danger">*</em> </label>
			<div class="col-lg-8">
				<input type="file" accept=".jpg" name="urlbusi" id="fileuploade"
					size="40" onchange="checkFileSize();" />

				<form:input path="urlbusiImg" name="urlbusiImg" id="urlbusiImg"
					cssClass="form-control" readonly="true" maxlength="255"
					required="true" style="opacity: 0" />
				<form:errors path="urlbusiImg" cssClass="label label-warning" />
				<p class="help-block">jpg图片,&lt;=2MB</p>
				<span id="urlbusiImg_error" class="label label-warning"
					style="display:none;">请上传&lt;=2M大小图片</span> <span
					id="urlbusiImg_null_error" class="label label-warning"
					style="display:none;">请上传公司营业执照（副本）</span> <span
					id="urlbusiImg_format_error" class="label label-warning"
					style="display:none;">文件应为.jpg图片</span>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label  col-lg-3">上传公司机构证书<em
				class="text-danger">*</em> </label>
			<div class="col-lg-8">
				<input type="file" accept=".jpg" name="urlauth" id="fileuploade2"
					size="40" onchange="checkFileSize2();" />
				<form:input path="urlauthImg" name="urlauthImg" id="urlauthImg"
					cssClass="form-control" readonly="true" maxlength="255"
					required="true" style="opacity: 0" />
				<form:errors path="urlauthImg" cssClass="label label-warning" />
				<p class="help-block">jpg图片,&lt;=2MB</p>
				<span id="urlbusiImg2_error" class="label label-warning"
					style="display:none;">请上传&lt;=2M大小图片</span> <span
					id="urlbusiImg2_null_error" class="label label-warning"
					style="display:none;">请上传公司机构证书</span> <span
					id="urlbusiImg2_format_error" class="label label-warning"
					style="display:none;">文件应为.jpg图片</span>
			</div>
		</div>
		<div class="form-group">
			<div class="col-lg-offset-3 col-lg-3">
				<button id="submit" class="btn btn-primary" type="submit">保存</button>
				&nbsp;<a id="backBtn" href="../NoVerify/false">返回</a>
				<%--<c:if test="${alert eq 'saveOK'}">
					<span class="label label-info">保存成功!</span>
				</c:if>
				<c:if test="${alert eq 'Notimg'}">
					<span class="label label-info">必须上传证书或营业执照!</span>
				</c:if>
				<c:if test="${alert eq 'saveError'}">
					<span class="label label-info">保存异常!</span>
				</c:if>
				<c:if test="${alert eq 'companyExits'}">
					<span class="label label-info">公司名称已存在!</span>
				</c:if>
				<c:if test="${alert eq 'usernameExits'}">
					<span class="label label-info">用户名已存在!</span>
				</c:if>
			--%>
			</div>
		</div>
	</form:form>
	<script type="text/javascript"
		src="${contextPath}/static/js/jquery-2.0.3.m.js"></script>
	<script type="text/javascript">
		$(function() {
			new CompanyForm("Y");
			new City();
			new PasswordStrength('pass', 'passStrength');

			var href = location.pathname;
			if (href.indexOf("/company/create/verify") > 0) {
				$('#backBtn').attr('href', '../NoVerify/false');
			}

			if (href.indexOf("/company/create/pass") > 0) {
				$('#backBtn').attr('href', '../passVerify/true');
			}

		});
	</script>
</body>
</html>
