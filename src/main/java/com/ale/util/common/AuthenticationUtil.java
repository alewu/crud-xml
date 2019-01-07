package com.ale.util.common;

import com.ale.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AuthenticationUtil {

    // 密钥
    public static final String MY_SECRET = "secret";
    // JWT签发主体
    public static final String ISSUER = "alewu";
    // jackson
    private static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) {
        User u = new User("jack", "123456");
        String token = AuthenticationUtil.createJWT(u, 1000L*60L*30L);
        System.out.println(token);
        User  user = AuthenticationUtil.validateJWT(token,User.class);
        System.out.println(user.getUsername() + user.getPassword());
    }

    public static <T> String createJWT(T obj, long maxAge) {
        try {
            JWTCreator.Builder builder = JWT.create();
            builder.withHeader(createHeaderClaims())
                    .withIssuer(ISSUER)
                    .withSubject(mapper.writeValueAsString(obj));
            if (maxAge >= 0) {
                long expMillis = System.currentTimeMillis() + maxAge;
                Date exp = new Date(expMillis);
                builder.withExpiresAt(exp);
            }
            Algorithm algorithm = Algorithm.HMAC256(MY_SECRET);
            return builder.sign(algorithm);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JWTCreationException exception) {
            //Invalid Signing configuration / Couldn't convert Claims.
        }
        return null;
    }

    public static <T> T validateJWT(String token, Class<T> clazz) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(MY_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            Date exp = jwt.getExpiresAt(); // JWT的过期时间
            System.out.println(exp);
            if (exp != null && exp.after(new Date())) {
                String subject = jwt.getSubject();
                System.out.println(subject);
                return mapper.readValue(subject, clazz);
            }

        } catch (UnsupportedEncodingException exception) {
            //UTF-8 encoding not supported
            exception.printStackTrace();
        } catch (JWTVerificationException exception) {
            //Invalid signature/claims
            exception.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Map<String, Object> createHeaderClaims() {
        Map<String, Object> map = new HashMap<>(2);
        map.put("typ", "JWT");
        map.put("alg", "HS256");
        return map;
    }

}
