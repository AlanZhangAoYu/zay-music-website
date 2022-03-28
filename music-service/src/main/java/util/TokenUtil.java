package util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import pojo.Admin;
import java.util.Date;

/**
 * @author ZAY
 */
public class TokenUtil {
    /**
     * 过期时间2小时
     */
    private static final long EXPIRE_TIME = 2*60*60*1000;
    /**
     * 盐加密字符串
     */
    private static final String TOKEN_SECRET="ZAY_IS_HANDSOME";

    /**
     * 管理员登录token生成
     * @param admin 输对了账号密码的管理员
     * @return 生成的token
     */
    public static String signAdmin(Admin admin){
        String token = null;
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            JWTCreator.Builder builder = JWT.create();
            builder.withIssuer("auth0");
            builder.withClaim("adminName",admin.getAdminName());
            builder.withExpiresAt(date);
            token = builder.sign(Algorithm.HMAC256(TOKEN_SECRET));
            return token;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * token验证
     * @param token 要验证的token
     * @return 返回是否放行s
     */
    public static boolean verify(String token){
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);
            System.out.println("认证通过:");
            System.out.println("username:" + jwt.getClaim("username").asString());
            System.out.println("过期时间:" + jwt.getExpiresAt());
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
