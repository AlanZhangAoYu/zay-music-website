package util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Date;

/**
 * @author ZAY
 */
public class TokenUtil {
    /**
     * token生成
     * @param account 输对了的账号
     * @param password 输对了的密码
     * @return 生成的token
     */
    public static String signToken(String account,String password){
        String token = null;
        try {
            Date date = new Date(System.currentTimeMillis() + GlobalConstant.TOKEN_EXPIRE_TIME);
            JWTCreator.Builder builder = JWT.create();
            builder.withIssuer("auth0");
            builder.withClaim("Account",account);
            builder.withClaim("Password",password);
            builder.withExpiresAt(date);
            token = builder.sign(Algorithm.HMAC256(GlobalConstant.TOKEN_SECRET));
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
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(GlobalConstant.TOKEN_SECRET)).withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);
            /*System.out.println("认证通过:");
            System.out.println("username:" + jwt.getClaim("username").asString());
            System.out.println("过期时间:" + jwt.getExpiresAt());*/
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
