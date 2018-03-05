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
<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ include file="../commons/taglib-header.jsp"%>

<!DOCTYPE HTML>
<html>
<link href="${contextPath}/static/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" />
<link href="${contextPath}/static/css/xsifter.css" rel="stylesheet" />
<link href="${contextPath}/static/css/dashboard.css" rel="stylesheet" />
<!-- ie8 hack ie8补充样式 -->
<!--[if lt IE 10]> 
<link href="${contextPath}/static/css/ie8.css" rel="stylesheet" />
<![endif]-->
<!--低版本ie补丁js -->
<script type="text/javascript"
	src="${contextPath}/static/js/html5shiv-3.7.0.js"></script>
<script type="text/javascript"
	src="${contextPath}/static/js/respond-1.3.0.min.js"></script>
<head>
<title>企业注册</title>

<style type="text/css">
#urlauthImg {
	border: 0;
}

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

#prov_wid,#industry_wid {
	display: block;
	width: 100%;
	height: 34px;
	padding: 6px 12px;
	font-size: 14px;
	line-height: 1.42857143;
	color: #555;
	background-color: #fff;
	background-image: none;
	border: 1px solid #ccc;
	border-radius: 4px;
}
</style>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top"
		style="background-color: #2a2f3d">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#" style="margin-top: 5px;"><img
					src="${contextPath}/static/img/logo.png"> </a>
			</div>
			<div id="indexnavbar" class="navbar-collapse collapse">
				<ul id="index" class="nav navbar-nav navbar-right">
					<li></li>
					<li><a href="#" class="active">平台新闻</a>
					</li>
					<li><a href="${contextPath}/contact_us">联系我们</a>
					</li>
					<li style="position: relative;"><input type="checkbox"
						id="login_" class="loginbtn"
						style="width: 85px; height: 50px; border: 1px solid red;position: absolute;z-index: 99;top: 0px;left:85px;opacity: 0.001">
						<input type="checkbox" id="register_" class="loginbtn"
						style="width: 85px; height: 50px; border: 1px solid red;position: absolute;z-index: 99;top: 0px;left:0px;opacity: 0.001">
						<!--注册图片--> <a href="#" class="register"
						style="float: left; display: block;"><img
							src="${contextPath}/static/img/index/login.png" class="login_img">
					</a> <!--登录图片--> <a href="#" class="login"
						style="float: left; display: none;"><img
							src="${contextPath}/static/img/index/logout.png"
							class="login_out"> </a>
					</li>
				</ul>
			</div>
		</div>
	</nav>
	<form:form commandName="formDto" style="margin-top:80px;"
		name="CompanyForm" class="form-horizontal"
		enctype='multipart/form-data' accept=".jpg">
		<div class="form-group">
			<label class="control-label col-lg-12"
				style="text-align:center;font-size:19px;">企业注册</label>
		</div>
		<div class="form-group">
			<label class="control-label col-lg-offset-1 col-lg-3">用户名<em
				class="text-danger">*</em> </label>
			<div class="col-lg-4">

				<form:hidden path="operator" value="I" />
				<form:input path="username" cssClass="form-control"
					placeholder="用户名长度2-16位" maxlength="16" required="required" />
				<form:errors path="username" cssClass="label label-warning" />
				<%--<p class="help-bolck">用户名长度2-16位</p>
				--%>
				<span id="usernameError" class="label label-warning"
					style="display:none;">用户名长度在2-16位</span> <span
					id="usernameExistError" class="label label-warning"
					style="display:none;">该用户名已经存在</span>
				<%--<label class="label label-warning" style="display: none;"
					id="username_error">用户名长度在2-16位</label>
			--%>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-lg-offset-1 col-lg-3">密码<em
				class="text-danger">*</em> </label>
			<div class="col-lg-4">
				<form:password path="password" id="pass" cssClass="form-control"
					placeholder="密码长度2-16位,至少包含一个字母" maxlength="16" required="required" />
				<form:errors path="password" cssClass="label label-warning" />
				<%--<p class="help-bolck">密码长度在2-16位</p>
				--%>
				<span id="passwordError" class="label label-warning"
					style="display:none;">密码长度在2-16位</span> <span id="passwordStrError"
					class="label label-warning" style="display:none;">密码至少包含一个字母</span>
				<%--<label class="label label-warning" style="display: none;"
					id="password_error">密码长度在2-16位</label>
				--%>
				<div class="pass-wrap">
					<em>密码强度：</em>
					<div id="passStrength"></div>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-lg-offset-1 col-lg-3">确认密码<em
				class="text-danger">*</em> </label>
			<div class="col-lg-4">
				<form:password path="rePassword" id="repassword"
					cssClass="form-control" placeholder="确定密码" maxlength="16"
					required="true" />
				<form:errors path="rePassword" cssClass="label label-warning" />
				<span id="repasswordError" class="label label-warning"
					style="display:none;">两次密码不一致</span>
				<%--<label class="label label-warning" style="display: none;"
					id="repassword_error">密码长度在2-16位</label>
				--%>
				<%--<label
					class="label label-warning" style="display: none;"
					id="repassword_two_error">两次密码不一致</label>
			--%>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-lg-offset-1 col-lg-3">企业全称<em
				class="text-danger">*</em> </label>
			<div class="col-lg-4">
				<form:input path="companyName" id="companyName"
					cssClass="form-control" placeholder="请与营业执照名称一致" maxlength="25"
					required="true" />
				<form:errors path="companyName" cssClass="label label-warning" />
				<span id="companyNameError" class="label label-warning"
					style="display:none;">企业名称长度2-25位</span> <span
					id="companyNameExistError" class="label label-warning"
					style="display:none;">企业名称已经存在</span>
				<%--<label class="label label-warning" style="display: none;"
					id="company_error">企业名称长度2-25位</label>
			--%>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-lg-offset-1 col-lg-3">企业邮箱<em
				class="text-danger">*</em> </label>
			<div class="col-lg-4">
				<form:input path="companyEmail" id="companyEmail"
					cssClass="form-control" placeholder="企业邮箱" maxlength="255"
					required="true" />
				<%--<label class="label label-warning" style="display: none;"
					id="emailLable">企业邮箱不合法</label>
				--%>
				<form:errors path="companyEmail" cssClass="label label-warning" />
				<span id="companyEmailError" class="label label-warning"
					style="display:none;">企业邮箱不合法</span> <span
					id="companyEmailExistError" class="label label-warning"
					style="display:none;">企业邮箱已经存在</span>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-lg-offset-1 col-lg-3">联系人<em
				class="text-danger">*</em> </label>
			<div class="col-lg-4">
				<form:input path="contacts" id="contacts" cssClass="form-control"
					placeholder="联系人" maxlength="15" required="true" />
				<form:errors path="contacts" cssClass="label label-warning" />
				<span id="contactsError" class="label label-warning"
					style="display:none;">联系人长度应为2-15位</span>
				<%--<label class="label label-warning" style="display: none;"
					id="contacts_error">联系人长度不合法</label>
			--%>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-lg-offset-1 col-lg-3">区域<em
				class="text-danger">*</em> </label>
			<div id="city_4" class="col-lg-4">
				<select class="prov" id="prov_wid" name="prov"></select>
				<!-- 				<select class="city" name="city" disabled="disabled" ></select> -->
				<!-- 				<select class="dist" name="dist" disabled="disabled" ></select> -->
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-lg-offset-1 col-lg-3">行业<em
				class="text-danger">*</em> </label>
			<div id="city_4" class="col-lg-4">
				<form:select path="industry" id="industry_wid">
					<%-- <c:forEach items="${indus }" var="indu">
					<form:option value="${indu }">${indu.label }</form:option>
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
		<div class="form-group">
			<label class="control-label col-lg-offset-1 col-lg-3">联系人座机或手机<em
				class="text-danger">*</em> </label>
			<div class="col-lg-4">
				<form:input path="contactsPhone" id="contactsPhone"
					cssClass="form-control" placeholder="联系人电话" maxlength="255"
					required="true" />
				<%--<label class="label label-warning" style="display: none;" id="phone">请输入正确的手机号或座机号</label>
				--%>
				<form:errors path="contactsPhone" cssClass="label label-warning" />
				<span id="contactsPhoneError" class="label label-warning"
					style="display:none;">请输入正确的手机号或座机号</span>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-lg-offset-1 col-lg-3">上传公司营业执照（副本）<em
				class="text-danger">*</em> </label>
			<div class="col-lg-4">
				<!-- 				<input type="file" accept=".jpg" name="urlbusi"  onchange="getFileSize(this);" /> -->

				<img id="tempimg" dynsrc="" src="" style="display:none" /> <input
					type="file" accept=".jpg" name="urlbusi" id="fileuploade" size="40"
					onchange="checkFileSize();" />

				<form:input path="urlbusiImg" name="urlbusiImg" id="urlbusiImg"
					cssClass="form-control" readonly="true" maxlength="255"
					required="true" style="opacity: 0" />
				<form:errors path="urlbusiImg" cssClass="label label-warning" />
				<p class="help-block">文件应是大小2M以内的jpg图片</p>
				<span id="urlbusiImg_error" class="label label-warning"
					style="display:none;">请上传&lt;=2M大小图片</span> <span
					id="urlbusiImg_null_error" class="label label-warning"
					style="display:none;">请上传公司营业执照（副本）</span> <span
					id="urlbusiImg_format_error" class="label label-warning"
					style="display:none;">文件应为.jpg图片</span>
				<%--<label class="label label-warning" style="display: none;"
					id="urlbusiImg_error">请上传&lt;=2M大小图片</label> <label
					class="label label-warning" style="display: none;"
					id="urlbusiImg_null_error">请上传公司营业执照（副本）</label>
			--%>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-lg-offset-1  col-lg-3">上传公司机构证书<em
				class="text-danger">*</em> </label>
			<div class="col-lg-4">
				<img id="tempimg2" dynsrc="" src="" style="display:none" /> <input
					type="file" accept=".jpg" name="urlauth" id="fileuploade2"
					size="40" onchange="checkFileSize2();" />
				<form:input path="urlauthImg" name="urlauthImg" id="urlauthImg"
					cssClass="form-control" readonly="true" maxlength="255"
					required="true" style="opacity: 0" />
				<form:errors path="urlauthImg" cssClass="label label-warning" />
				<p class="help-block">文件应是大小2M以内的jpg图片</p>
				<span id="urlbusiImg2_error" class="label label-warning"
					style="display:none;">请上传&lt;=2M大小图片</span> <span
					id="urlbusiImg2_null_error" class="label label-warning"
					style="display:none;">请上传公司机构证书</span> <span
					id="urlbusiImg2_format_error" class="label label-warning"
					style="display:none;">文件应为.jpg图片</span>
				<%--<label class="label label-warning" style="display: none;"
					id="urlbusiImg2_error">请上传&lt;=2M大小图片</label> <label
					class="label label-warning" style="display: none;"
					id="urlbusiImg2_null_error">请上传公司机构证书</label>
			--%>
				<label class="help-block">三证合一请上传两次!</label>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-lg-offset-1  col-lg-3"></label>
			<div class="col-lg-4">
				<div id="agreeAgreement">
					<div class="checkbox">
						<label> <input id="viewprotocol" type="checkbox">我已阅读并同意
							<a id="readProtocol" href="#">《筛子网协议》</a> <a
							href="${contextPath }/" class="agreement">回到首页</a> </label>
					</div>
				</div>

			</div>
		</div>
		<div class="form-group">
			<div class="col-lg-offset-4 col-lg-5">
				<button id="submit" class="btn btn-primary" disabled="disabled"
					type="submit">提交</button>
				<%--<c:if test="${alert eq 'saveOK'}">
					<span class="label label-info">注册成功,企业正在审核,通过后会邮件发给您!</span>
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



	<div id="myModal" class="modal fade ">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<!-- style=" width:840px; "-->

				<!-- dialog body -->
				<div class=" modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 align="center">
						<strong>用户协议</strong>
					</h4>
				</div>
				<div class=" modal-body">
					<div class="row">
						<div class="col-sm-12">
							<h5 align="left">
								<strong>筛子网网络服务会员使用协议:</strong>
							</h5>
							<div class="form-group">
								<div class="col-lg-12 col-md-12 col-xs-12">1、确认并接受协议筛子网提供的网络服务的所有权和最终解释权归筛子网所有。会员为获得网络服务，应当确认并接受本协议的全部条款。会员在注册过程中点击"我同意"按钮即表示会员完全接受本协议中的全部条款。随后按照页面给予的提示完成全部的注册步骤。</div>
							</div>

							<div class="form-group">
								<div class="col-lg-12 col-md-12 col-xs-12">2、服务内容：筛子网是服务于企业和人才的在线人力资源平台，目的是整合千万企业提供的员工从业表现(含面试,工作,离职等)，构成最全面最客观的“个人经历”，
									注册企业在同意本协议后将与筛子网自动签署和成为人力资源背景调查合同关系，背景调查合同细节如下：</div>
								<div class="col-lg-12 col-md-12 col-xs-12">&nbsp;&nbsp;2.1
									本会员保证:</div>
								<div class="col-lg-12 col-md-12 col-xs-12">&nbsp;&nbsp;&nbsp;&nbsp;本会员是在中华人民共和国境内注册且有效的法人单位,并愿意配合筛子网的进一步审核.若本会员法人单位有撤销,失效,变更等事宜,请在5个工作日内立即通知筛子网进行处理;</div>
								<div class="col-lg-12 col-md-12 col-xs-12">&nbsp;&nbsp;&nbsp;&nbsp;本会员上传的所有资料均为与工作相关的信息,不涉及任何个人隐私;</div>
								<div class="col-lg-12 col-md-12 col-xs-12">&nbsp;&nbsp;&nbsp;&nbsp;本会员所有上传的资料均真实可信;</div>
								<div class="col-lg-12 col-md-12 col-xs-12">&nbsp;&nbsp;&nbsp;&nbsp;在搜索候选人之前,本会员确认已经取得候选人同意进行工作背景调查的书面确认(本网站可下载相关表格);</div>
								<div class="col-lg-12 col-md-12 col-xs-12">&nbsp;&nbsp;&nbsp;&nbsp;本会员确认在筛子网所有进行的调查均为本会员单位工作之用,且在本会员单位内只限与此相关的管理人员知晓;</div>
								<div class="col-lg-12 col-md-12 col-xs-12">&nbsp;&nbsp;&nbsp;&nbsp;本会员保证不接受除本会员单位以外的其它机构和个人的委托使用筛子网;</div>
								<div class="col-lg-12 col-md-12 col-xs-12">&nbsp;&nbsp;&nbsp;&nbsp;本会员承认来自于本网站的查询结果所有权利(汇编,复制,翻译)属于筛子网,并保证不会进行对查询结果进行汇编,复制,修改,翻译,传播,和销售等一切商业和非商业行为;</div>
								<div class="col-lg-12 col-md-12 col-xs-12">&nbsp;&nbsp;&nbsp;&nbsp;会员不得将其帐号、密码转让或出借予其他人使用,无论出于何种目的;</div>
								<div class="col-lg-12 col-md-12 col-xs-12">&nbsp;&nbsp;&nbsp;&nbsp;以上各项,自本会员开始注册起立即生效.如有违反,本会员愿承担一切法律责任。</div>
							</div>

							<div class="form-group">
								<div class="col-lg-12 col-md-12 col-xs-12">3、协议的修改:筛子网保留在必要时对此协议中的所有条款进行随时修改的权利。一旦协议有所修改，筛子网会在会员访问筛子网网站的第一时间给予通知。</div>
							</div>

							<div class="form-group">
								<div class="col-lg-12 col-md-12 col-xs-12">4、服务的中断或终止因为以下三种情况导致服务中断或终止，筛子网无需为此承担任何责任。</div>
								<div class="col-lg-12 col-md-12 col-xs-12">&nbsp;&nbsp;4.1
									筛子网需要定期或不定期地对提供网络服务的平台或相关的设备进行检修或者维护而造成网络服务在合理时间内的中断。</div>
								<div class="col-lg-12 col-md-12 col-xs-12">&nbsp;&nbsp;4.2
									由于其他人为情况（如恶意攻击）导致系统瘫痪造成的服务中断。</div>
								<div class="col-lg-12 col-md-12 col-xs-12">&nbsp;&nbsp;4.3
									若因线路及非本公司控制范围外的硬件故障或其它不可抗力导致的服务中断。上述三种情况，筛子网会在事前尽可能及时通知会员。</div>
							</div>
							<div class="form-group">
								<div class="col-lg-12 col-md-12 col-xs-12">
									<strong>5、因黑客行为或会员的保管疏忽导致帐号、密码遭他人非法使用，因以上三种情况对您造成的损失。筛子网不承担任何责任。如会员发现其帐号遭他人非法使用，请立即和筛子网联系。</strong>
								</div>
							</div>
							<div class="form-group">
								<div class="col-lg-12 col-md-12 col-xs-12">6、帐号的封杀和保留:如果会员在使用网站使用时违反了协议内容或国家相关法律法规，筛子网有权在不提前通知用户的情况下，随时对此帐号进行有限时间的禁用，并且在禁用期间中断筛子网对此帐号的所有服务。所有会员的帐号不存在登陆的时间限制。在不违反协议内容和国家相关法律法规的前提下，筛子网会为会员永久保留每一个帐号。</div>
							</div>
							<div class="form-group">
								<div class="col-lg-12 col-md-12 col-xs-12">7、免责条款</div>
								<div class="col-lg-12 col-md-12 col-xs-12">&nbsp;&nbsp;注册企业会员应遵守本协议内容约束，自行承担因企业原因提供信息不真实和泄露得到的被调查人信息所造成的法律责任和损失。</div>
								<div class="col-lg-12 col-md-12 col-xs-12">&nbsp;&nbsp;如因不可抗力或其他无法控制的原因造成网站销售系统崩溃或无法正常使用，从而导致网上交易无法完成或丢失有关的信息、记录等，网站将不承担责任。但是我们将会尽合理的可能协助处理善后事宜，并努力使客户减少可能遭受的损失。</div>
								<div class="col-lg-12 col-md-12 col-xs-12">&nbsp;&nbsp;本网站使用者因为违反本声明的规定而触犯中华人民共和国法律的，一切后果自己负责，本网站不承担任何责任。</div>
							</div>
						</div>
					</div>
				</div>
				<!-- dialog buttons -->
				<div class="modal-footer">
					<input class="btn bg-blue-zdy" id="agree" type="button" value="我同意" />
					<button type="button" class="btn btn-danger font-color-btn"
						data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>





