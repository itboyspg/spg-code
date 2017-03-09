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
			                	<h2>系统配置项管理</h2>
			                	<div class="clearfix"></div>
			                </div>
								<div class="x_content">
									<div id="alterDivId" class="alert alert-success" role="alert" style="display: none;"></div>
									<div class="row">
										<form class="form-horizontal form-label-left" novalidate>
										<div class="item form-group">
					                        <label class="control-label col-md-4 col-sm-4 col-xs-12" for="systemName">系统名</label>
					                        <div class="col-md-6 col-sm-6 col-xs-12">
                                                <select class="form-control" id="systemName" name="systemName">
                                                  <c:forEach items="${accessSystem }" var="system">
                                                    <option value="${system.englishName }">${system.chineseName }[${system.englishName }]</option>
                                                  </c:forEach>
                                                </select>
					                        </div>
					                    </div>
										<div class="item form-group">
					                        <label class="control-label col-md-4 col-sm-4 col-xs-12" for="configName">配置项</label>
					                        <div class="col-md-6 col-sm-6 col-xs-12">
					                        	<input id="configName" class="form-control col-md-7 col-xs-12" name="configName" placeholder="配置项key" type="text">
					                        </div>
					                    </div>
					                    <div class="item form-group" style="text-align: center;">
					                    	<button type="button" class="btn btn-primary" id="queryConfigBtn">查询配置信息</button>
					                    	<button type="button" class="btn btn-success" id="addConfigBtn">添加配置信息</button>
					                    </div>
					                    </form>
									</div>
			                  		<p>各系统详细配置项列表</p>
				                    <div class="table-responsive">
										<table class="table table-striped jambo_table bulk_action" id="configDataList">
											<thead>
												<tr class="headings">
													<th>
													  <input type="checkbox" id="check-all">
													</th>
													<th class="column-title">系统名</th>
													<th class="column-title">配置项</th>
													<th class="column-title">配置项值</th>
													<th class="column-title">备注</th>
													<th class="column-title no-link last">操作</th>
												</tr>
											</thead>
											<tbody id="configDataTable">
												<c:forEach items="${configs }" var="config">
													<tr class='even pointer'>
														<td class='a-center'><input type='checkbox'></td>
														<td>${config.systemName }</td>
														<td>${config.configName }</td>
														<td>${config.configValue }</td>
														<td>${config.remark }</td>
														<td>
															<button name='updateConfig' type='button' class='btn btn-warning btn-xs'>修改</button>
															<button name='deleteConfig' type='button' class='btn btn-danger btn-xs'>删除</button>
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
				                    </div>
			                  </div>
						</div>
					</div>
				</div>
				<br />
			</div>
			<!-- modal start -->
			<div class="modal fade" id="addConfigModal">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
							<h4 class="modal-title">添加配置项</h4>
						</div>
						<div class="modal-body">
                            <div id="addModalAlterId" class="alert alert-success" role="alert" style="display: none;"></div>
							<form id="addConfigForm" class="form-horizontal form-label-left">
								<div class="item form-group">
			                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="systemName">系统名</label>
			                        <div class="col-md-8 col-sm-8 col-xs-12">
                                        <select class="form-control" name="systemName">
                                          <option>请选择</option>
                                          <c:forEach items="${accessSystem }" var="system">
                                            <option value="${system.englishName }">${system.chineseName }[${system.englishName }]</option>
                                          </c:forEach>
                                        </select>
			                        </div>
			                    </div>
								<div class="item form-group">
			                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="configName">配置项</label>
			                        <div class="col-md-8 col-sm-8 col-xs-12">
			                        	<input class="form-control col-md-7 col-xs-12" name="configName" placeholder="配置项key" type="text">
			                        </div>
			                    </div>
								<div class="item form-group">
			                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="configValue">配置值</label>
			                        <div class="col-md-8 col-sm-8 col-xs-12">
			                        	<input class="form-control col-md-7 col-xs-12" name="configValue" placeholder="配置值" type="text">
			                        </div>
			                    </div>
								<div class="item form-group">
			                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="remark">备注</label>
			                        <div class="col-md-8 col-sm-8 col-xs-12">
			                        	<textarea class="form-control col-md-7 col-xs-12" name="remark" placeholder="备注"></textarea>
			                        </div>
			                    </div>
							</form>
						</div>
						<div class="modal-footer" style="text-align: center;">
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
							<button type="button" class="btn btn-primary" name="saveConfigBtn">保存</button>
						</div>
					</div><!-- /.modal-content -->
				</div><!-- /.modal-dialog -->
			</div><!-- /.modal -->
			<div class="modal fade" id="updateConfigModal">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
							<h4 class="modal-title">添加配置项</h4>
						</div>
						<div class="modal-body">
                            <div id="updateModalAlterId" class="alert alert-success" role="alert" style="display: none;"></div>
							<form id="updateConfigForm" class="form-horizontal form-label-left">
								<div class="item form-group">
			                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="systemName">系统名</label>
			                        <div class="col-md-8 col-sm-8 col-xs-12">
			                        	<input class="form-control col-md-7 col-xs-12" name="systemName" placeholder="系统名" type="text" readonly="readonly">
			                        </div>
			                    </div>
								<div class="item form-group">
			                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="configName">配置项</label>
			                        <div class="col-md-8 col-sm-8 col-xs-12">
			                        	<input class="form-control col-md-7 col-xs-12" name="configName" placeholder="配置项key" type="text" readonly="readonly">
			                        </div>
			                    </div>
								<div class="item form-group">
			                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="configValue">配置值</label>
			                        <div class="col-md-8 col-sm-8 col-xs-12">
			                        	<input class="form-control col-md-7 col-xs-12" name="configValue" placeholder="配置值" type="text">
			                        </div>
			                    </div>
								<div class="item form-group">
			                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="remark">备注</label>
			                        <div class="col-md-8 col-sm-8 col-xs-12">
			                        	<textarea class="form-control col-md-7 col-xs-12" name="remark" placeholder="备注"></textarea>
			                        </div>
			                    </div>
							</form>
						</div>
						<div class="modal-footer" style="text-align: center;">
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
							<button type="button" class="btn btn-primary" name="saveConfigBtn">保存</button>
						</div>
					</div><!-- /.modal-content -->
				</div><!-- /.modal-dialog -->
			</div><!-- /.modal -->
			<div class="modal fade" id="confirmDeleteModal">
				<div class="modal-dialog modal-sm">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
							</button>
							<h4 class="modal-title">删除配置项</h4>
						</div>
						<div class="modal-body">
							<form id="deleteConfigForm" class="form-horizontal form-label-left">
								<input type="hidden" name="systemName">
								<input type="hidden" name="configName">
								确定删除当前配置信息？
							</form>
						</div>
						<div class="modal-footer" style="text-align: center;">
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
							<button type="button" class="btn btn-primary" name="confirmDeleteBtn">确定删除</button>
						</div>
					</div><!-- /.modal-content -->
				</div><!-- /.modal-dialog -->
			</div><!-- /.modal -->
		</div>
	</div>
	<script type="text/javascript" src="<%=basePath%>js/configMgr.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/commonUtil.js"></script>
	<script type="text/javascript">
		$(function(){
			//queryConfig("", "", "configDataTable");
			// 添加配置项modal框
			$("#addConfigBtn").click(function(){
				$("#addConfigModal").modal("toggle");
			});
			// 添加配置信息提交
			$("#addConfigModal button[name='saveConfigBtn']").click(function(){
				var sysName = $("#addConfigForm select[name='systemName']").val();
				var configName = $("#addConfigForm input[name='configName']").val();
				var configValue = $("#addConfigForm input[name='configValue']").val();
				if (sysName === '' || configName === "" || configValue === "") {
					alterTips("addModalAlterId", "error", "系统名称、配置项名称、配置项值必须输入！");
					return;
				}
				if (!regCheck(alphabetNumOutlintPoint, configName)) {
					alterTips("addModalAlterId", "error", "配置项名只允许输入英文、数字、下划线或英文点号！");
					return;
				}
				$.ajax({
					url: "<%=basePath %>configCtrl/addConfig",
					data: $("#addConfigForm").serialize(),
					dataType: "json",
					success: function(data){
						if (data["resultCode"] == 0){
							$("#addConfigModal").modal("toggle");
							$("#queryConfigBtn").trigger("click");
							cleanModalData("addConfigModal");
							alterTips("alterDivId", "success", "配置项添加成功！");
						}else {
							alterTips("alterDivId", "error", ("配置项添加失败，" + data["resultMessage"]));
						}
					},
					error: function(){
						alterTips("alterDivId", "error", "系统繁忙，配置项添加失败!");
					}
				});
			});
			// 修改配置项
// 			$(document).on("click", "#configDataList button[name='updateConfig']", function(){
// 				$("#updateConfigModal").modal("toggle");
// 			});
			// delegate 事件委托；
			$("#configDataList").delegate("button[name='updateConfig']", "click", function(){
				// 将数据带到modal框
				var sysName = $(this).parent().parent().find("td").eq(1).text();
				var configName = $(this).parent().parent().find("td").eq(2).text();
				var configValue = $(this).parent().parent().find("td").eq(3).text();
				var remark = $(this).parent().parent().find("td").eq(4).text();
				$("#updateConfigModal input[name='systemName']").val(sysName);
				$("#updateConfigModal input[name='configName']").val(configName);
				$("#updateConfigModal input[name='configValue']").val(configValue);
				$("#updateConfigModal textarea[name='remark']").val(remark);
				$("#updateConfigModal").modal("toggle");
			});
			// 修改配置信息提交
			$("#updateConfigModal button[name='saveConfigBtn']").click(function(){
				$.ajax({
					url: "<%=basePath %>configCtrl/updateConfig",
					data: $("#updateConfigForm").serialize(),
					dataType: "json",
					success: function(data){
						if (data["resultCode"] == 0){
							$("#updateConfigModal").modal("toggle");
							$("#queryConfigBtn").trigger("click");
							cleanModalData("updateConfigModal");
							alterTips( "alterDivId","success", "配置项修改成功!");
						}else {
							alterTips( "alterDivId","error", ("配置项修改失败，" + data["resultMessage"]));
						}
					},
					error: function(){
						alterTips( "alterDivId","error", "系统繁忙，配置项修改失败!");
					}
				});
			});
			$("#configDataList").delegate("button[name='deleteConfig']", "click", function(){
				var sysName = $(this).parent().parent().find("td").eq(1).text();
				var configName = $(this).parent().parent().find("td").eq(2).text();
				$("#confirmDeleteModal input[name='systemName']").val(sysName);
				$("#confirmDeleteModal input[name='configName']").val(configName);
				$("#confirmDeleteModal").modal("toggle");
			});
			$("#confirmDeleteModal button[name='confirmDeleteBtn']").click(function(){
				$.ajax({
					url: "<%=basePath %>configCtrl/deleteConfig",
					data: $("#deleteConfigForm").serialize(),
					dataType: "json",
					success: function(data){
						if (data["resultCode"] == 0){
							$("#confirmDeleteModal").modal("toggle");
							$("#queryConfigBtn").trigger("click");
							alterTips( "alterDivId","success", "配置项删除成功!");
						}else {
							alterTips( "alterDivId","error", ("配置项删除失败，" + data["resultMessage"]));
						}
					},
					error: function(){
						alterTips( "alterDivId","error", "系统繁忙，配置项删除失败!");
					}
				});
			});
			// 查询配置列表信息
			$("#queryConfigBtn").click(function(){
				var systemName = $("#systemName").val();
				var configName = $("#configName").val();
				queryConfig(systemName, configName, "configDataTable");
			});
		});
	</script>
</body>
</html>