package com.sohan.restfulapi.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.sohan.restfulapi.messenger.database.DatabaseCache;
import com.sohan.restfulapi.messenger.exception.DataNotFoundException;
import com.sohan.restfulapi.messenger.model.Comment;
import com.sohan.restfulapi.messenger.model.Message;

public class MessageService {

	private static Map<Long, Message> messages = DatabaseCache.getMessages();

	static {
		Message m1 = new Message(1L, "sohanlal311", "Hello World!");
		Message m2 = new Message(2L, "sonujalap", "Hello Jersey!");
		messages.put(m1.getId(), m1);
		messages.put(m2.getId(), m2);

		Comment c11 = new Comment(101L, "Kuldeep", "Wah bhai 11");
		Comment c12 = new Comment(102L, "Pankaj", "Wah bhai 12");
		m1.getComments().put(c11.getId(), c11);
		m1.getComments().put(c12.getId(), c12);

		Comment c21 = new Comment(103L, "Anurag", "Good job 21");
		Comment c22 = new Comment(104L, "Sameer", "Good job 22");
		m2.getComments().put(c21.getId(), c21);
		m2.getComments().put(c22.getId(), c22);
	}

	public List<Message> getAllMessages() {
		return new ArrayList<Message>(messages.values());
	}

	public List<Message> getAllMessagesByYear(int year) {
		List<Message> list = new ArrayList<Message>();
		Calendar cal = Calendar.getInstance();
		for (Message m : getAllMessages()) {
			cal.setTime(m.getDateCreated());
			if (cal.get(Calendar.YEAR) == year) {
				list.add(m);
			}
		}
		return list;
	}

	public List<Message> getAllMessagesPaginated(int start, int size) {
		List<Message> list = new ArrayList<Message>(messages.values());
		if (start < 0 || start >= list.size() || start + size > list.size())
			return new ArrayList<Message>();
		return list.subList(start, start + size);
	}

	public Message getMessage(long id) {
		Message message = messages.get(id);
		if (message == null) {
			throw new DataNotFoundException("Message for id " + id + " not found");
		}
		return message;
	}

	public Message addMessage(Message message) {
		message.setId(messages.size() + 1);
		messages.put(message.getId(), message);
		return message;
	}

	public Message updateMessage(Message message) {
		if (message.getId() <= 0) {
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}

	public void deleteMessage(long id) {
		messages.remove(id);
	}
}
