<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<title>按钮点击统计配置</title>
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
		   			color: '#fff', // 图标和字体颜色
		   			backColor: '#428bca', // 背景颜色
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
		reloadData(true);
	});
	// 重新加载页面数据
	function reloadData(deleteOldData){
		$.ajax({
			url: "<%=basePath%>configCtrl/queryConfig",
			type: "POST",
			asyn: true,
			dataType: "json",
			data: {"dataType": "2"},
			beforeSend: function(){
				// 是否删除现有表格数据
				if (deleteOldData && $("#pvConfigTable tbody tr").length > 1){
					$("#pvConfigTable tbody tr:gt(0)").html("");
				}
			},
			success: function(returnData){
				// 拼接table中tr数据
				var trs = "";
				$.each(returnData["resultData"], function(i, data){
					trs += "<tr>";
					trs += "<td><input type='checkbox' name='checkOne'></td>";
					trs += "<td>" + (i + 1) + "</td>";
					trs += "<td>" + data['englishName'] + "</td>";
					trs += "<td>" + data['chineseDescription'] + "</td>";
					trs += "</tr>";
				});
				$("#pvConfigTable tr:first").parent().append(trs);
			},
			error: function(error){
				$("#error-waring-body").html("不好意思，系统太忙啦，请等会儿再试试吧！");
				$('#error-warning-modal').modal('show');
			}
		});
	}
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
			<table class="table table-hover" id="pvConfigTable">
				<tr>
					<th>
						<input type="checkbox" id="check-all">选择
					</th>
					<th>序号</th>
					<th>按钮英文名</th>
					<th>按钮中文名</th>
				</tr>
			</table>
			<nav class="navbar navbar-default">
			  <div class="container-fluid">
			    <div class="collapse navbar-collapse" id="navbar-buttom">
					<button id="update-btn" type="button" class="btn btn-primary btn-sm navbar-btn navbar-right" data-toggle="modal">
						<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
					</button>
					<button id="delete-btn" type="button" class="btn btn-primary btn-sm navbar-btn navbar-right" data-toggle="modal">
						<span class="glyphicon glyphicon-minus" aria-hidden="true"></span>删除
					</button>
					<button id="add-btn" type="button" class="btn btn-primary btn-sm navbar-btn navbar-right" data-toggle="modal" data-target="#add-config-model">
						<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>增加
					</button>
			    </div><!-- /.navbar-collapse -->
			  </div><!-- /.container-fluid -->
			</nav>
		</div>
	</div>
</div>
<!-- 页面所有modal框start -->
<!-- 添加配置弹出框Modal -->
<div class="modal fade" id="add-config-model" tabindex="-1" role="dialog" aria-labelledby="addModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="addModalLabel">新增按钮统计项</h4>
      </div>
      <div class="modal-body">
        <form>
          <div class="form-group">
            <label for="recipient-name" class="control-label">按钮英文名:</label>
            <input type="text" class="form-control" id="add-english-name">
          </div>
          <div class="form-group">
            <label for="message-text" class="control-label">按钮中文描述:</label>
            <input type="text" class="form-control" id="add-chinese-desc">
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="add-modal-submit">保存</button>
      </div>
    </div>
  </div>
</div>
<!-- 修改配置弹出框Modal -->
<div class="modal fade" id="update-config-model" tabindex="-1" role="dialog" aria-labelledby="updateModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="updateModalLabel">修改按钮统计配置项</h4>
      </div>
      <div class="modal-body">
        <form>
          <div class="form-group">
            <label for="recipient-name" class="control-label">按钮英文名:</label>
            <input type="text" class="form-control" id="update-english-name">
          </div>
          <div class="form-group">
            <label for="message-text" class="control-label">按钮中文描述:</label>
            <input type="text" class="form-control" id="update-chinese-desc">
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="update-modal-submit">保存</button>
      </div>
    </div>
  </div>
</div>
<!-- 错误警告提示框 -->
<div class="modal fade" id="error-warning-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="error-warning-label">
        	<span class="glyphicon glyphicon-warning-sign" aria-hidden="true"></span>
  			<strong>警告!</strong>
  		</h4>
      </div>
      <div class="modal-body" id="error-waring-body"></div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
<div class="modal fade" id="success-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="success-label">
        	<span class="glyphicon glyphicon-ok-circle" aria-hidden="true"></span>
  			<strong>恭喜!</strong>
  		</h4>
      </div>
      <div class="modal-body" id="success-body"></div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
