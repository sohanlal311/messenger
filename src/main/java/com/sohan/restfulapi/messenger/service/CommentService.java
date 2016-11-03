package com.sohan.restfulapi.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;

import com.sohan.restfulapi.messenger.database.DatabaseCache;
import com.sohan.restfulapi.messenger.model.Comment;
import com.sohan.restfulapi.messenger.model.Message;

public class CommentService {

	private static Map<Long, Message> messages = DatabaseCache.getMessages();

	@GET
	public List<Comment> getAllComments(long messageId) {
		List<Comment> result = new ArrayList<Comment>();
		Message message = messages.get(messageId);
		if (message != null) {
			Map<Long, Comment> comments = message.getComments();
			if (comments != null) {
				result = new ArrayList<Comment>(comments.values());
			}
		}
		return result;
	}

	public List<Comment> getAllCommentsByYear(long messageId, int year) {
		List<Comment> list = new ArrayList<Comment>();
		Calendar cal = Calendar.getInstance();
		for (Comment comment : getAllComments(messageId)) {
			cal.setTime(comment.getDateCreated());
			if (cal.get(Calendar.YEAR) == year) {
				list.add(comment);
			}
		}
		return list;
	}

	public List<Comment> getAllCommentsPaginated(long messageId, int start, int size) {
		List<Comment> list = getAllComments(messageId);
		if (start < 0 || start >= list.size() || start + size > list.size())
			return new ArrayList<Comment>();
		return list.subList(start, start + size);
	}

	public Comment getComment(long messageId, long commentId) {
		Comment result = null;
		Map<Long, Comment> comments = getAllCommentsMap(messageId);
		if (comments != null) {
			result = comments.get(commentId);
		}
		return result;
	}

	public boolean addComment(long messageId, Comment comment) {
		boolean isAdded = false;
		Map<Long, Comment> comments = getAllCommentsMap(messageId);
		if (comments != null) {
			comment.setId(comments.size() + 1);
			comments.put(comment.getId(), comment);
			isAdded = true;
		}
		return isAdded;
	}

	public boolean updateComment(long messageId, Comment comment) {
		boolean isUpdated = false;
		if (comment.getId() <= 0) {
			return isUpdated;
		}

		Map<Long, Comment> comments = getAllCommentsMap(messageId);
		if (comments != null) {
			comments.put(comment.getId(), comment);
			isUpdated = true;
		}

		return isUpdated;
	}

	public boolean deleteComment(long messageId, long commentId) {
		boolean isDeleted = false;
		if (commentId <= 0) {
			return isDeleted;
		}

		Map<Long, Comment> comments = getAllCommentsMap(messageId);
		if (comments != null) {
			comments.remove(commentId);
			isDeleted = true;
		}
		return isDeleted;
	}

	public Map<Long, Comment> getAllCommentsMap(long messageId) {
		Message message = messages.get(messageId);
		if (message != null) {
			return message.getComments();
		}
		return null;
	}
}
