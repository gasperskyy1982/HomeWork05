<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8">
<link href="./styles/style.css" rel="stylesheet" />
<link href="./styles/register_style.css" rel="stylesheet" />
<title>Authorization</title>
</head>
<body>
	<table align="center">
		<tr>
			<td width="100px" align='center'><a href='./auth'> LOGOUT</a></td>
			<td width="100px" align='center'><a href='./correct'>CORRECT</a></td>
		</tr>
		<tr>
		<td align = "center">
			Hello!
			<h1> ${authUser.name}</h1>
			<br />
			<div>You are ${auth}</div><br />
			<div>Session</div><br />
			<div>${user.login}</div><br />
			<div>${user.password}</div><br />
			<div>${user.name}</div><br />
			<div>${user.region}</div><br />
			<div>${user.gender}</div><br />
			<div>${user.comment}</div><br />
			<br/>
			<div>Request</div><br />
			<div>${authUser.login}</div><br />
			<div>${authUser.password}</div><br />
			<div>${authUser.name}</div><br />
			<div>${authUser.region}</div><br />
			<div>${authUser.gender}</div><br />
			<div>${authUser.comment}</div><br />
		</td>
		</tr>
</body>
</html>