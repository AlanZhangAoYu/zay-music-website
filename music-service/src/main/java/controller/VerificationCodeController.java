package controller;

import util.VerificationCodeUtil;

import java.util.Map;

/**
 * @author ZAY
 * 验证码接口
 */
public class VerificationCodeController {
    /**
     * 前端获取验证码时请求的接口
     * @return 返回一个随机验证码
     */
    public String getVerificationCode(){
        Map<String,Object> codeMap= VerificationCodeUtil.generateVerificationCode();

        return "";
    }
}
