package edu.eci.dosw.tdd.controller;

import edu.eci.dosw.tdd.model.Book;
import edu.eci.dosw.tdd.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

	private final BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@PostMapping
	public Book addBook(@RequestBody AddBookRequest request) {
		Book book = new Book(request.getId(), request.getTitle(), request.getAuthor());
		return bookService.addBook(book, request.getQuantity());
	}

	@GetMapping
	public List<Book> getBooks() {
		return bookService.getAllBooks();
	}

	@GetMapping("/{bookId}/stock")
	public StockResponse getStock(@PathVariable String bookId) {
		return new StockResponse(bookId, bookService.getStock(bookId));
	}

	public static class AddBookRequest {
		private String id;
		private String title;
		private String author;
		private int quantity;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
	}

	public static class StockResponse {
		private final String bookId;
		private final int quantity;

		public StockResponse(String bookId, int quantity) {
			this.bookId = bookId;
			this.quantity = quantity;
		}

		public String getBookId() {
			return bookId;
		}

		public int getQuantity() {
			return quantity;
		}
	}
}
