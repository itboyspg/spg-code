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

<style>
img.hand:hover {
	cursor: hand
}
</style>

<script type="text/javascript" src="../js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#onlineuser").attr("id", "current");

		$("img").click(function() {
			//$("img").css("cursor", "pointer");
		});
	});
</script>
</head>
<body>
	<div id="wrap">
		<c:import url="commonhead.jsp" />
		<div id="content">
			<div id="main">
				<h3>在线用户列表</h3>
				<table width="100%">
					<tr>
						<th>登陆IP:端口</th>
						<th>ChannelID</th>
						<th>是否在线</th>
						<th>强制下线</th>
					</tr>
					<c:forEach items="${users }" var="user">
						<tr>
							<td>${user.ip }</td>
							<td>${user.channelId }</td>
							<c:choose>
								<c:when test="${user.alive }">
									<td><img src="../images/online.png"></td>
								</c:when>
								<c:otherwise>
									<td><img border="0px" src="../images/offline.png"></td>
								</c:otherwise>
							</c:choose>
							<td align="center"><img class="mouse_hand" alt="强制下线" src="../images/xx.jpg"></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<div id="footer"></div>
	</div>
</body>
</html>