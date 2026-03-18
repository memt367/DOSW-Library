package edu.eci.dosw.tdd.service;

import edu.eci.dosw.tdd.exception.BookNotAvailableException;
import edu.eci.dosw.tdd.exception.ResourceNotFoundException;
import edu.eci.dosw.tdd.model.Book;
import edu.eci.dosw.tdd.model.Loan;
import edu.eci.dosw.tdd.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoanServiceTest {

    private BookService bookService;
    private UserService userService;
    private LoanService loanService;

    @BeforeEach
    void setUp() {
        bookService = new BookService();
        userService = new UserService();
        loanService = new LoanService(bookService, userService);

        bookService.addBook(new Book("B1", "Clean Code", "Robert C. Martin"), 1);
        userService.addUser(new User("U1", "Ana", "ana@mail.com"));
    }

    @Test
    void shouldCreateLoanAndDecreaseStock() {
        Loan loan = loanService.createLoan("U1", "B1", 7);

        assertEquals("U1", loan.getUserId());
        assertEquals("B1", loan.getBookId());
        assertEquals(0, bookService.getStock("B1"));
    }

    @Test
    void shouldReturnLoanAndIncreaseStock() {
        Loan loan = loanService.createLoan("U1", "B1", 7);

        Loan returned = loanService.returnLoan(loan.getId());

        assertTrue(returned.isReturned());
        assertEquals(1, bookService.getStock("B1"));
    }

    @Test
    void shouldThrowWhenCreatingLoanWithoutStock() {
        loanService.createLoan("U1", "B1", 7);

        assertThrows(BookNotAvailableException.class, () -> loanService.createLoan("U1", "B1", 7));
    }

    @Test
    void shouldThrowWhenUserDoesNotExist() {
        assertThrows(ResourceNotFoundException.class, () -> loanService.createLoan("UNKNOWN", "B1", 7));
    }
}
