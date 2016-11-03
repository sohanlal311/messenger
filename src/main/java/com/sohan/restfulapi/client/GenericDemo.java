package com.sohan.restfulapi.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import com.sohan.restfulapi.messenger.model.Message;

public class GenericDemo {

	public static void main(String[] args) {
		Invocation invocation = prepareRequestForMessageByYear(2016);
		List<Message> list = invocation.invoke(new GenericType<List<Message>>() {
		});
		System.out.println(list.size());
	}

	private static Invocation prepareRequestForMessageByYear(int year) {
		Client client = ClientBuilder.newClient();
		return client.target("http://localhost:8080/messenger/webapi/").path("messages").queryParam("year", year)
				.request(MediaType.APPLICATION_JSON).buildGet();
	}

}
