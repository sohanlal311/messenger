package com.sohan.restfulapi.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.sohan.restfulapi.messenger.model.ExceptionMessage;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

	@Override
	public Response toResponse(DataNotFoundException ex) {
		ExceptionMessage exceptionMessage = new ExceptionMessage(404, ex.getMessage(), "http://www.sohan.com");
		return Response.status(Status.NOT_FOUND).entity(exceptionMessage).build();
	}

}
