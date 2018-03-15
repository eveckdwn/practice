<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="../logonheader.jsp"%>
<fieldset>
	<legend>친구 목록</legend>
	<table class=".table-striped">
		<colgroup>
			<col width="25%" />
			<col width="50%" />
			<col width="" />
		</colgroup>
		<tr>
			<th>아이디</th>
			<th>이메일</th>
			<th>Lv</th>
		</tr>
		<c:choose>
			<c:when test="${fn:length(friends) == 0 }">
				<tr colspan="2">
					<td></td>
					<td>
						<div align="center">
							친구가 없습니다. <a href="/friend/follow"><span
								style="cursor: pointer; color: blue; text-decoration: underline;">[친구
									찾기]</span></a>
						</div>
					</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach var="friend" items="${friends }">
					<tr>
						<td>friend.ID</td>
						<td>friend.EMAIL</td>
						<td>friend.LV</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</table>
</fieldset>

<%@ include file="../logonfooter.jsp"%>
