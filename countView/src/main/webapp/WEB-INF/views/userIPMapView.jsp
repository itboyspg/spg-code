<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<title>用户IP分布</title>
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
   			tags: ['0']
   		},
   		{
   			text: '按钮点击量统计结果',
   			href: '<%=basePath%>pvCtrl/toPvBtnLinkView?dataType=2',
   			tags: ['0']
   		},
   		{
   			text: '链接点击量统计结果',
   			href: '<%=basePath%>pvCtrl/toPvBtnLinkView?dataType=3',
   			tags: ['0']
   		},
   		{
   			text: '用户活跃量统计结果',
   			href: '<%=basePath%>userCtrl/queryUserActive',
   			tags: ['0']
   		},
   		{
   			text: '活跃用户地图分布',
   			href: '<%=basePath%>userCtrl/userIpMap',
   			color: '#fff', // 图标和字体颜色
   			backColor: '#428bca', // 背景颜色
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
					<div class="panel-body" id="container" style="width: 100%; height: 500px; padding: 0px;"></div>
				</div>
			</div>
		</div>
	</div>
    <!-- Heighcharts图表 -->
    <script type="text/javascript">
	    $(function () {
	    	// 此处路径为页面引入的js中定义的路径，如当前页引入的cn-all-sat-taiwan-pretty.js中定义的Highcharts.maps['']
	        var data = Highcharts.geojson(Highcharts.maps['countries/cn/custom/cn-all-sar-taiwan']),
            // Some responsiveness
            small = $('#container').width() < 400;
	
	        // Set drilldown pointers
	        $.each(data, function (i) {
	            this.drilldown = this.properties['dd-key'];
	            this.value = i; // Non-random bogus data
	        });
	
	        // Instanciate the map
	        $('#container').highcharts('Map', {
	            chart : {
	                events: {
	                    drilldown: function (e) {
	                        if (!e.seriesOptions) {
	                            var chart = this,
	                            mapKey = 'js/Highcharts/mapdata/mapjson/' + e.point.drilldown + '.geo.json',
	                            // Handle error, the timeout is cleared on success
	                            fail = setTimeout(function () {
	                            	alert(mapKey);
		                            if (!Highcharts.maps[mapKey]) {
			                            chart.showLoading('<i class="icon-frown"></i> Failed loading ' + e.point.name);
			                            fail = setTimeout(function () {
			                            	chart.hideLoading();
			                            }, 1000);
		                            }
	                            }, 3000);
	
	                            // Show the spinner
	                            chart.showLoading('<i class="icon-spinner icon-spin icon-3x"></i>'); // Font Awesome spinner
	                            // Load the drilldown map
	                            $.getJSON("<%=basePath%>" + mapKey, function (geojson) {
	                                //data = Highcharts.geojson(Highcharts.maps[mapKey]);
	                                data = Highcharts.geojson(geojson);
	
	                                // Set a non-random bogus value
	                                $.each(data, function (i) {
	                                    this.value = i;
	                                });
	
	                                // Hide loading and add series
	                                chart.hideLoading();
	                                clearTimeout(fail);
	                                chart.addSeriesAsDrilldown(e.point, {
	                                    name: e.point.name,
	                                    data: data,
	                                    dataLabels: {
	                                        enabled: true,
	                                        format: '{point.name}'
	                                    }
	                                });
	                            });
	                        }
	
	                        this.setTitle(null, { text: e.point.name });
	                    },
	                    drillup: function () {
	                        this.setTitle(null, { text: '中国' });
	                    }
	                }
	            },
	
	            title : {
	                text : '用户IP地图分布'
	            },
	
	            subtitle: {
	                text: '中国',
	                floating: true,
	                align: 'right',
	                y: 55,
	                x: -35,
	                style: {
	                    fontSize: '16px'
	                }
	            },
	
	            legend: small ? {} : {
	                layout: 'vertical',
	                align: 'right',
	                verticalAlign: 'middle'
	            },
	
	            colorAxis: {
	                min: 0,
	                minColor: '#E6E7E8',
	                maxColor: '#005645'
	            },
	
	            mapNavigation: {
	                enabled: true,
	                buttonOptions: {
	                    verticalAlign: 'bottom'
	                }
	            },
	
	            plotOptions: {
	                map: {
	                    states: {
	                        hover: {
	                            color: '#EEDD66'
	                        }
	                    }
	                }
	            },
	
	            series : [{
	                data : data,
	                name: '中国',
	                dataLabels: {
	                    enabled: true,
	                    format: '{point.properties.postal-code}'
	                }
	            }],
	
	            drilldown: {
	                //series: drilldownSeries,
	                activeDataLabelStyle: {
	                    color: '#FFFFFF',
	                    textDecoration: 'none',
	                    textShadow: '0 0 3px #000000'
	                },
	                drillUpButton: {
	                    relativeTo: 'spacingBox',
	                    position: {
	                        x: 0,
	                        y: 60
	                    }
	                }
	            }
	        });
	    });
    </script>
</body>
    <!-- Highcharts -->
    <script src="<%=basePath%>js/Highcharts/highmaps.js"></script>
    <script src="<%=basePath%>js/Highcharts/modules/data.js"></script>
    <script src="<%=basePath%>js/Highcharts/modules/drilldown.js"></script>
    <script src="<%=basePath%>js/Highcharts/mapdata/cn-all-sar-taiwan-pretty.js"></script>
</html>