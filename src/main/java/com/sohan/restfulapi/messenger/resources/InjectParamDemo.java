package com.sohan.restfulapi.messenger.resources;

import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("injectDemo")
public class InjectParamDemo {

	@GET
	@Path("annotations")
	@Produces(MediaType.TEXT_PLAIN)
	public String getParamsUsingAnnotations(@MatrixParam("param") String matrixParam,
			@HeaderParam("authSessionId") String sessionId, @CookieParam("name") String cookieParam) {
		return "Matrix Param: " + matrixParam + " \nHeader Param: " + sessionId + " \nCookie Param: " + cookieParam;
	}

	@GET
	@Path("context")
	public String getParamsUsingContext(@Context UriInfo uriInfo, @Context HttpHeaders httpHeaders) {
		return "url: " + uriInfo.getAbsolutePath() + " \nCookie : " + httpHeaders.getCookies().toString();
	}

	@GET
	@Path("test")
	public String testWithoutAnnotations(UriInfo uriInfo) {
		return "url: ";
	}
}
