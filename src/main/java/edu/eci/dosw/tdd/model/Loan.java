package edu.eci.dosw.tdd.model;

import java.time.LocalDate;

public class Loan {

	private String id;
	private String userId;
	private String bookId;
	private LocalDate startDate;
	private LocalDate dueDate;
	private boolean returned;

	public Loan() {
	}

	public Loan(String id, String userId, String bookId, LocalDate startDate, LocalDate dueDate, boolean returned) {
		this.id = id;
		this.userId = userId;
		this.bookId = bookId;
		this.startDate = startDate;
		this.dueDate = dueDate;
		this.returned = returned;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public boolean isReturned() {
		return returned;
	}

	public void setReturned(boolean returned) {
		this.returned = returned;
	}
}
