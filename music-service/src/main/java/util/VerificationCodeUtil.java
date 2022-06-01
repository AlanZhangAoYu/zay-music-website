package util;

import java.util.*;

/**
 * @author ZAY
 * 验证码生成工具
 * 生成类似于
 * 2x5=
 * 3+6=
 * 这种验证码
 */
public class VerificationCodeUtil {
    public static Map<String,Object> generateVerificationCode(){
        Random random=new Random();
        /*
         规则:
         第一个数字与第二个数字都是一位整数(0-9)
         运算符为 + 和 x
         */
        Map<String,Object> resultMap=new HashMap<>(2);
        int firstNum = random.nextInt(10);
        int secondNum= random.nextInt(10);
        char[] chars={'+','x'};
        int operator = random.nextInt(2);
        int result;
        if(operator == 0){
            result = firstNum + secondNum;
        }else {
            result = firstNum * secondNum;
        }
        String code = new StringBuffer().append(firstNum).append(chars[operator]).append(secondNum).append('=').toString();
        resultMap.put("code",code);
        resultMap.put("result",result);
        return resultMap;
    }
}
