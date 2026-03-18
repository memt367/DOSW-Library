package edu.eci.dosw.tdd.service;

import edu.eci.dosw.tdd.exception.ResourceNotFoundException;
import edu.eci.dosw.tdd.model.Loan;
import edu.eci.dosw.tdd.util.ValidationUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class LoanService {

	private final List<Loan> loans = new ArrayList<>();
	private final BookService bookService;
	private final UserService userService;

	public LoanService(BookService bookService, UserService userService) {
		this.bookService = bookService;
		this.userService = userService;
	}

	public Loan createLoan(String userId, String bookId, int loanDays) {
		ValidationUtil.requireNonBlank(userId, "userId");
		ValidationUtil.requireNonBlank(bookId, "bookId");
		ValidationUtil.requirePositive(loanDays, "loanDays");

		userService.getUserById(userId);
		bookService.getBookById(bookId);
		bookService.takeOneCopy(bookId);

		LocalDate startDate = LocalDate.now();
		Loan loan = new Loan(
				UUID.randomUUID().toString(),
				userId,
				bookId,
				startDate,
				startDate.plusDays(loanDays),
				false
		);
		loans.add(loan);
		return loan;
	}

	public List<Loan> getAllLoans() {
		return new ArrayList<>(loans);
	}

	public Loan returnLoan(String loanId) {
		ValidationUtil.requireNonBlank(loanId, "loanId");
		Loan loan = findById(loanId);

		if (loan.isReturned()) {
			throw new IllegalArgumentException("Loan already returned: " + loanId);
		}

		loan.setReturned(true);
		bookService.returnOneCopy(loan.getBookId());
		return loan;
	}

	private Loan findById(String loanId) {
		return loans.stream()
				.filter(loan -> loan.getId().equals(loanId))
				.findFirst()
				.orElseThrow(() -> new ResourceNotFoundException("Loan not found: " + loanId));
	}
}
