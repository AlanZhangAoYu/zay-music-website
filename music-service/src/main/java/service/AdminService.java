package service;

import mapper.AdminMapper;
import org.springframework.stereotype.Service;
import pojo.Admin;
import util.Md5Util;
import util.TokenUtil;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author ZAY
 */
@Service
public class AdminService {
    @Resource
    private AdminMapper adminMapper;
    public HashMap<String,String> adminLoginService(String adminAccount,String adminPassword) {
        ArrayList<Admin> list=adminMapper.selectAdminByAccount(adminAccount);
        HashMap<String,String> map=new HashMap<>(3);
        if(list.size() == 0){
            //数据库中没有查到该管理员
            map.put("code","1");
            map.put("msg","Not Found Admin");
            return map;
        }
        if(list.get(0).getAdminPassword().equals(Md5Util.getMd5Str(adminPassword))){
            //查到了且密码正确
            String token= TokenUtil.signToken(adminAccount,adminPassword);
            String adminName=list.get(0).getAdminName();
            map.put("code","0");
            map.put("adminName",adminName);
            map.put("token",token);
            return map;
        }
        //查到了但密码错误
        map.put("code","2");
        map.put("msg","Password Error");
        return map;
    }
}
