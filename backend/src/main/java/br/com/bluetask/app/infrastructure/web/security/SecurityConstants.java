package br.com.bluetask.app.infrastructure.web.security;

public class SecurityConstants {

	public static final long EXPIRATION_TIME = 86400000;
	public static final String SECRET_KEY = "tHeSeCrEtKey";
	public static final String AUTHORIZATION_HEADER = "Authorization";
	public static final String TOKEN_PREFIX = "Bearer ";
}
