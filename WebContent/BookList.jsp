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
			<a href="new" class="btn btn-success">Add New Book</a> <a
				href="list" class="btn btn-success">List Book</a>
		</div>
		<br>
		<h3 class="text-center">List of Books</h3>

		<table class="table">
			<tr>
				<th scope="col">ID</th>
				<th scope="col">Title</th>
				<th scope="col">Author</th>
				<th scope="col">Price</th>
				<th scope="col">Actions</th>
			</tr>
			<c:forEach var="book" items="${listBook}">
				<tr>
					<td><c:out value="${book.id}" /></td>
					<td><c:out value="${book.title}" /></td>
					<td><c:out value="${book.author}" /></td>
					<td><c:out value="${book.price}" /></td>
					<td><a href="edit?id=<c:out value='${book.id}' />">Edit</a> <a
						href="delete?id=<c:out value='${book.id}' />">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>