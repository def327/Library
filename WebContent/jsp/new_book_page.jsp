<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<form method="POST" action="../AddNewBookController">
			<p>
				<strong>Book Name:</strong> <input type="text" name="bookName">
			</p>
			<p>
				<strong>Price:</strong> <input type="text" name="bookPrice">
			</p>
			<p>
				<strong>Description:</strong>
				<textarea name="bookDescription" maxlength="254"></textarea>
			</p>
			<p>
				<strong>ReleaseDate:</strong> <input type="text"
					name="bookReleaseDate">
			</p>
			<p>
				<strong>Categorie:</strong>
			</p>
			<c:choose>
				<c:when test="${categoriesCount > 0}">
					<p>
						<c:forEach var="i" begin="0" end="${categoriesCount - 1 }">
							<input name="bookCategorieId" type="radio"
								value="${categoriesList[i].id}"> ${categoriesList[i].categorieName}
				</c:forEach>
					</p>
				</c:when>
			</c:choose>
			<p>
				<button type="reset">Clear</button>
			</p>
			<p>
				<button type="submit">Save</button>
			</p>
		</form>
	</div>
</body>
</html>