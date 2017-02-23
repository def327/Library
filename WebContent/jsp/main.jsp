<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Web Bank</title>
</head>
<body>
	<form method="POST" action="LoginController">
		<p>
			<strong>Email:</strong> <input type="text" name="email">
		</p>
		<p>
			<strong>Password:</strong> <input type="text" name="password">
		</p>
		<p>
			<button type="submit">Login</button>
		</p>
	</form>
	<form method="GET" action="jsp/registration.jsp">
		<p>
			<button type="submit">Registry</button>
		</p>
	</form>
</body>
</html>
