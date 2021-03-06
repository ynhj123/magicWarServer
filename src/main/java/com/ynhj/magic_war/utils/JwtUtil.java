package com.ynhj.magic_war.utils;

import com.ynhj.magic_war.model.entity.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String SUBJECT = "wtc";

    public static final long EXPIRITION = 1000 * 24 * 60 * 60 * 7;

    public static final String APPSECRET_KEY = "wtc_secret";

    private static final String ROLE_CLAIMS = "rol";

//    public static String generateJsonWebToken(User user) {
//
//        if (user.getId() == null || user.getUsername() == null || user.getFaceImage() == null) {
//            return null;
//        }
//
//        Map<String, Object> map = new HashMap<>();
//        map.put(ROLE_CLAIMS, "rol");
//
//        String token = Jwts
//                .builder()
//                .setSubject(SUBJECT)
//                .setClaims(map)
//                .claim("id", user.getId())
//                .claim("name", user.getUsername())
//                .claim("img", user.getFaceImage())
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + EXPIRITION))
//                .signWith(SignatureAlgorithm.HS256, APPSECRET_KEY).compact();
//        return token;
//    }

    /**
     * 生成token
     *
     * @param username
     * @param role
     * @return
     */
    public static String createToken(String username, String role) {

        Map<String, Object> map = new HashMap<>();
        map.put(ROLE_CLAIMS, role);

        String token = Jwts
                .builder()
                .setSubject(username)
                .setClaims(map)
                .claim("username", username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRITION))
                .signWith(SignatureAlgorithm.HS256, APPSECRET_KEY).compact();
        return token;
    }

    public static String createToken(JwtUser user) {

        Map<String, Object> map = new HashMap<>();
        map.put("uid", user.getId());
        map.put("username", user.getUsername());

        String token = Jwts
                .builder()
                .setSubject(user.getUsername())
                .setClaims(map)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRITION))
                .signWith(SignatureAlgorithm.HS256, APPSECRET_KEY).compact();
        return token;
    }

    public static JwtUser getJwtUser(String token) {
        Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
        JwtUser user = new JwtUser(claims.get("uid").toString(), claims.get("username").toString());
        return user;
    }

    public static String createToken(Map<String, Object> map) {
        String token = Jwts
                .builder()
                .setSubject(SUBJECT)
                .setClaims(map)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRITION))
                .signWith(SignatureAlgorithm.HS256, APPSECRET_KEY).compact();
        return token;
    }

    public static String createToken(String ploy) {
        Map<String, Object> hashMap = new HashMap<String, Object>() {{
            put("alg", "HS256");
            put("typ", "JWT");
        }};
        String token = Jwts
                .builder()
                .setHeader(hashMap)
                .setPayload(ploy)
                .signWith(SignatureAlgorithm.HS256, APPSECRET_KEY).compact();
        return token;
    }

    public static Claims checkJWT(String token) {
        try {
            final Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
            return claims;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取用户名
     *
     * @param token
     * @return
     */
    public static String getUsername(String token) {
        Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
        return claims.get("username").toString();
    }


    /**
     * 获取用户角色
     *
     * @param token
     * @return
     */
    public static String getUserRole(String token) {
        Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
        return claims.get("rol").toString();
    }

    /**
     * 是否过期
     *
     * @param token
     * @return
     */
    public static boolean isExpiration(String token) {
        Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
        return claims.getExpiration().before(new Date());
    }

//    public static void main(String[] args) {
//        String name = "acong";
//        String role = "rol";
//        String token = createToken(name, role);
//        System.out.println(token);
//
//        Claims claims = checkJWT(token);
//        String string = JSON.toJSONString(claims);
//        System.out.println(claims.get("username"));
//
//        System.out.println(getUsername(token));
//        System.out.println(getUserRole(token));
//        System.out.println(isExpiration(token));
////        User user = new User();
////        user.setFaceImage("a");
////        user.setId("1");
////        user.setRoleId("role");
////        String userStr = JSON.toJSONString(user);
////        String token = createToken(userStr);
////        Claims claims = checkJWT(token);
////        boolean expiration = isExpiration(token);
//
//
//    }


    /**
     * eyJhbGciOiJIUzI1NiJ9.
     * eyJzdWIiOiJjb25nZ2UiLCJpZCI6IjExMDExIiwibmFtZSI6Im51b3dlaXNpa2kiLCJpbWciOiJ3d3cudW9rby5jb20vMS5wbmciLCJpYXQiOjE1NTQ5OTI1NzksImV4cCI6MTU1NTU5NzM3OX0.
     * 6DJ9En-UBcTiMRldZeevJq3e1NxJgOWryUyim4_-tEE
     *
     * @param args
     */

	/*public static void main(String[] args) {

		Users user = new Users();
		user.setId("11011");
		user.setUserName("nuoweisiki");
		user.setFaceImage("www.uoko.com/1.png");
		String token = generateJsonWebToken(user);

		System.out.println(token);

		Claims claims = checkJWT(token);
		if (claims != null) {
			String id = claims.get("id").toString();
			String name = claims.get("name").toString();
			String img = claims.get("img").toString();

			String rol = claims.get("rol").toString();

			System.out.println("id:" + id);
			System.out.println("name:" + name);
			System.out.println("img:" + img);

			System.out.println("rol:" + rol);



		}

	}*/

}

