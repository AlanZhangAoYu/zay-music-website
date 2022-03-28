package util;

import java.util.Random;

/**
 * @author ZAY
 * 生成一个六位的包含小写字母和数字的随机码
 */
public class RandomSaltUtil {
    public static String generateRandomCode(){
        Random random=new Random();
        StringBuilder builder=new StringBuilder();
        char[] charList={'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v',
                'w','x','y','z','0','1','2','3','4','5','6','7','8','9'};
        for(int i=0;i<6;i++){
            builder.append(charList[random.nextInt(36)]);
        }
        return builder.toString();
    }
}