<!-- 页面所有modal框end -->
<!-- 页面功能js-start -->
<script type="text/javascript">
	$(function(){
		// 新增数据提交
		$("#add-modal-submit").click(function(){
			var englishName = $("#add-english-name").val();
			var chineseDesc = $("#add-chinese-desc").val();
			var modalData = {"dataType": "2", "englishName": englishName, "chineseDesc": chineseDesc};
			$.ajax({
				url: "<%=basePath%>configCtrl/addConfig",
				type: "POST",
				dataType: "json",
				data : modalData,
				success: function(data){
					$('#add-config-model').modal('toggle');
					if (data["resultCode"] != 0){
						$("#error-waring-body").html(data["resultMessage"]);
						$('#error-warning-modal').modal('toggle');
					}else{
						reloadData(true);
						$("#success-body").html("哇塞，您提交数据保存成功啦！");
						$('#success-modal').modal('toggle');
					}
				},
				error: function(jqXHR, textStatus, errorThrown){
					$('#add-config-model').modal('toggle');
					$("#error-waring-body").html(textStatus + ", " + errorThrown);
					$('#error-warning-modal').modal('show');
				}
			});
		});
		// 修改按钮
		$("#update-btn").click(function(){
			var checkedSize = $("input[name='checkOne']:checked").length;
			if (checkedSize == 0){
				$("#error-waring-body").html("要修改数据，当然要先选择一条记录啦！");
				$('#error-warning-modal').modal('show');
			}else if (checkedSize > 1){
				$("#error-waring-body").html("哦 No!! 每次只能修改一条记录哦！");
				$('#error-warning-modal').modal('show');
			}else {
				var englishName = $($("input[name='checkOne']:checked")[0]).parent().next().next().text();
				var chineseDesc = $($("input[name='checkOne']:checked")[0]).parent().next().next().next().text();
				$("#update-english-name").attr("oldValue", englishName);
				$("#update-chinese-desc").attr("oldValue", chineseDesc);
				$("#update-english-name").val(englishName);
				$("#update-chinese-desc").val(chineseDesc);
				$('#update-config-model').modal('show');
			}
		});
		// 修改数据提交
		$("#update-modal-submit").click(function(){
			var oldEnglishName = $("#update-english-name").attr("oldValue");
			var oldChineseDesc = $("#update-chinese-desc").attr("oldValue");
			var englishName = $("#update-english-name").val();
			var chineseDesc = $("#update-chinese-desc").val();
			if (oldEnglishName == englishName && oldChineseDesc == chineseDesc){
				$('#update-config-model').modal('toggle');
				$("#error-waring-body").html("没有啥可修改的！");
				$('#error-warning-modal').modal('toggle');
				return false;
			}
			var modalData = {"dataType": "2", "englishName": englishName, "chineseDesc": chineseDesc,
					"oldEnglishName" : oldEnglishName, "oldChineseDesc": oldChineseDesc};
			$.ajax({
				url: "<%=basePath%>configCtrl/updateConfig",
				type: "POST",
				dataType: "json",
				data : modalData,
				success: function(data){
					$('#update-config-model').modal('toggle');
					if (data["resultCode"] != 0){
						$("#error-waring-body").html(data["resultMessage"]);
						$('#error-warning-modal').modal('toggle');
					}else{
						reloadData(true);
						$("#success-body").html("哇塞，数据修改成功啦！");
						$('#success-modal').modal('toggle');
					}
				},
				error: function(jqXHR, textStatus, errorThrown){
					$('#update-config-model').modal('toggle');
					$("#error-waring-body").html(textStatus + ", " + errorThrown);
					$('#error-warning-modal').modal('show');
				}
			});
		});
		// 删除按钮
		$("#delete-btn").click(function(){
			var checkedSize = $("input[name='checkOne']:checked").length;
			if (checkedSize <= 0){
				$("#error-waring-body").html("肯定是先选择一条记录再删除啦！");
				$('#error-warning-modal').modal('show');
			}else {
				var deleteDatas = new Array();
				$.each($("input[name='checkOne']:checked"), function(i, data){
					var deleteData = {};
					deleteData["englishName"] = $(this).parent().next().next().text();
					deleteData["chineseDescription"] = $(this).parent().next().next().next().text();
					deleteDatas.push(deleteData);
				});
				$.ajax({
					url: "<%=basePath%>configCtrl/deleteConfig",
					type: "POST",
					dataType: "json",
					data : {"dataType": "2", "deleteDatas": JSON.stringify(deleteDatas)},
					success: function(data){
						if (data["resultCode"] != 0){
							$("#error-waring-body").html(data["resultMessage"]);
						}else{
							reloadData(true);
							$("#error-waring-body").html("恭喜，您成功删除了" + data["resultData"] + "条数据！");
						}
						$('#error-warning-modal').modal('toggle')
					},
					error: function(error){
						$("#error-waring-body").html("不好意思，系统太忙啦，请等会儿再试试吧！");
						$('#error-warning-modal').modal('show');
					}
				});
			}
		});
		// 全选按钮
		$("#check-all").click(function(){
			$.each($("input[name='checkOne']"), function(i, data){
				if ($(this).is(":checked")){
					this.checked = false;
				}else{
					this.checked = true;
				}
			});
		});
	});
</script>
<!-- 页面功能js-end -->
</body>
</html>