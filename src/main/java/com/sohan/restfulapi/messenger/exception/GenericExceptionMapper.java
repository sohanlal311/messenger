package com.sohan.restfulapi.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.sohan.restfulapi.messenger.model.ExceptionMessage;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable ex) {
		ExceptionMessage exceptionMessage = new ExceptionMessage(404, ex.getMessage(), "http://www.sohan.com");
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(exceptionMessage).build();
	}

}
