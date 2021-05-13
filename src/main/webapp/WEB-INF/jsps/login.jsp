<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>

	<center>
		<h1>Login</h1>
		<c:url value="/login" var="loginProcessingUrl" />
		<form action="${loginProcessingUrl}" method="post">
			<table border="1">
				<c:if test="${param.error != null}">
					<tr>
						<td colspan="2">Failed to login. <c:if
								test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
                  Reason: <c:out
									value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
							</c:if>
						</td>
					</tr>
				</c:if>
				<c:if test="${param.logout != null}">
					<tr>
					<td colspan="2">
					<div>You have been logged out. ${param.logout}</div>
					</td>
					</tr>
				</c:if>
				<tr>
					<th>UserName :</th>
					<th><input type="text" name="username" id="username" /></th>
				</tr>
				<tr>
					<th>Password :</th>
					<th><input type="password" name="password" id="password" /></th>
				</tr>
				<tr>
					<th colspan="2"><input type="submit" width="100%" value="Login"></th>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>