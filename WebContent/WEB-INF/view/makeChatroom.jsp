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
			Lv : ${lv }�̹Ƿ� ä�ù� ���� ����
		</c:when>
		<c:otherwise>
			Lv : ${lv }�̹Ƿ� ä�ù� ���� �Ұ���
			������ ���Ͻø� <a href="/test/email">����</a>�� �����ּ���.
		</c:otherwise>
	</c:choose>
</body>
</html>