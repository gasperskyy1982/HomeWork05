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
			<td width="100px" align='center'><a href='./auth'> LOGIN</a></td>
			<td width="100px" align='center'><a href='./register'>REGISTER</a></td>
		</tr>

		<tr>
			<table align="center">
				<tr>
					<td>
						<form id="loginForm" action="./auth" method="post">
							<div class="field">
								<label>Enter your login:</label>
								<div class="input">
									<input type="text" name="Login" value="" id="Login" />
								</div>
							</div>

							<div class="field">
								<a href="#" id="forgot">Forgot your password?</a> <label>Enter
									your password:</label>
								<div class="input">
									<input type="password" name="Password" value="" id="Password" />
								</div>
							</div>

							<div class="submit">
								<button type="submit">Enter</button>
								<label id="remember"><input name="" type="checkbox"
									value="" /> Remember me</label>
							</div>
						</form>
					</td>
				</tr>
			</table>
		</tr>

<c:if test = "${isError == 'true'}">
		<tr>
		<td align = 'center'>
		Incorrect login or password
		</td></tr>
</c:if>

</table>

</body>
</html>