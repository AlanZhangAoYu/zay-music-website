package controller;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.VerificationCodeService;
import util.GlobalConstant;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
/**
 * @author ZAY
 * 验证码接口
 */
@RestController
public class VerificationCodeController {
    @Resource
    private VerificationCodeService verificationCodeService;
    @GetMapping(value = "/getVerificationCode")
    public String getVerificationCode(@RequestParam("userKey") String userKey){
        Map<String,Object> resultMap=new HashMap<>(1);
        Map<String,Object> codeMap= verificationCodeService.getVerificationCode(userKey);
        //将生成的验证码作为响应发送回前端
        resultMap.put("code",codeMap.get("code"));
        return JSON.toJSONString(resultMap);
    }
    @GetMapping(value = "/checkVerificationCode")
    public String checkVerificationCode(@RequestParam("userKey")String userKey,
                                        @RequestParam("code")String code){
        Map<String,Object> resultMap=new HashMap<>(1);
        if(verificationCodeService.checkVerificationCode(userKey,code)){
            resultMap.put("msg", GlobalConstant.SUCCESS);
        }else {
            resultMap.put("msg",GlobalConstant.FAIL_STR);
        }
        return JSON.toJSONString(resultMap);
    }
}
