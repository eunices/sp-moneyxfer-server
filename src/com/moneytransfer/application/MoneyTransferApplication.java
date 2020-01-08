package com.moneytransfer.application;
import org.glassfish.jersey.server.ResourceConfig;

public class MoneyTransferApplication extends ResourceConfig {
	public MoneyTransferApplication() {
		packages("com.moneytransfer.resources");
		register(com.moneytransfer.application.MyJacksonFeature.class);
		
		//register(com.sp.marketplaceapi.auth.BasicAuthentication.class);
		// register(com.moneytransfer.auth.jwt.BearerAuthentication.class);
	}
}

