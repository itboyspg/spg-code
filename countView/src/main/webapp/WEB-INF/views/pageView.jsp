<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
<meta charset="utf-8">
<title>首页</title>
</head>
<body>
    <div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
    <script src="<%=basePath%>js/jQuery/jquery-1.11.3.min.js"></script>
    <script type="text/javascript">
    	// 图标数据初始化
    	var legend = [];
    	<c:forEach var="legend" items="${legendMap}">
     		legend.push('${legend.value}');
     	</c:forEach>
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
    	            text: '页面访问量',
    	            x: -20 //center
    	        },
    	        subtitle: {
    	            text: '每个页面日PV',
    	            x: -20
    	        },
    	        xAxis: {
    	            categories: xData
    	        },
    	        yAxis: {
    	            title: {
    	                text: '访问量'
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
    	            layout: 'vertical',
    	            align: 'right',
    	            verticalAlign: 'middle',
    	            borderWidth: 0
    	        },
    	        series: [
           	        <c:forEach varStatus="status" var="legend" items="${legendMap}">
           	        	{
           	        		name: '${legend.value}',
           	        		data:[
           	        			<c:forEach var="data" items="${viewData[legend.key]}">
   	        		      			${data},
           	        		    </c:forEach>
           	        		]
           	        	},
           	        </c:forEach>         
    	        ]
    	    });
    	});
    </script>
</body>
    <!-- Highcharts -->
    <script src="<%=basePath%>js/Highcharts/highcharts.js"></script>
    <script src="<%=basePath%>js/Highcharts/modules/exporting.js"></script>
</html>