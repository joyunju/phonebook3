<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list</title>
</head>
<body>
	<h1>전화번호부</h1>

	<h2>리스트</h2>

	<p>입력한 정보 내용입니다</p>

	<c:forEach items="${requestScope.personList }" var="personVo">
		<table border="1">
			<tr>
				<td>이름(name)${personVo.personId }</td>
				<td>${personVo.name }</td>
			</tr>
			<tr>
				<td>핸드폰(hp)</td>
				<td>${personVo.hp }</td>
			</tr>
			<tr>
				<td>회사(company)</td>
				<td>${personVo.company }</td>
			</tr>
			<tr>
				<td>[수정폼]</td>
				<td><a href="./delete?no=${personVo.personId}">[삭제]</a></td>
			</tr>
		</table>
		<br>
	</c:forEach>
	
	<a href="./writeForm">전화번호 등록</a>
</body>
</html>