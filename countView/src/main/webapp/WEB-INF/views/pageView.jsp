<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>index</title>
<link href="<%=basePath%>bootstrap/css/bootstrap.min.css" rel="stylesheet">
<style type="text/css">
	#navbar-buttom button{
		margin-left: 5px;
	}
</style>
<script type="text/javascript" src="<%=basePath%>js/jQuery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="<%=basePath%>bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
	var xAxis = [];
	$(function(){
		<c:forEach var="item" items="${xAxis}">
			xAxis.push('${item}');
	 	</c:forEach>
	});
</script>
</head>
<body>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">埋点系统</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <form class="navbar-form navbar-right" role="search">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Search">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
      </form>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
<div class="container-fluid">
	<div class="row">
		<div class="col-md-3">
			<div class="list-group">
				<a href="<%=basePath %>pvCtrl/toPvView" class="list-group-item active">
					网站PV量
				</a>
				<a href="#" class="list-group-item">Dapibus ac facilisis in</a>
				<a href="#" class="list-group-item">Morbi leo risus</a>
				<a href="#" class="list-group-item">Porta ac consectetur ac</a>
				<a href="#" class="list-group-item">统计项目配置</a>
			</div>
		</div>
		<div class="col-md-9">
			<div class="panel panel-default">
				<div class="panel-body" id="pv-container" style="width: 100%; height: 500px; padding: 0px;"></div>
			</div>
		</div>
	</div>
</div>
</body>
<!-- Highcharts -->
<script src="<%=basePath%>js/Highcharts/highcharts.js"></script>
<script src="<%=basePath%>js/Highcharts/modules/exporting.js"></script>
<script type="text/javascript">
	$(function () {
	    $('#pv-container').highcharts({
	        title: {
	            text: '页面PV量统计图',
	            x: 0 //center
	        },
	        subtitle: {
	            text: '记录最近半个月各页面访问量',
	            x: 0
	        },
	        xAxis: {
	            categories: xAxis
	        },
	        yAxis: {
	            title: {
	                text: '访问量 (K)'
	            },
	            plotLines: [{
	                value: 0,
	                width: 1,
	                color: '#808080'
	            }]
	        },
	        tooltip: {
	            valueSuffix: 'K'
	        },
	        legend: {
	            layout: 'horizontal',
	            align: 'center',
	            verticalAlign: 'bottom',
	            borderWidth: 0
	        },
	        series: [
				<c:forEach varStatus="status" var="xKey" items="${xAxisKey}">
					{
						name: '${xKey.englishName}',
						data: [
							<c:forEach var="data" items="${yAxis['20160508']}">
								${data},
							</c:forEach>
						]
					},
				</c:forEach>
	        ]
	    });
	});
</script>
</html>