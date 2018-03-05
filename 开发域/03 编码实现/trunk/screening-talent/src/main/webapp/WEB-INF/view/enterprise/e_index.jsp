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
<!DOCTYPE HTML>
<html>
<head>
<title>首页</title>
<link id="beyond-link" href="${contextPath}/static/beyond/beyond.css"
	rel="stylesheet" type="text/css" />
<style type="text/css">
.navbar {
	height: 56px;
}
</style>
</head>
<body>

	<div class="page-body">
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">

				<div class="row">
					<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
						<div class="databox bg-white radius-bordered">
							<div class="databox-left bg-themesecondary">
								<div class="databox-piechart">
									<div data-toggle="easypiechart" class="easyPieChart"
										data-barcolor="#fff" data-linecap="butt" data-percent="100"
										data-animate="500" data-linewidth="3" data-size="47"
										data-trackcolor="rgba(255,255,255,0.1)">
										<span class="white font-90"></span>
									</div>
								</div>
							</div>
							<div class="databox-right">
								<span class="databox-number themesecondary">${dto.totalMember
									}</span>
								<div class="databox-text darkgray">录入职工数</div>
								<div class="databox-stat themesecondary radius-bordered">
									<i class="stat-icon icon-lg fa fa-tasks"></i>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
						<div class="databox bg-white radius-bordered">
							<div class="databox-left bg-themethirdcolor">
								<div class="databox-piechart">
									<div data-toggle="easypiechart" class="easyPieChart"
										data-barcolor="#fff" data-linecap="butt" data-percent="100"
										data-animate="500" data-linewidth="3" data-size="47"
										data-trackcolor="rgba(255,255,255,0.2)">
										<span class="white font-90"></span>
									</div>
								</div>
							</div>
							<div class="databox-right">
								<span class="databox-number themethirdcolor">${dto.inviteCompany
									}</span>
								<div class="databox-text darkgray">邀请企业数</div>
								<div class="databox-stat themethirdcolor radius-bordered">
									<i class="stat-icon  icon-lg fa fa-envelope-o"></i>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
						<div class="databox bg-white radius-bordered">
							<div class="databox-left bg-themeprimary">
								<div class="databox-piechart">
									<div data-toggle="easypiechart" class="easyPieChart"
										data-barcolor="#fff" data-linecap="butt" data-percent="100"
										data-animate="500" data-linewidth="3" data-size="47"
										data-trackcolor="rgba(255,255,255,0.2)">
										<span class="white font-90"></span>
									</div>
								</div>
							</div>
							<div class="databox-right">
								<span class="databox-number themefourthcolor">${dto.invitedPerson
									== null ? '无' : dto.invitedPerson }</span>
								<div class="databox-text darkgray">被邀请人</div>
								<div class="databox-stat themethirdcolor radius-bordered">
									<i class="stat-icon  icon-lg fa fa-envelope-o"></i>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
						<div class="databox bg-white radius-bordered">
							<div class="databox-left bg-themefourthcolor">
								<div class="databox-piechart">
									<div data-toggle="easypiechart" class="easyPieChart"
										data-barcolor="#fff" data-linecap="butt" data-percent="100"
										data-animate="500" data-linewidth="3" data-size="47"
										data-trackcolor="rgba(255,255,255,0.2)">
										<span class="white font-90"></span>
									</div>
								</div>
							</div>
							<div class="databox-right">
								<span class="databox-number themethirdcolor">${dto.totalStationQueryAmount
									}</span>
								<div class="databox-text darkgray">全站职工查询数</div>
								<div class="databox-stat themethirdcolor radius-bordered">
									<i class="stat-icon  icon-lg fa fa-envelope-o"></i>
								</div>
							</div>
						</div>
					</div>
				</div>



				<div class="row">
					<div class="col-xs-12 col-md-12 col-lg-12">
						<div class="widget">
							<div class="widget-header ">
								<span class="widget-caption">月访问量</span>
								<div class="widget-buttons">
									<a href="#" data-toggle="maximize"> <i class="fa fa-expand"></i>
									</a> <a href="#" data-toggle="collapse"> <i class="fa fa-minus"></i>
									</a> <a href="#" data-toggle="dispose"> <i class="fa fa-times"></i>
									</a>
								</div>
							</div>
							<div class="widget-body">
								<div id="line-chart" class="chart chart-lg"></div>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>

	<script src="${contextPath}/static/skin/skins.js"></script>
	<script src="${contextPath}/static/slimscroll/jquery.slimscroll.js"></script>
	<script src="${contextPath}/static/beyond/beyond.js"></script>
	<script
		src="${contextPath}/static/charts/easypiechart/jquery.easypiechart.js"></script>
	<script
		src="${contextPath}/static/charts/easypiechart/easypiechart-init.js"></script>

	<script src="${contextPath}/static/charts/morris/raphael-2.0.2.min.js"></script>
	<script src="${contextPath}/static/charts/morris/morris.js"></script>
	<script src="${contextPath}/static/charts/morris/morris-init.js"></script>

	<script type="text/javascript">
		$(window).bind("load", function() {
			themeprimary = getThemeColorFromCss('themeprimary');
			themesecondary = getThemeColorFromCss('themesecondary');
			themethirdcolor = getThemeColorFromCss('themethirdcolor');
			themefourthcolor = getThemeColorFromCss('themefourthcolor');
			themefifthcolor = getThemeColorFromCss('themefifthcolor');

			//月访问量
			InitiateLineChartEnterprise.init();

			InitiateEasyPieChart.init();
		});
	</script>

</body>
</html>
