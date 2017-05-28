<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>首页</title>

<!-- Bootstrap -->
<link href="<%=basePath%>bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Font Awesome -->
<link href="<%=basePath%>css/font-awesome.min.css" rel="stylesheet">
<!-- NProgress -->
<link href="<%=basePath%>css/nprogress.css" rel="stylesheet">
<!-- bootstrap-wysiwyg -->
<link href="<%=basePath%>css/prettify.min.css" rel="stylesheet">
<!-- Custom styling plus plugins -->
<link href="<%=basePath%>/css/custom.min.css" rel="stylesheet">
</head>

<body>
	<div class="col-md-3 left_col">
		<div class="left_col scroll-view">
			<div class="navbar nav_title" style="border: 0;">
				<a href="<%=basePath%>index.jsp" class="site_title">
					<i class="fa fa-cloud"></i>
					<span>ZK-Center</span>
				</a>
			</div>
			<div class="clearfix"></div>
			<!-- menu profile quick info -->
			<div class="profile">
				<div class="profile_pic">
					<img src="<%=basePath%>images/img.jpg" alt="..." class="img-circle profile_img">
				</div>
				<div class="profile_info">
					<span>Welcome</span>
					<h2>ZK Admin</h2>
				</div>
			</div>
			<!-- /menu profile quick info -->
			<br />
			<!-- sidebar menu -->
			<div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
				<div class="menu_section">
					<h3>&nbsp;</h3>
					<ul class="nav side-menu">
						<li>
							<a>
								<i class="fa fa-gears"></i> 云配置 <span class="fa fa-chevron-down"></span>
							</a>
							<ul class="nav child_menu">
								<li><a href="<%=basePath %>configCtrl/forward?target=cloudConfigReadme">云配置接入须知</a></li>
								<li><a href="<%=basePath %>configCtrl/forward?target=configSystem">接入系统配置</a></li>
								<li><a href="<%=basePath %>configCtrl/configMgr">配置项管理</a></li>
							</ul>
						</li>
						<li>
							<a>
								<i class="fa fa-gears"></i> 云日志 <span class="fa fa-chevron-down"></span>
							</a>
							<ul class="nav child_menu">
								<li><a href="<%=basePath %>configCtrl/forward?target=queryLog">日志查询</a></li>
							</ul>
						</li>
						<li>
							<a><i class="fa fa-edit"></i> 埋点统计 <span class="fa fa-chevron-down"></span></a>
							<ul class="nav child_menu">
								<li><a href="<%=basePath %>error/comingsoon.html">PV</a></li>
								<li><a href="<%=basePath %>error/comingsoon.html">UV</a></li>
								<li><a href="<%=basePath %>error/comingsoon.html">用户分布</a></li>
								<li><a href="<%=basePath %>error/comingsoon.html">按钮/链接点击量</a></li>
								<li><a href="<%=basePath %>error/comingsoon.html">日活用户量</a></li>
							</ul>
						</li>
					</ul>
				</div>
			</div>
			<!-- /sidebar menu -->
		</div>
	</div>

	<!-- top navigation -->
	<div class="top_nav">
		<div class="nav_menu" style="margin-bottom: -20px;">
			<nav>
				<div class="nav toggle">
					<a id="menu_toggle"><i class="fa fa-bars"></i></a>
				</div>
				<ul class="nav navbar-nav navbar-right">
					<li class="">
						<a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
							<img src="<%=basePath%>images/img.jpg" alt="">John Doe <span class=" fa fa-user"></span>
						</a>
					</li>
				</ul>
			</nav>
		</div>
	</div>
	<!-- /top navigation -->

	<!-- footer content -->
<!-- 	<footer> -->
<!-- 		<div class="pull-right"> -->
<!-- 			Gentelella - Bootstrap Admin Template by <a href="https://colorlib.com">Colorlib</a> -->
<!-- 		</div> -->
<!-- 		<div class="clearfix"></div> -->
<!-- 	</footer> -->
	<!-- /footer content -->
	<!-- jQuery -->
	<script src="<%=basePath%>js/jquery-1.11.3.min.js"></script>
	<!-- Bootstrap -->
	<script src="<%=basePath%>bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- FastClick -->
	<script src="<%=basePath%>js/fastclick.js"></script>
	<!-- NProgress -->
	<script src="<%=basePath%>js/nprogress.js"></script>
	<!-- bootstrap-wysiwyg -->
	<script src="<%=basePath%>js/bootstrap-wysiwyg.min.js"></script>
	<script src="<%=basePath%>js/jquery.hotkeys.js"></script>
	<script src="<%=basePath%>js/prettify.js"></script>
	<script src="<%=basePath%>js/icheck.min.js"></script>
	<!-- Custom Theme Scripts -->
	<script src="<%=basePath%>js/custom.min.js"></script>

	<!-- bootstrap-wysiwyg -->
	<script>
		$(document).ready(function() {
			function initToolbarBootstrapBindings() {
				var fonts = [ 'Serif', 'Sans', 'Arial',
						'Arial Black', 'Courier',
						'Courier New', 'Comic Sans MS',
						'Helvetica', 'Impact', 'Lucida Grande',
						'Lucida Sans', 'Tahoma', 'Times',
						'Times New Roman', 'Verdana' ], fontTarget = $(
						'[title=Font]').siblings(
						'.dropdown-menu');
				$.each(fonts, function(idx, fontName) {
					fontTarget.append($('<li><a data-edit="fontName ' + fontName + '" style="font-family:\'' + fontName + '\'">'
									+ fontName
									+ '</a></li>'));
				});
				$('a[title]').tooltip({
					container : 'body'
				});
				$('.dropdown-menu input').click(function() {
					return false;
				}).change(function() {
					$(this).parent('.dropdown-menu')
							.siblings(
									'.dropdown-toggle')
							.dropdown('toggle');
				}).keydown('esc', function() {
					this.value = '';
					$(this).change();
				});

				$('[data-role=magic-overlay]').each(
					function() {
						var overlay = $(this), target = $(overlay.data('target'));
						overlay.css('opacity', 0).css('position', 'absolute').offset(target.offset()).width(target.outerWidth()).height(target.outerHeight());
				});

				if ("onwebkitspeechchange" in document.createElement("input")) {
					var editorOffset = $('#editor').offset();
					$('.voiceBtn').css('position', 'absolute').offset({
						top : editorOffset.top,
						left : editorOffset.left + $('#editor').innerWidth() - 35});
				} else {
					$('.voiceBtn').hide();
				}
			}

			function showErrorAlert(reason, detail) {
				var msg = '';
				if (reason === 'unsupported-file-type') {
					msg = "Unsupported format " + detail;
				} else {
					console.log("error uploading file", reason, detail);
				}
				$('<div class="alert"> <button type="button" class="close" data-dismiss="alert">&times;</button>'
								+ '<strong>File upload error</strong> '
								+ msg + ' </div>').prependTo('#alerts');
			}
			initToolbarBootstrapBindings();
			$('#editor').wysiwyg({
				fileUploadError : showErrorAlert
			});
			prettyPrint();
		});
	</script>
	<!-- /bootstrap-wysiwyg -->

	<!-- compose -->
	<script>
		$('#compose, .compose-close').click(function() {
			$('.compose').slideToggle();
		});
	</script>
	&nbsp;
	<!-- /compose -->
</body>
</html>