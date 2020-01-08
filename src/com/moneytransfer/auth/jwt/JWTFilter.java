package com.moneytransfer.auth.jwt;


import java.lang.reflect.Method;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;


@Provider
public class JWTFilter implements javax.ws.rs.container.ContainerRequestFilter {
	
	@Context
    private ResourceInfo resourceInfo;
	
    @Override
    public void filter(ContainerRequestContext requestContext) {
    	
    	// check if resource method has a rolesAllowed "user"
    	Method method = resourceInfo.getResourceMethod();
    	if(method.isAnnotationPresent(RolesAllowed.class)){
    		
    		// extract token from header "auth"
    		String authKey = requestContext.getHeaderString("Authorization");
    		
    		if (authKey != null) {
    			String token = authKey.split(" ")[1]; // bearer <auth key> format
    			// call method authManager isTokenValid
            	if (!JWTToken.validateJWT(token)) {
            		requestContext.abortWith(Response.status(Status.FORBIDDEN).build());
            		return;
            	}
            	
    		} else {
    			
    			// return 403 forbidden if token is invalid
    			requestContext.abortWith(Response.status(Status.FORBIDDEN).build());
    			return;
    			
    		}
        	    		
    	}
    }
	

}