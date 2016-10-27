package com.app.server.businessservice.appbasicsetup.aaa;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

public class TokenGenerator {

	public String generateToken(String algorithm, String key, String loginId, Date expirationDate, HttpSession session) {
		/****
		 * Set signature algorithm
		 */
		SignatureAlgorithm signatureAlgorithm = getAlgorhythm(algorithm);

		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);

		/****
		 * We will sign our JWT with our ApiKey secret
		 */
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(key);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		/****
		 * Let's set the JWT Claims
		 */
		JwtBuilder builder = Jwts.builder().setId("1").setIssuedAt(now).setSubject(loginId).signWith(signatureAlgorithm, signingKey);

		/****
		 * set session vaiables in payload
		 */
		Enumeration<String> listOfAttribute = session.getAttributeNames();
		Map<String, Object> mapOfSession = new HashMap<String, Object>();
		while (listOfAttribute.hasMoreElements()) {
			String name = (String) listOfAttribute.nextElement();
			if (!name.equalsIgnoreCase(session.getAttribute("usidHash").toString())) {
				mapOfSession.put(name, session.getAttribute(name));
			}
		}
		builder.setClaims(mapOfSession);

		/****
		 * if it has been specified, let's add the expiration
		 */
		builder.setExpiration(expirationDate);

		/****
		 * Builds the JWT and serializes it to a compact, URL-safe string
		 */
		return builder.compact();
	}

	public SignatureAlgorithm getAlgorhythm(String algoType) {
		switch (algoType) {
		case "HS256":
			return SignatureAlgorithm.HS256;
		case "HS384":
			return SignatureAlgorithm.HS384;
		case "HS512":
			return SignatureAlgorithm.HS512;
		case "RS256":
			return SignatureAlgorithm.RS256;
		case "RS384":
			return SignatureAlgorithm.RS384;
		case "RS512":
			return SignatureAlgorithm.RS512;
		case "ES256":
			return SignatureAlgorithm.ES256;
		case "ES384":
			return SignatureAlgorithm.ES384;
		case "ES512":
			return SignatureAlgorithm.ES512;
		case "PS256":
			return SignatureAlgorithm.PS256;
		case "PS384":
			return SignatureAlgorithm.PS384;
		}
		return null;
	}

}
