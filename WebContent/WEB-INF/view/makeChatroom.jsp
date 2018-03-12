<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<c:choose>
		<c:when test="${lv == 1 }">
			Lv : ${lv }이므로 채팅방 개설 가능
		</c:when>
		<c:otherwise>
			Lv : ${lv }이므로 채팅방 개설 불가능
			인증을 원하시면 <a href="/test/email">여기</a>를 눌러주세요.
		</c:otherwise>
	</c:choose>
</body>
</html>