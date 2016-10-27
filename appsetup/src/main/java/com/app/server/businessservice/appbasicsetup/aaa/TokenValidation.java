package com.app.server.businessservice.appbasicsetup.aaa;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.xml.bind.DatatypeConverter;

public class TokenValidation {

	public boolean validateToken(String token, String key) {
		if (token != null) {
			try {
				Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(key)).parseClaimsJws(token).getBody();
				if (claims.getExpiration().getTime() < new Date().getTime()) {
					System.out.println("TOKEN IS EXPIRED");
					return false;
				}
				return true;
			} catch (Exception e) {
				System.out.println("WRONG TOKEN INPUT");
			}
		}
		return false;
	}

	public Payload getPayloadData(String token, String key) {
		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(key)).parseClaimsJws(token).getBody();
		String usidHash = claims.get("usidHash").toString();
		String loginId = claims.get("loginId").toString();
		Integer userAccessCode = Integer.parseInt(claims.get("userAccessCode").toString());
		String contactId = claims.get("contactId").toString();
		String timeZoneId = claims.get("timeZoneId").toString();
		String timeZone = claims.get("timeZone").toString();
		String CookieTokenName = claims.get("CookieTokenName").toString();
		String userId = claims.get("userId").toString();
		String qKeHash = claims.get("qKeHash").toString();
		String clientIP = claims.get("clientIP").toString();
		Integer clientPort = Integer.parseInt(claims.get("clientPort").toString());
		Integer sessionTimeout = Integer.parseInt(claims.get("sessionTimeout").toString());
		return new Payload(usidHash, loginId, userAccessCode, contactId, timeZoneId, timeZone, CookieTokenName, userId, qKeHash, clientIP, clientPort, sessionTimeout);
	}

}
