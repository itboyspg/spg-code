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
<title>index</title>
<link href="<%=basePath%>bootstrap/css/bootstrap.min.css" rel="stylesheet">
<style type="text/css">
	#navbar-buttom button{
		margin-left: 5px;
	}
</style>
<script type="text/javascript" src="js/jQuery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="<%=basePath%>bootstrap/js/bootstrap.min.js"></script>
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
				<a href="#" class="list-group-item active">
				  Cras justo odio
				</a>
				<a href="#" class="list-group-item">Dapibus ac facilisis in</a>
				<a href="#" class="list-group-item">Morbi leo risus</a>
				<a href="#" class="list-group-item">Porta ac consectetur ac</a>
				<a href="#" class="list-group-item">统计项目配置</a>
			</div>
		</div>
		<div class="col-md-9">
			<ul class="nav nav-tabs">
			  <li role="presentation" class="active">
			  	<a href="#">PV配置</a>
			  	</li>
			  <li role="presentation"><a href="#">Profile</a></li>
			  <li role="presentation"><a href="#">Messages</a></li>
			</ul>
			<table class="table table-hover" id="pvConfigTable">
				<tr>
					<th>
						<input type="checkbox" id="check-all">选择
					</th>
					<th>序号</th>
					<th>页面英文名</th>
					<th>页面中文名</th>
				</tr>
				<tr>
					<td><input type="checkbox" name="checkOne"></td>
					<td>1</td>
					<td>login</td>
					<td>登陆</td>
				</tr>
				<tr>
					<td><input type="checkbox" name="checkOne"></td>
					<td>2</td>
					<td>regist</td>
					<td>注册</td>
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
					<button id="add-btn" type="button" class="btn btn-primary btn-sm navbar-btn navbar-right" data-toggle="modal" data-target="#add-pv-config-model">
						<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>增加
					</button>
			    </div><!-- /.navbar-collapse -->
			  </div><!-- /.container-fluid -->
			</nav>
		</div>
	</div>
</div>
<!-- 页面所有modal框start -->
<!-- 添加PV配置弹出框Modal -->
<div class="modal fade" id="add-pv-config-model" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">新增</h4>
      </div>
      <div class="modal-body">
        <form>
          <div class="form-group">
            <label for="recipient-name" class="control-label">页面英文名:</label>
            <input type="text" class="form-control" id="english-name">
          </div>
          <div class="form-group">
            <label for="message-text" class="control-label">中文描述:</label>
            <input type="text" class="form-control" id="chinese-desc">
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="modal-submit">保存</button>
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
		$("#modal-submit").click(function(){
			var englishName = $("#english-name").val();
			var chineseDesc = $("#chinese-desc").val();
			var modalData = {"dataType": "1", "englishName": englishName, "chineseDesc": chineseDesc, "dataType": "1"};
			$.ajax({
				url: "configCtrl/addConfig",
				type: "POST",
				dataType: "json",
				data : modalData,
				success: function(data){
					$('#add-pv-config-model').modal('toggle')
					if (data["resultCode"] != 0){
						$("#error-waring-body").html(data["resultMessage"]);
						$('#error-warning-modal').modal('toggle')
					}else{
						$("#success-body").html("提交数据添加成功！");
						$('#success-modal').modal('toggle')
					}
				},
				error: function(error){
					$("#error-waring-body").html(error);
					$('#error-warning-modal').modal('show');
				}
			});
		});
		// 修改按钮
		$("#update-btn").click(function(){
			var checkedSize = $("input[name='checkOne']:checked").length;
			if (checkedSize == 0){
				$("#error-waring-body").html("请至少选择一条记录进行修改！");
				$('#error-warning-modal').modal('show');
			}else if (checkedSize > 1){
				$("#error-waring-body").html("只能选择一条记录进行修改！");
				$('#error-warning-modal').modal('show');
			}else {
				var englishName = $($("input[name='checkOne']:checked")[0]).parent().next().next().text();
				var chineseDesc = $($("input[name='checkOne']:checked")[0]).parent().next().next().next().text();
				$("#english-name").val(englishName);
				$("#chinese-desc").val(chineseDesc);
				$('#add-pv-config-model').modal('show');
			}
		});
		// 删除按钮
		$("#delete-btn").click(function(){
			var checkedSize = $("input[name='checkOne']:checked").length;
			if (checkedSize <= 0){
				$("#error-waring-body").html("请至少选择一条记录进行删除！");
				$('#error-warning-modal').modal('show');
			}else {
				$.ajax({
					url: "pvCtrl/deletePV",
					type: "POST",
					dataType: "json",
					data : {},
					success: function(data){
						if (data["resultCode"] != 0){
							$("#error-waring-body").html(data["resultMessage"]);
						}
						$('#add-pv-config-model').modal('toggle')
						$('#error-warning-modal').modal('toggle')
					},
					error: function(error){
						$("#error-waring-body").html("系统繁忙，请稍后再试！");
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