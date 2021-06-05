<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="UTF-8">
<title>Bookstore App</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container">
		<br>
		<h2 class="text-center">Bookstore</h2>
		<hr>
		<div class="container text-center">
			<a href="new" class="btn btn-success">Add New Book</a> <a href="list"
				class="btn btn-success">List Book</a>
		</div>
		<br>
		<h3 class="text-center">
			<c:if test="${book != null}">
                        Edit Book
            </c:if>
			<c:if test="${book == null}">
                        Add New Book
            </c:if>
		</h3>

		<div class="text-center">
			<c:if test="${book != null}">
				<form action="update" method="post">
			</c:if>
			<c:if test="${book == null}">
				<form action="insert" method="post">
			</c:if>
			<table class="table">
				<c:if test="${book != null}">
					<input type="hidden" name="id" value="<c:out value='${book.id}' />" />
				</c:if>
				<tr>
					<th>Title:</th>
					<td><input type="text" name="title" size="45"
						value="<c:out value='${book.title}' />" /></td>
				</tr>
				<tr>
					<th>Author:</th>
					<td><input type="text" name="author" size="45"
						value="<c:out value='${book.author}' />" /></td>
				</tr>
				<tr>
					<th>Price:</th>
					<td><input type="text" name="price" size="5"
						value="<c:out value='${book.price}' />" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input class="btn btn-success"
						type="submit" value="Save" /></td>
				</tr>
			</table>
			</form>
		</div>
	</div>
</body>
</html>