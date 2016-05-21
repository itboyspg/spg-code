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
<link href="<%=basePath%>bootstrap/css/bootstrap-treeview.min.css" rel="stylesheet">
<style type="text/css">
	#navbar-buttom button{
		margin-left: 5px;
	}
</style>
<script type="text/javascript" src="<%=basePath%>js/jQuery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="<%=basePath%>bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>bootstrap/js/bootstrap-treeview.min.js"></script>
<script type="text/javascript">
	var menuData = [
   		{
   			text: '网站PV量统计结果',
   			href: '<%=basePath%>pvCtrl/toPvBtnLinkView?dataType=1',
			<c:if test="${strDataType eq '1'}">
	   			color: '#fff', // 图标和字体颜色
	   			backColor: '#428bca', // 背景颜色
   			</c:if>
   			tags: ['0']
   		},
   		{
   			text: '按钮点击量统计结果',
   			href: '<%=basePath%>pvCtrl/toPvBtnLinkView?dataType=2',
   			<c:if test="${strDataType eq '2'}">
	   			color: '#fff', // 图标和字体颜色
	   			backColor: '#428bca', // 背景颜色
			</c:if>
   			tags: ['0']
   		},
   		{
   			text: '链接点击量统计结果',
   			href: '<%=basePath%>pvCtrl/toPvBtnLinkView?dataType=3',
   			<c:if test="${strDataType eq '3'}">
	   			color: '#fff', // 图标和字体颜色
	   			backColor: '#428bca', // 背景颜色
			</c:if>
   			tags: ['0']
   		},
   		{
   			text: '用户活跃量统计结果',
   			href: '<%=basePath%>userCtrl/queryUserActive',
   			tags: ['0']
   		},
   		{
   			text: '统计项目配置',
   			icon: 'glyphicon glyphicon-wrench',
   			tags: ['1'],
   			nodes:[{
	   				text: 'PV配置',
	   				href: '<%=basePath%>configCtrl/toConfigView?pageName=pvConfig',
	   				icon: 'glyphicon glyphicon-cog',
	   				tags: ['0']
	   			},
	   			{
	   				text: '按钮点击配置',
	   				href: '<%=basePath%>configCtrl/toConfigView?pageName=buttonConfig',
	   				icon: 'glyphicon glyphicon-cog',
	   				tags: ['0']
	   			},
	   			{
	   				text: '链接访问配置',
	   				href: '<%=basePath%>configCtrl/toConfigView?pageName=linkConfig',
	   				icon: 'glyphicon glyphicon-cog',
	   				tags: ['0']
	   			},
				{
					text: '用户活跃配置',
					href: '#用户活跃配置',
					icon: 'glyphicon glyphicon-cog',
					tags: ['0']
				}]
   		}
   	];
	$(function(){
		// 初始化菜单
		$('#menu-tree').treeview({
			color: "#428bca",
			expandIcon: 'glyphicon glyphicon-chevron-right',
            collapseIcon: 'glyphicon glyphicon-chevron-down',
            nodeIcon: 'glyphicon glyphicon-stats',
			enableLinks: true, // 设置菜单链接可用
			selectedColor: '',
			selectedBackColor: '',
            data: menuData
        });
	});
	// x轴坐标
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
			<div id="menu-tree"></div>
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
		var title = "页面PV量统计结果";
		var subtitle = "记录最近半个月各页面访问量";
		<c:choose>
			<c:when test="${strDataType eq '1' }">
				title = "页面PV量统计结果";
				subtitle = "记录最近半个月各页面访问量";
			</c:when>
			<c:when test="${strDataType eq '2' }">
				title = "按钮点击量统计结果";
				subtitle = "记录最近半个月按钮点击量";
			</c:when>
			<c:when test="${strDataType eq '3' }">
				title = "链接访问量统计结果";
				subtitle = "记录最近半个月各链接访问量";
			</c:when>
		</c:choose>
	    $('#pv-container').highcharts({
	    	chart: {
	            type: 'spline'
	        },
	        title: {
	            text: title,
	            x: 0 //center
	        },
	        subtitle: {
	            text: subtitle,
	            x: 0
	        },
	        xAxis: {
	            categories: xAxis
	        },
	        yAxis: {
	            title: {
	                text: '访问量 (次)'
	            },
	            plotLines: [{
	                value: 0,
	                width: 1,
	                color: '#808080'
	            }]
	        },
	        tooltip: {
	            valueSuffix: '次'
	        },
	        legend: {
	            layout: 'horizontal',
	            align: 'center',
	            verticalAlign: 'bottom',
	            borderWidth: 0
	        },
	        series: [
				<c:forEach varStatus="status" var="xAxisData" items="${yAxisDatas}">
					{
						name: '${xAxisData.key}',
						data: ${xAxisData.value}
					},
				</c:forEach>
	        ]
	    });
	});
</script>
</html>