</body>
<script src="${contextPath}/static/js/jquery-1.12.1.min.js"></script>
<script type="text/javascript"
	src="${contextPath}/static/js/placeholder.js"></script>
<script src="${contextPath}/static/bootstrap/js/bootstrap.min.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="${contextPath}/static/js/jquery-2.0.3.m.js"></script>

<script>
	var $loginbtn = $(".loginbtn");
	$loginbtn.click(function() {
		var $this = $(this); //当前被点击的元素
		var $loginid = $this.attr("id"); //获取当前元素的attr
		var login = "login_"; //
		var register = "register_";
		if ($loginid == login) {
			if ($this.is(":checked")) {
				$(".register").hide();
				$(".login").show();
				$this.css({
					"left" : "0px"
				});
				$("#register_").css({
					"left" : "85px"
				});
				setTimeout(function() {
					window.location = "${contextPath}/login";
				}, 100)
			} else {
				$(".register").show();
				$(".login").hide();
				$this.css({
					"left" : "85px"
				});
				$("#register_").css({
					"left" : "0px"
				});
				setTimeout(function() {
					window.location = "${contextPath}/public/company/create";
				}, 100)
			}
		}
		if ($loginid == register) {
			var $position = $(this).position().left;
			if ($position == 0) {
				window.location = "${contextPath}/public/company/create";
			} else if ($position == 85) {
				window.location = "${contextPath}/login";
			}
		}
	});

	$(function() {
		new CompanyForm("${hasErrors}");
	});

	new PasswordStrength('pass', 'passStrength');
</script>
<script type="text/javascript"
	src="${contextPath }/static/js/js/jquery.cityselect.js"></script>
<script type="text/javascript">
	$(function() {

		//demo01
		$("#city_1").citySelect({
			nodata : "none",
			required : false
		});

		//demo02
		$("#city_2").citySelect({
			prov : "北京",
			nodata : "none"
		});

		//demo03
		$("#city_3").citySelect({
			prov : "湖南",
			city : "长沙"
		});

		//demo04
		$("#city_4").citySelect({
			prov : "北京",
			nodata : "none"
		});
		allinfo();
	});
</script>


</html>
