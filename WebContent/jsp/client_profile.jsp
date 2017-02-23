<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<H1>Client Page</H1>
		<h2>Books at the library:</h2>
		<c:choose>
			<c:when test="${booksCount > 0}">
				<table cellspacing="2" border="1" cellpadding="5">
					<tr>
						<td>Book name</td>
						<td>Price</td>
						<td>Description</td>
						<td>ReleaseDate</td>
						<td>Categorie</td>
					</tr>
					<c:forEach var="i" begin="0" end="${booksCount - 1 }">
						<tr>
							<td>${booksList[i].bookName}</td>
							<td>${booksList[i].bookPrice}</td>
							<td>${booksList[i].bookDescription}</td>
							<td>${booksList[i].bookReleaseDate}</td>
							<td>${booksList[i].bookCategorie.categorieName}</td>
						</tr>
					</c:forEach>
				</table>
			</c:when>
			<c:when test="${booksCount == 0}">
				<h1>Empty Library!!!</h1>
			</c:when>
		</c:choose>
		<form method="GET" action="jsp/new_book_page.jsp">
			<p>
				<button type="submit">Add new Book</button>
			</p>
		</form>
	</div>
</body>
</html>

