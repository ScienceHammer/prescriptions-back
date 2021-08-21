package app.core.security;

public class SecurityConstrains {
	
	public static final String SIGN_UP_URL = "/api/users/**";
	public static final String H2_URL = "h2-console/**";
	public static final String SECRET = "this+is+my+key+and+it+must+be+at+least+256+bits+long";
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final long EXPRIATION_TIME = 30_000;

}
