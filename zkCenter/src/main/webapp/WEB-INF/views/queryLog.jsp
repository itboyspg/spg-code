<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>zookeeper首页</title>
</head>
<body>
<body class="nav-md">
	<div class="container body">
		<div class="main_container">
			<jsp:include page="./menu.jsp"></jsp:include>
			<div class="right_col" role="main">
				<div class="row">
					<div class="col-md-12 col-sm-12 col-xs-12">
		                <div class="x_panel">
			            	<div class="x_title">
			                	<h2>日志查询</h2>
			                	<div class="clearfix"></div>
			                </div>
								<div class="x_content">
									<div id="alterDivId" class="alert alert-success" role="alert" style="display: none;"></div>
									<div class="row">
										<form id="queryLogForm" class="form-horizontal form-label-left" novalidate>
										<div class="item form-group">
					                        <label class="control-label col-md-4 col-sm-4 col-xs-12" for="systemName">系统名</label>
					                        <div class="col-md-6 col-sm-6 col-xs-12">
                                                <select class="form-control" id="systemName" name="systemName">
                                                    <option value="intf">intf</option>
                                                </select>
					                        </div>
					                    </div>
										<div class="item form-group">
					                        <label class="control-label col-md-4 col-sm-4 col-xs-12" for="logLevel">日志级别</label>
					                        <div class="col-md-6 col-sm-6 col-xs-12">
                                                <select class="form-control" id="logLevel" name="logLevel">
                                                    <option value="">---全部---</option>
                                                    <option value="ERROR">error</option>
                                                    <option value="INFO">info</option>
                                                    <option value="DEBUG">debug</option>
                                                    <option value="WARN">warn</option>
                                                    <option value="FATAL">fatal</option>
                                                </select>
					                        </div>
					                    </div>
										<div class="item form-group">
					                        <label class="control-label col-md-4 col-sm-4 col-xs-12" for="configName">关键字</label>
					                        <div class="col-md-6 col-sm-6 col-xs-12">
					                        	<input id="keywords" class="form-control col-md-7 col-xs-12" name="keywords" placeholder="关键字" type="text">
					                        </div>
					                    </div>
					                    <div class="item form-group" style="text-align: center;">
					                    	<button type="button" class="btn btn-primary" id="queryLogBtn">查询日志</button>
					                    </div>
					                    </form>
									</div>
			                  		<h4>查询结果</h4>
                                    <div class="clearfix"></div>
				                    <div id="queryLogResult" class="table-responsive">
                                      <table class="table table-striped jambo_table bulk_action" id="logTable">
                                      </table>
				                    </div>
			                  </div>
						</div>
					</div>
				</div>
				<br />
			</div>
		</div>
	</div>
	<script type="text/javascript" src="<%=basePath%>js/configMgr.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/commonUtil.js"></script>
	<script type="text/javascript">
		$(function(){
			$("#queryLogBtn").click(function(){
				$.ajax({
					url: "<%=basePath %>cloudLogCtrl/queryLog",
					data: $("#queryLogForm").serialize(),
					dataType: "json",
					success: function(data){
						if (data["resultCode"] == 0){
							$("#logTable").html("");
							$.each(data["resultData"], function(i, collData){
								$.each(collData, function(j, log){
	    							$("#logTable").append("<tr><td>" + JSON.stringify(log) + "</td></tr>");
								})
							});
						}else {
							alterTips("alterDivId", "error", ("日志查询失败，" + data["resultMessage"]));
						}
					},
					error: function(){
						alterTips("alterDivId", "error", "系统繁忙，查询日志失败!");
					}
				});
			});
		});
	</script>
</body>
</html>