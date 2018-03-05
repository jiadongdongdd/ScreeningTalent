<%--
 * 
 * @author Shengzhao Li
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>首页</title>
</head>
<body>

<ol class="breadcrumb">
    <li><a href="#">首页</a></li>
    <li><a href="#">企业管理</a></li>
    <li class="active">待审核</li>
</ol>
<div class="row">
    <div class="col-lg-12 col-sm-12">
        <div class="pull-right">
            <a href="application_new.html" class="btn btn-success">新增</a>
            <a href="application_new.html" class="btn label-danger">审核</a>
        </div>
        <form action="" class="form-inline" id="filterForm">
            <div class="form-group col-lg-4">
                <input type="search" class="form-control col-lg-12" placeholder="请输入您要搜索的内容" name="appName" value=""/>
                <i class="glyphicon glyphicon-search"></i>
            </div>
        </form>
    </div>
</div>
<div class="table-responsive">
    <table class="table table-striped">
        <thead>
        <tr>
            <th></th>
            <th>序号</th>
            <th>企业ID</th>
            <th>企业名称</th>
            <th>联系人</th>
            <th>联系人电话</th>
            <th>联系人邮箱</th>
            <th>审核状态</th>
            <th>积分</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><label><input type="checkbox"></label></td>
            <td>1</td>
            <td>0001</td>
            <td>九州云腾</td>
            <td>张程</td>
            <td>13312343210</td>
            <td>andy@123.com</td>
            <td>未审核</td>
            <td>0</td>
            <td>查看</td>
        </tr>
        <tr>
            <td><label><input type="checkbox"></label></td>
            <td>1</td>
            <td>0001</td>
            <td>阿里巴巴</td>
            <td>马云</td>
            <td>13312343210</td>
            <td>andy@123.com</td>
            <td>未审核</td>
            <td>0</td>
            <td>查看</td>
        </tr>
        <tr>
            <td><label><input type="checkbox"></label></td>
            <td>1</td>
            <td>0001</td>
            <td>九州云腾</td>
            <td>张程</td>
            <td>13312343210</td>
            <td>andy@123.com</td>
            <td>未审核</td>
            <td>0</td>
            <td>查看</td>
        </tr>
        <tr>
            <td><label><input type="checkbox"></label></td>
            <td>1</td>
            <td>0001</td>
            <td>九州云腾</td>
            <td>张程</td>
            <td>13312343210</td>
            <td>andy@123.com</td>
            <td>未审核</td>
            <td>0</td>
            <td>查看</td>
        </tr>
        <tr>
            <td><label><input type="checkbox"></label></td>
            <td>1</td>
            <td>0001</td>
            <td>九州云腾</td>
            <td>张程</td>
            <td>13312343210</td>
            <td>andy@123.com</td>
            <td>未审核</td>
            <td>0</td>
            <td>查看</td>
        </tr>
        <tr>
            <td><label><input type="checkbox"></label></td>
            <td>1</td>
            <td>0001</td>
            <td>九州云腾</td>
            <td>张程</td>
            <td>13312343210</td>
            <td>andy@123.com</td>
            <td>未审核</td>
            <td>0</td>
            <td>查看</td>
        </tr>
        </tbody>
    </table>
</div>

</body>
</html>
