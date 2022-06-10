<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>writeForm</title>
</head>
<body>
	<h1>전화번호부</h1>
	<h2>등록폼</h2>
	<p>
		전화번호 등록하려면
		<br> 아래 항목을 기입하고 "등록" 버튼을 클릭하세요
	</p>
	
	<form action="/phonebook3/write" method="get">
		이름(name) <input type="tesxt" name="name" value="">
		<br>
		핸드폰(hp) <input type="tesxt" name="hp" value="">
		<br>
		회사(company) <input type="tesxt" name="company" value="">
		<br>
		<button type="submit">등록</button>
	</form>
</body>
</html>