package controller;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.RedisService;
import util.VerificationCodeUtil;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
/**
 * @author ZAY
 * 验证码接口
 * 检查验证码是否正确在用户登录逻辑逻辑中，不在这里
 */
@RestController
public class VerificationCodeController {
    @Resource
    private RedisService redisService;
    /**
     * 前端获取验证码时请求的接口
     * @return 返回一个随机验证码
     */
    @GetMapping(value = "/getVerificationCode")
    public String getVerificationCode(@RequestParam("userName") String userName){
        Map<String,Object> codeMap= VerificationCodeUtil.generateVerificationCode();
        Map<String,Object> resultMap=new HashMap<>(1);
        //将请求中的账号作为key,生成的验证码的结果作为value,存入Redis中,有效期为120秒
        redisService.set(userName,codeMap.get("result").toString(),120);
        //将生成的验证码作为响应发送回前端
        resultMap.put("code",codeMap.get("code"));
        return JSON.toJSONString(resultMap);
    }
}
