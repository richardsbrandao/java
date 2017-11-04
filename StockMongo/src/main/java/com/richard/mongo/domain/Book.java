package com.richard.mongo.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Book {

	@Id
	private String id;
	private String number;
	private String series;
	private String author;
	private List<BookDetails> books;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getSeries() {
		return series;
	}
	public void setSeries(String series) {
		this.series = series;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public List<BookDetails> getBooks() {
		return books;
	}
	public void setBooks(List<BookDetails> books) {
		this.books = books;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", number=" + number + ", series=" + series
				+ ", author=" + author + ", books=" + books + "]";
	}

}
