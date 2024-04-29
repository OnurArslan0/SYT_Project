package com.SYT.STY_project.util;

import org.springframework.stereotype.Component;
import java.util.Date;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenUtil {
    private static final String SECRET_KEY = "SunucuYazilimTeknolojisiDersiBirinciDonemOdeviVeAciklamasiveKoduSunucuYazilimTeknolojisiDersiBirinciDonemOdeviVeAciklamasiveKoduSunucuYazilimTeknolojisiDersiBirinciDonemOdeviVeAciklamasiveKoduSunucuYazilimTeknolojisiDersiBirinciDonemOdeviVeAciklamasiveKoduSunucuYazilimTeknolojisiDersiBirinciDonemOdeviVeAciklamasiveKoduSunucuYazilimTeknolojisiDersiBirinciDonemOdeviVeAciklamasiveKoduSunucuYazilimTeknolojisiDersiBirinciDonemOdeviVeAciklamasiveKoduSunucuYazilimTeknolojisiDersiBirinciDonemOdeviVeAciklamasiveKoduSunucuYazilimTeknolojisiDersiBirinciDonemOdeviVeAciklamasiveKoduSunucuYazilimTeknolojisiDersiBirinciDonemOdeviVeAciklamasiveKodu";
    private static final long EXPIRATION_TIME = 20000000;

    public String generateToken(String mail) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(mail)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public String getEmailFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}

