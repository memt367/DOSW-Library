package edu.eci.dosw.tdd.service;

import edu.eci.dosw.tdd.exception.BookNotAvailableException;
import edu.eci.dosw.tdd.exception.ResourceNotFoundException;
import edu.eci.dosw.tdd.model.Book;
import edu.eci.dosw.tdd.util.ValidationUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookService {

	private final Map<String, Book> booksById = new HashMap<>();
	private final Map<String, Integer> inventoryByBookId = new HashMap<>();

	public Book addBook(Book book, int quantity) {
		if (book == null) {
			throw new IllegalArgumentException("book is required");
		}

		String id = ValidationUtil.requireNonBlank(book.getId(), "book.id");
		ValidationUtil.requireNonBlank(book.getTitle(), "book.title");
		ValidationUtil.requireNonBlank(book.getAuthor(), "book.author");
		ValidationUtil.requirePositive(quantity, "quantity");

		booksById.put(id, book);
		inventoryByBookId.merge(id, quantity, Integer::sum);
		return book;
	}

	public List<Book> getAllBooks() {
		return new ArrayList<>(booksById.values());
	}

	public Book getBookById(String bookId) {
		ValidationUtil.requireNonBlank(bookId, "bookId");
		Book book = booksById.get(bookId);
		if (book == null) {
			throw new ResourceNotFoundException("Book not found: " + bookId);
		}
		return book;
	}

	public int getStock(String bookId) {
		getBookById(bookId);
		return inventoryByBookId.getOrDefault(bookId, 0);
	}

	public void takeOneCopy(String bookId) {
		int available = getStock(bookId);
		if (available <= 0) {
			throw new BookNotAvailableException("No copies available for book: " + bookId);
		}
		inventoryByBookId.put(bookId, available - 1);
	}

	public void returnOneCopy(String bookId) {
		getBookById(bookId);
		inventoryByBookId.merge(bookId, 1, Integer::sum);
	}
}
