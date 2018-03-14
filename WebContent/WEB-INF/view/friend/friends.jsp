<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<%@ include file="../logonheader.jsp"%>
<fieldset>
	<legend>친구 목록</legend>
	${friends }
	<table class=".table-striped">
		<tr>
			<th>아이디</th>
			<th>이메일</th>
			<th>Lv</th>
		</tr>
		<c:choose>
		<c:when test="${friends == null }">
			<tr colspan="3">
				<td>친구가 없습니다. <a href=""><span style="cursor:pointer; color: blue; text-decoration:underline;">친구 찾기</span></a></td>
			</tr>
		</c:when>
		<c:forEach var="friend" items="${friends }">
			<tr>
				<td>friend.ID</td>
				<td>friend.EMAIL</td>
				<td>friend.LV</td>
			</tr>
		</c:forEach>
		</c:choose>
	</table>
</fieldset>

<%@ include file="../logonfooter.jsp"%>