package com.sohan.restfulapi.messenger.resources;

import java.net.URI;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.sohan.restfulapi.messenger.beans.FilterBean;
import com.sohan.restfulapi.messenger.model.Message;
import com.sohan.restfulapi.messenger.service.MessageService;

@Path("/messages")
@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class MessageResource {

	private MessageService messageService = new MessageService();

	@Context
	private UriInfo uriInfo;

	@GET
	public List<Message> getMessages(@BeanParam FilterBean filterBean) {
		if (filterBean.getYear() > 0) {
			return messageService.getAllMessagesByYear(filterBean.getYear());
		}
		if (filterBean.getStart() >= 0 && filterBean.getSize() > 0) {
			return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
		}
		List<Message> messages = messageService.getAllMessages();
		for (Message message : messages) {
			addLinks(message, uriInfo);
		}
		return messages;
	}

	@POST
	public Response addMessage(Message message) {
		Message newMessage = messageService.addMessage(message);
		addLinks(message, uriInfo);
		// return Response.status(Status.CREATED).entity(newMessage).build();
		URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(newMessage.getId())).build();
		return Response.created(uri).entity(newMessage).build();
	}

	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long id) {
		Message message = messageService.getMessage(id);
		addLinks(message, uriInfo);
		return message;
	}

	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long id, Message message) {
		message.setId(id);
		Message updatedMessage = messageService.updateMessage(message);
		addLinks(updatedMessage, uriInfo);
		return updatedMessage;
	}

	@DELETE
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId") long id) {
		messageService.deleteMessage(id);
	}

	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
	}

	private void addLinks(Message message, UriInfo uriInfo) {
		if (message.getLinks().size() == 0) {
			message.addLink(getLinkForSelf(uriInfo, message), "self");
			message.addLink(getLinkForProfile(uriInfo, message), "profile");
			message.addLink(getLinkForComments(uriInfo, message), "comments");
		}
	}

	private String getLinkForSelf(UriInfo uriInfo, Message message) {
		return uriInfo.getBaseUriBuilder().path(MessageResource.class).path(Long.toString(message.getId())).build()
				.toString();
	}

	private String getLinkForProfile(UriInfo uriInfo, Message message) {
		return uriInfo.getBaseUriBuilder().path(ProfileResource.class).path(message.getAuthor()).build().toString();
	}

	private String getLinkForComments(UriInfo uriInfo, Message message) {
		return uriInfo.getBaseUriBuilder().path(MessageResource.class).path(MessageResource.class, "getCommentResource")
				.path(CommentResource.class).resolveTemplate("messageId", message.getId()).build().toString();
	}

}
