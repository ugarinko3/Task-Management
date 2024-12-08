package org.example.managment.service;

import io.jsonwebtoken.*;
import lombok.AllArgsConstructor;
import org.example.managment.model.request.UserRequest;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
@AllArgsConstructor
public class JwtService {

    private SecretKey secret;

    public JwtService() {
        byte[] keyBytes = "2f73bb18fdf365a62cad45d8841f135dcbd6fbb1dcf5311b6240d96cde65f764".getBytes(StandardCharsets.UTF_8);
        this.secret = new SecretKeySpec(keyBytes, 0, keyBytes.length, "HmacSHA256"); // Укажите алгоритм
    }

    public String generateToken(String email, String password) {

        return Jwts.builder()
                .setSubject(email)
                .claim("email", email)
                .claim("password", password)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))  // Токен истекает через 1 час
                .signWith(secret)
                .compact();
    }


    /**
     *  Получение email и password с token
     *
     * @param token JWT
     * @return Возвращает {@link UserRequest}
     */
    public UserRequest getEmailAndPassword(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)  // Разбираем токен
                .getBody();  // Получаем тело токена

        String email = claims.get("email", String.class);
        String password = claims.get("password", String.class);

        UserRequest userRequest = new UserRequest();
        userRequest.setEmail(email);
        userRequest.setPassword(password);
        return userRequest;
    }

    public String extractEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secret) // Преобразуем секрет в массив байтов
                .build()
                .parseClaimsJws(token)  // Разбираем токен
                .getBody()
                .getSubject();  // Извлекаем email из токена
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secret)  // Указываем ключ для подписи
                    .build()
                    .parseClaimsJws(token);  // Разбираем токен

            return true;  // Токен валиден
        } catch (JwtException | IllegalArgumentException e) {
            return false;  // Токен невалиден
        }
    }
}
