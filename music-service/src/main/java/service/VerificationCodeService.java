package service;

import org.springframework.stereotype.Service;
import util.VerificationCodeUtil;
import javax.annotation.Resource;
import java.util.Map;

/**
 * @author ZAY
 * 验证码业务逻辑
 */
@Service
public class VerificationCodeService {
    @Resource
    private RedisService redisService;
    /**
     * 生成验证码
     * @param key 要当作key查找到验证码的字段,可以是手机号或邮箱
     * @return 验证码工具生成的map
     */
    public Map<String,Object> getVerificationCode(String key){
        Map<String,Object> codeMap= VerificationCodeUtil.generateVerificationCode();
        //将请求中的账号作为key,生成的验证码的结果作为value,存入Redis中,有效期为120秒
        redisService.set(key,codeMap.get("result").toString(),120);
        return codeMap;
    }
    /**
     * 校验输入的验证码是否正确
     * @param key 要查询 "谁" 的验证码
     * @param code 待校验是否正确的验证码
     * @return 该验证码是否正确
     */
    public boolean checkVerificationCode(String key,String code){
        String value= redisService.get(key);
        return code.equals(value);
    }
}
