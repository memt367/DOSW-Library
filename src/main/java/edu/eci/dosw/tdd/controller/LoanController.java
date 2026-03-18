package edu.eci.dosw.tdd.controller;

import edu.eci.dosw.tdd.model.Loan;
import edu.eci.dosw.tdd.service.LoanService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {

	private final LoanService loanService;

	public LoanController(LoanService loanService) {
		this.loanService = loanService;
	}

	@PostMapping
	public Loan createLoan(@RequestBody CreateLoanRequest request) {
		return loanService.createLoan(request.getUserId(), request.getBookId(), request.getLoanDays());
	}

	@GetMapping
	public List<Loan> getLoans() {
		return loanService.getAllLoans();
	}

	@PostMapping("/{loanId}/return")
	public Loan returnLoan(@PathVariable String loanId) {
		return loanService.returnLoan(loanId);
	}

	public static class CreateLoanRequest {
		private String userId;
		private String bookId;
		private int loanDays;

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public String getBookId() {
			return bookId;
		}

		public void setBookId(String bookId) {
			this.bookId = bookId;
		}

		public int getLoanDays() {
			return loanDays;
		}

		public void setLoanDays(int loanDays) {
			this.loanDays = loanDays;
		}
	}
}
