package com.sohan.restfulapi.messenger.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class Message {
	private long id;
	private String author;
	private String message;
	private Date dateCreated;
	private Map<Long, Comment> comments = new HashMap<Long, Comment>();
	private List<Link> links = new ArrayList<Link>();

	public Message() {

	}

	public Message(long id, String author, String message) {
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

	@XmlTransient
	public Map<Long, Comment> getComments() {
		return comments;
	}

	public void setComments(Map<Long, Comment> comments) {
		this.comments = comments;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	public void addLink(String link, String rel) {
		links.add(new Link(link, rel));
	}

	@Override
	public String toString() {
		return "id : " + id + "\nauthor: " + author + "\nmessage: " + message + "\ndateCreated: " + dateCreated
				+ "\nlinks: " + links;
	}

}
