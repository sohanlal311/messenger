package com.sohan.restfulapi.messenger.resources;

import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sohan.restfulapi.messenger.beans.FilterBean;
import com.sohan.restfulapi.messenger.model.Comment;
import com.sohan.restfulapi.messenger.service.CommentService;

@Path("/")
@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class CommentResource {

	private CommentService commentService = new CommentService();

	@GET
	public List<Comment> getComments(@PathParam("messageId") long messageId, @BeanParam FilterBean filterBean) {
		if (filterBean.getYear() > 0) {
			return commentService.getAllCommentsByYear(messageId, filterBean.getYear());
		}
		if (filterBean.getStart() >= 0 && filterBean.getSize() > 0) {
			return commentService.getAllCommentsPaginated(messageId, filterBean.getStart(), filterBean.getSize());
		}
		return commentService.getAllComments(messageId);
	}

	@POST
	public boolean addComment(@PathParam("messageId") long messageId, Comment comment) {
		return commentService.addComment(messageId, comment);
	}

	@GET
	@Path("/{commentId}")
	public Comment getComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {
		return commentService.getComment(messageId, commentId);
	}

	@PUT
	@Path("/{commentId}")
	public boolean updateComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId,
			Comment comment) {
		comment.setId(commentId);
		return commentService.updateComment(messageId, comment);
	}

	@DELETE
	@Path("/{commentId}")
	public boolean deleteComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {
		return commentService.deleteComment(messageId, commentId);
	}

}
