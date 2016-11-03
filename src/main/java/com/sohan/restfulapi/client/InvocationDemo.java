package com.sohan.restfulapi.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class InvocationDemo {

	public static void main(String[] args) {
		Invocation invocation = prepareRequestForMessageByYear(2016);
		Response getResponse = invocation.invoke();
		System.out.println(getResponse.readEntity(String.class));
	}

	private static Invocation prepareRequestForMessageByYear(int year) {
		Client client = ClientBuilder.newClient();
		return client.target("http://localhost:8080/messenger/webapi/").path("messages").queryParam("year", year)
				.request(MediaType.APPLICATION_JSON).buildGet();
	}

}
