package com.moneytransfer.auth.jwt;

public class jwtIssuer {
	public static String getId() {
		return "moneytransfer";
	}
	public static String getIssuer() {
		return "moneytransfer.com"; // change to your own domain name
	}
}
