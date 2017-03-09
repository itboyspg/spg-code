<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>配置项系统接入</title>
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
                <h2>系统接入配置</h2>
                <div class="clearfix"></div>
              </div>
              <div class="x_content">
                <div id="alterDivId" class="alert alert-success" role="alert" style="display: none;"></div>
                <div class="row">
                  <form class="form-horizontal form-label-left" novalidate>
                    <div class="item form-group">
                      <label class="control-label col-md-4 col-sm-4 col-xs-12" for="englishName">系统英文名</label>
                      <div class="col-md-6 col-sm-6 col-xs-12">
                        <input id="englishName" class="form-control col-md-7 col-xs-12" name="englishName" placeholder="系统英文名" type="text">
                      </div>
                    </div>
                    <div class="item form-group" style="text-align: center;">
                      <button type="button" class="btn btn-primary" id="querySystemBtn">查询</button>
                      <button type="button" class="btn btn-success" id="addNewSystemBtn">新系统接入</button>
                    </div>
                  </form>
                </div>
                <p>接入系统详细列表</p>
                <div class="table-responsive">
                  <table class="table table-striped jambo_table bulk_action" id="accessSystemDataList">
                    <thead>
                      <tr class="headings">
                        <th><input type="checkbox" id="check-all" class="flat"></th>
                        <th class="column-title">系统中文名</th>
                        <th class="column-title">系统英文名</th>
                        <th class="column-title">备注</th>
                        <th class="column-title no-link last">操作</th>
                      </tr>
                    </thead>
                    <tbody id="accessSystemDataTable">
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
      <div class="modal fade" id="addNewSystemModal">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal">
                <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
              </button>
              <h4 class="modal-title">新系统接入</h4>
            </div>
            <div class="modal-body">
              <div id="modalAlterId" class="alert alert-success" role="alert" style="display: none;"></div>
              <form id="addSystemForm" class="form-horizontal form-label-left">
                <div class="item form-group">
                  <label class="control-label col-md-3 col-sm-3 col-xs-12" for="chineseName">系统中文名</label>
                  <div class="col-md-8 col-sm-8 col-xs-12">
                    <input class="form-control col-md-7 col-xs-12" name="chineseName" placeholder="系统中文名" type="text">
                  </div>
                </div>
                <div class="item form-group">
                  <label class="control-label col-md-3 col-sm-3 col-xs-12" for="englishName">系统英文名</label>
                  <div class="col-md-8 col-sm-8 col-xs-12">
                    <input class="form-control col-md-7 col-xs-12" name="englishName" placeholder="系统英文名" type="text">
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
              <button type="button" class="btn btn-primary" name="saveNewSystemBtn">保存</button>
            </div>
          </div>
          <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
      </div>
      <!-- /.modal -->
      <!-- /.modal -->
      <div class="modal fade" id="confirmDeleteModal">
        <div class="modal-dialog modal-sm">
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal">
                <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
              </button>
              <h4 class="modal-title">删除接入系统</h4>
            </div>
            <div class="modal-body">
              <form id="deleteConfigForm" class="form-horizontal form-label-left">
                <input type="hidden" name="englishName">
                                警告！如果删除当前所接入的系统，同时会删除当前系统下所有配置项！！！
              </form>
            </div>
            <div class="modal-footer" style="text-align: center;">
              <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
              <button type="button" class="btn btn-primary" name="confirmDeleteBtn">确定删除</button>
            </div>
          </div>
          <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
      </div>
      <!-- /.modal -->
    </div>
  </div>
  <script type="text/javascript" src="<%=basePath%>js/configMgr.js"></script>
  <script type="text/javascript" src="<%=basePath%>js/commonUtil.js"></script>
  <script type="text/javascript">
  $(function() {
	queryAccessSystem(null, "accessSystemDataTable");
	$("#addNewSystemBtn").click(function(){
		$("#addNewSystemModal").modal("toggle");
  	});
	// 提交保存新系统
	$("#addNewSystemModal button[name='saveNewSystemBtn']").click(function(){
		var chineseName = $("#addSystemForm input[name='chineseName']").val();
		var englishName = $("#addSystemForm input[name='englishName']").val();
		console.log(chineseName);
		if (chineseName === "" || englishName === "") {
			alterTips("modalAlterId", "error", "请输入系统中文名及英文名！");
			return;
		}
		if (!checkChinese(chineseName)) {
			alterTips("modalAlterId", "error", "系统中文名只允许输入中文信息！");
			return;
		}
		if (!regCheck(alphabetNumOutlintPoint, englishName)) {
			alterTips("modalAlterId", "error", "系统英文名只允许输入英文、数字、下划线！");
			return;
		}
		$.ajax({
			url: "<%=basePath%>newSystemCtrl/addAccessSystem",
			data: $("#addSystemForm").serialize(),
			dataType: "json",
			type: "POST",
			success: function(data){
				if (data["resultCode"] == 0){
					$("#addNewSystemModal").modal("toggle");
					$("#querySystemBtn").trigger("click");
					cleanModalData("addNewSystemModal");
					alterTips("alterDivId", "success", "新系统配置成功！");
				}else {
					alterTips("modalAlterId", "error", ("新系统配置失败，" + data["resultMessage"]));
				}
			},
			error: function(){
				alterTips("modalAlterId", "error", "系统繁忙，新系统配置项添加失败!");
			}
		});
	});
	// 查询按钮
	$("#querySystemBtn").click(function(){
		var englishName = $("#englishName").val();
		queryAccessSystem(englishName, "accessSystemDataTable");
	});
	// 删除接入系统按钮
	$("#accessSystemDataTable").delegate("button[name='deleteAccessSystem']", "click", function(){
		$("#confirmDeleteModal").modal('toggle');
		var englishName = $(this).parent().parent().find("td").eq(2).text();
		$("#confirmDeleteModal input[name='englishName']").val(englishName);
	});
	$("#confirmDeleteModal button[name='confirmDeleteBtn']").click(function(){
		$.ajax({
			url: "<%=basePath %>newSystemCtrl/deleteAccessSystem",
			data: $("#deleteConfigForm").serialize(),
			dataType: "json",
			type: "POST",
			success: function(data){
				if (data["resultCode"] == 0){
					$("#confirmDeleteModal").modal("toggle");
					$("#querySystemBtn").trigger("click");
					alterTips( "alterDivId","success", "接入系统删除成功!");
				}else {
					alterTips( "alterDivId","error", ("接入系统删除失败，" + data["resultMessage"]));
				}
			},
			error: function(){
				alterTips( "alterDivId","error", "系统繁忙，接入系统删除失败!");
			}
		});
	});
  });
  </script>
</body>
</html>