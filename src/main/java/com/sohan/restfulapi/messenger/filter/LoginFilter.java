package com.sohan.restfulapi.messenger.filter;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.util.Base64;

@Provider
public class LoginFilter implements ContainerRequestFilter {

	private static final String AUTHORIZATION_KEY = "Authorization";
	private static final String BASIC_AUTH_KEY = "Basic ";
	private static final String SECURED = "messages";

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		System.out.println("Coming here.." + requestContext.getUriInfo().getPath());
		if (requestContext.getUriInfo().getPath().contains(SECURED)) {
			MultivaluedMap<String, String> headers = requestContext.getHeaders();
			List<String> webTokens = headers.get(AUTHORIZATION_KEY);
			if (webTokens != null && webTokens.size() > 0) {
				String webToken = webTokens.get(0).replace(BASIC_AUTH_KEY, "");
				String decodedStr = Base64.decodeAsString(webToken);
				StringTokenizer tokenizer = new StringTokenizer(decodedStr, ":");
				String userName = tokenizer.nextToken();
				String password = tokenizer.nextToken();
				if ("user".equals(userName) && "pass".equals(password)) {
					return; // Return successfully
				}
			}

			// Abort the request with error
			Response response = Response.status(Status.UNAUTHORIZED).entity("User cannot access this resource.").build();
			requestContext.abortWith(response);
		}
	}

}
