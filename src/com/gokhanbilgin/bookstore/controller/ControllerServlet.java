package com.gokhanbilgin.bookstore.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gokhanbilgin.bookstore.dao.BookDao;
import com.gokhanbilgin.bookstore.model.Book;

public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookDao bookDao;

	@Override
	public void init() throws ServletException {
		String jdbcUrl = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		bookDao = new BookDao(jdbcUrl, jdbcUsername, jdbcPassword);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(req, resp);
				break;
			case "/insert":
				insertBook(req, resp);
				break;
			case "/delete":
				deleteBook(req, resp);
				break;
			case "/edit":
				showEditForm(req, resp);
				break;
			case "/update":
				updateBook(req, resp);
				break;
			default:
				listBook(req, resp);
				break;
			}
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}

	private void deleteBook(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		Book book = new Book(id);
		bookDao.deleteBook(book);
		resp.sendRedirect("list");
	}

	private void updateBook(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		String title = req.getParameter("title");
		String author = req.getParameter("author");
		float price = Float.parseFloat(req.getParameter("price"));
		Book book = new Book(id, title, author, price);
		bookDao.updateBook(book);
		resp.sendRedirect("list");
	}

	private void insertBook(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
		String title = req.getParameter("title");
		String author = req.getParameter("author");
		float price = Float.parseFloat(req.getParameter("price"));
		Book newBook = new Book(title, author, price);
		bookDao.insertBook(newBook);
		resp.sendRedirect("list");
	}

	private void showEditForm(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		Book existingBook = bookDao.getBook(id);
		RequestDispatcher dispatcher = req.getRequestDispatcher("BookForm.jsp");
		req.setAttribute("book", existingBook);
		dispatcher.forward(req, resp);
	}

	private void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("BookForm.jsp");
		dispatcher.forward(req, resp);
	}

	private void listBook(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, IOException, ServletException {
		List<Book> listBook = bookDao.listAllBooks();
		req.setAttribute("listBook", listBook);
		RequestDispatcher dispacther = req.getRequestDispatcher("BookList.jsp");
		dispacther.forward(req, resp);
	}

}
