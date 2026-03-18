package edu.eci.dosw.tdd.model;

import java.util.Objects;

public class Book {

	private String id;
	private String title;
	private String author;

	public Book() {
	}

	public Book(String id, String title, String author) {
		this.id = id;
		this.title = title;
		this.author = author;
	}

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

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (!(object instanceof Book book)) {
			return false;
		}
		return Objects.equals(id, book.id);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}
}
