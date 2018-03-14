<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Spring - managed by GIT</title>
</head>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<body>
	<div align="center" class="container">
		<div class="jumbotron">
			<h1>Spring Project</h1>
			<small>- ${ment } -</small>
		</div>
		<hr />
		<div class="alert alert-warning alert-dismissible" id="warn" style="display: none">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<strong>경고!</strong> 다른 윈도우 혹은 탭에서 상태가 변경되었습니다.
		</div>
		<div align="right" style="padding-right: 20px;">
			<c:choose>
				<c:when test="${logon !=null }">
					${logon }님 환영합니다.
				</c:when>
				<c:otherwise>
					<a href="/login"><span>Sign in</span></a> <span>or</span> <a
						href="/join"><span>Sign up</span></a>
				</c:otherwise>
			</c:choose>
		</div>
		<hr />
		<div>
			<div class="alert alert-info">
				<strong>현재접속자수:</strong><span id="cnt"></span> / <strong>서버알림</strong><span id="info"></span>
			</div>
		</div>
	</div>
	<script>
		var ws1 = new WebSocket("ws://${pageContext.request.serverName}/handle");
		// 연결이 됬을때. 
		ws1.onopen = function() {
			//console.log("opened ");
			console.log(this);
		}
		// 메시지가 들어올때. 
		ws1.onmessage = function(resp) {
			console.log(resp);
			var obj = JSON.parse(resp.data);
			$("#cnt").html(obj.cnt);
			$("#info").html(obj.info);
		}
		// 연결이 끊길때. 
		ws1.onclose = function() {
			window.alert("연결이 해제되었습니다.");
		}
		
		var ws2 = new WebSocket("ws://${pageContext.request.serverName}/alert");
		// 메시지가 들어올때. 
		ws2.onmessage = function(rst) {
			console.log(rst);
			$("#warn").show();
		}
	</script>
</body>
</html>
