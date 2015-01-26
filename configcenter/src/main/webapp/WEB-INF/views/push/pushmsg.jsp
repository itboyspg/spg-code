<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../css/VectorLover.css" type="text/css" />
<link rel="stylesheet" href="../css/common.css" type="text/css" />
<title>在线用户</title>
<script type="text/javascript" src="../js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#pushrequest").attr("id", "current");
	});
	
	$(function(){
		// 提交表单
		$("#submitpushrequest").click(function(){
			var title = $("#title").val();
			var message = $("#message").val();
			if (title == "" || title == "请输入标题")
			{
				$("#title").addClass("red");
				return;
			}
			if (message == "")
			{
				$("#message").addClass("red");
				return;
			}
			
			$.ajax({
				type : "POST",
				url : "/configcenter/pushCtrl/pushRequest",
				data : $("#pushmsgform").serialize(),
				success : function(data)
				{
					alert(data["data"]);
					$("#title").val("请输入标题");
					$("#message").val("");
				},
				error : function(request)
				{
					alert("推送请求提交失败，请稍后再试！");
				}
			});
		});
		
		$("#submitpushrequest").mouseover(function(){
			$("#submitpushrequest").css("cursor", "pointer");
		});
		
		// 输入框变红
		$("#title").blur(function(){
			var title = $("#title").val();
			if (title == "" || title == "请输入标题"){
				$("#title").addClass("red");
			}
		});
		$("#message").blur(function(){
			var message = $("#message").val();
			if (message == ""){
				$("#message").addClass("red");
			}
		});
		
		// 取消输入框变红
		$("#title").focus(function(){
			var title = $("#title").val();
			if (title == "请输入标题"){
				$("#title").val("");
			}
			$("#title").removeClass();
		});
		$("#message").focus(function(){
			$("#message").removeClass();
		});
		
	});
</script>
</head>
<body>
	<div id="wrap">
		<c:import url="commonhead.jsp" />
		<div id="content">
			<div id="main">
				<h3>推送消息</h3>
				<form action="" method="post" id="pushmsgform">
					<p>
						<label for="name">指定用户 (可选，不填默认推送给所有用户)</label> <br /> <input
							id="name" name="name" value="用户ID，多个用户用,分割" type="text"
							tabindex="1" />
					</p>
					<p>
						<label for="title">标题 (必须)</label> <br /> <input id="title"
							name="title" value="请输入标题" type="text" tabindex="1" />
					</p>
					<p>
						<label for="message">推送消息内容</label> <br />
						<textarea id="message" name="message" rows="10" cols="20"
							tabindex="4"></textarea>
					</p>
					<p class="no-border">
						<input id="submitpushrequest" class="button" type="button" value="提交推送" tabindex="5" />
					</p>
				</form>
			</div>
		</div>
		<div id="footer"></div>
	</div>
</body>
</html>