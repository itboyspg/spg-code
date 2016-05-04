<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>活跃用户量</title>
</head>
<body>
    <div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
    <script src="<%=basePath%>js/jQuery/jquery-1.11.3.min.js"></script>
    <script type="text/javascript">
    	// 图标数据初始化
     	var xData = [];
        <c:forEach var="data" items="${xData}">
			xData.push('${data}');
   		</c:forEach>
    </script>
    <!-- Heighcharts图表 -->
    <script type="text/javascript">
    	$(function(){
    	    $('#container').highcharts({
    	        title: {
    	            text: '活跃用户',
    	            x: -20 //center
    	        },
    	        subtitle: {
    	            text: '每天活跃用户量',
    	            x: -20
    	        },
    	        xAxis: {
    	            categories: xData
    	        },
    	        yAxis: {
    	            title: {
    	                text: '活跃量'
    	            },
    	            plotLines: [{
    	                value: 0,
    	                width: 1,
    	                color: '#808080'
    	            }]
    	        },
    	        tooltip: {
    	            valueSuffix: '个'
    	        },
    	        legend: {
    	            layout: 'vertical',
    	            align: 'right',
    	            verticalAlign: 'middle',
    	            borderWidth: 0
    	        },
    	        series: [{
   	        		name: '活跃用户',
   	        		data:[
						<c:forEach varStatus="status" var="resultData" items="${resultData}">
							${resultData},
						</c:forEach>
   	        		]
    	        }]
    	    });
    	});
    </script>
</body>
    <!-- Highcharts -->
    <script src="<%=basePath%>js/Highcharts/highcharts.js"></script>
    <script src="<%=basePath%>js/Highcharts/modules/exporting.js"></script>
</html>