<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../commons/taglib-header.jsp"%>
<html>
<head>
<title>二维码</title>
</head>
<body>

	<ol class="breadcrumb">
		<li><a href="${contextPath}/enterprise/index">首页</a></li>
	</ol>

	<br />

	<form:form commandName="formDto" cssClass="form-horizontal">
		<div class="form-group">
			<label for="content" class="col-sm-2 control-label">扫码支付</label>
			<div class="col-sm-10">
				<img alt="QR" src="${contextPath}/share/payQR"
					style="width:300px;height:300px;">
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-2"></div>
			<div class="col-sm-10">
				<a href="../creditbuy">返回</a> &nbsp; 
			</div>
		</div>
	</form:form>
</body>
</html>
