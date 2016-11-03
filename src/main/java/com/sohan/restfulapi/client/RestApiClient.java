package com.sohan.restfulapi.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sohan.restfulapi.messenger.model.Message;

public class RestApiClient {

	public static void main(String[] args) {
		Client client = ClientBuilder.newClient();
		WebTarget baseTarget = client.target("http://localhost:8080/messenger/webapi/");
		WebTarget messagesTarget = baseTarget.path("messages");
		WebTarget singleMessageTarget = messagesTarget.path("{messageId}");

		// POST Request
		Message message = new Message(3, "Pankaj", "Namastey ji");
		Builder builder = messagesTarget.request(MediaType.APPLICATION_JSON);
		Response postResponse = builder.post(Entity.json(message));
		if (postResponse.getStatus() != 201) {
			System.out.println("Error in POST");
		} else {
			System.out.println(postResponse.readEntity(String.class));
		}

		// GET Request
		builder = singleMessageTarget.resolveTemplate("messageId", "3").request(MediaType.APPLICATION_JSON);
		Response getResponse = builder.get();
		System.out.println(getResponse.readEntity(String.class));

		builder = singleMessageTarget.resolveTemplate("messageId", "2").request(MediaType.APPLICATION_JSON);
		Message msg = builder.get(Message.class);
		System.out.println(msg.getMessage());
	}

}
