package edu.eci.dosw.tdd.service;

import edu.eci.dosw.tdd.exception.BookNotAvailableException;
import edu.eci.dosw.tdd.exception.ResourceNotFoundException;
import edu.eci.dosw.tdd.model.Book;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BookServiceTest {

    private final BookService bookService = new BookService();

    @Test
    void shouldAddBookAndIncreaseStock() {
        Book book = new Book("B1", "Clean Code", "Robert C. Martin");

        bookService.addBook(book, 3);

        assertEquals(3, bookService.getStock("B1"));
    }

    @Test
    void shouldThrowWhenBookHasNoStock() {
        Book book = new Book("B2", "DDD", "Eric Evans");
        bookService.addBook(book, 1);
        bookService.takeOneCopy("B2");

        assertThrows(BookNotAvailableException.class, () -> bookService.takeOneCopy("B2"));
    }

    @Test
    void shouldThrowWhenBookDoesNotExist() {
        assertThrows(ResourceNotFoundException.class, () -> bookService.getBookById("UNKNOWN"));
    }
}
