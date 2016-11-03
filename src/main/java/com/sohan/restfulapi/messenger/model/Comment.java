package com.sohan.restfulapi.messenger.model;

import java.util.Date;

public class Comment {
	private long id;
	private String author;
	private String message;
	private Date dateCreated;

	public Comment() {

	}

	public Comment(long id, String author, String message) {
		this.id = id;
		this.author = author;
		this.message = message;
		this.dateCreated = new Date();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

}
