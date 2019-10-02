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
<title>Insert title here</title>
</head>
<body>

	<table align="center">
		<tr>
			<td width="100px" align='center'><a href='./auth'> LOGOUT</a></td>
			<td width="100px" align='center'><a href='./correct'>CORRECT</a></td>
		</tr>
	</table>

	<c:choose>
		<c:when test="${showCorrForm == 'true'}">

			<form id="corrForm" action="./correct" method="post">
				<table border="0" align="center">
					<tr>
						<td>
							<table>
								<div class="field">
									<label>Login</label>
									<div class="input" align='center'>
									<input type = "hidden" name ="Login" value ="${user.login}" />
									${user.login}
									</div>
								</div>
								
													
								<div class="field">
									<label>Password</label>
									<div class="input">
										<input type="password" name="Password"
											placeholder="min 8 symbols" required="required" value=""
											id="Password" />
									</div>
								</div>


								<div class="field">
									<label>Re-type Password</label>
									<div class="input">
										<input type="password" name="Re_Password"
											placeholder="min 8 symbols" required="required" value=""
											id="Re_Password" />
									</div>
								</div>

								<div class="field">
									<label>Name</label>
									<div class="input">
										<input type="text" name="Name" value="${user.name}" id="Name" />
									</div>
								</div>


								<div class="field">
									<label>Region</label>
									<div class="input">
										<select name="Region" />
										<option value="DNR"
											<c:choose>
								 				<c:when test = "${user.region eq 'DNR'}">
								 				selected
												</c:when>
												<c:otherwise>
												""
												</c:otherwise>
											</c:choose>
											>DNR</option>
										<option value="LNR"
											<c:choose>
												 <c:when test = "${user.region eq 'LNR'}">
								 				selected
												</c:when>
												<c:otherwise>
												""
												</c:otherwise>
												</c:choose>
											>LNR</option>
										<option value="Crimea"
											<c:choose>
								 				<c:when test = "${user.region eq 'Crimea'}">
								 				selected
												</c:when>
												<c:otherwise>
												""
												</c:otherwise>
												</c:choose>
											>Crimea</option>
										</select>
									</div>
								</div>


								<div class="gender">
									<label>Gender</label>
									<div>
										<label id="gender"> Male <input type="radio"
											width="10px" name="Gender" value="true"
												<c:choose>
								 					<c:when test = "${user.gender == 'true'}">
								 					checked
													</c:when>
													<c:otherwise>
													""
													</c:otherwise>
												</c:choose>
											/>
										</label>
										 <label
											id="gender"> Female <input type="radio" name="Gender"
											value="false"
												<c:choose>
								 					<c:when test = "${user.gender == 'false'}">
									 				checked
													</c:when>
													<c:otherwise>
													""
													</c:otherwise>
												</c:choose>	
											 />
										</label>
									</div>
								</div>


								<div class="field">
									<label>Comment</label>
									<div class="input">
										<textarea name="Comment" cols="20" raws="10"
											value="">${user.comment}</textarea>
									</div>
								</div>

								<div class="submit">
									<button type="submit" value="Register" />
									Correct
									</button>
									<label id="agree"> I agree <input name="Agree" input
										type="checkbox" /></label>
								</div>
							</table>
						</td>

						<td align=center>${errorCorr}</td>
					</tr>
				</table>
			</form>
		</c:when>

		<c:otherwise>
			<h1 align='center'>You corrected your data</h1> <br/>
			<br />
			<div>${user.login}</div><br />
			<div>${user.password}</div><br />
			<div>${user.name}</div><br />
			<div>${user.region}</div><br />
			<div>${user.gender}</div><br />
			<div>${user.comment}</div><br />
		
		</c:otherwise>
	</c:choose>

</body>
</html>