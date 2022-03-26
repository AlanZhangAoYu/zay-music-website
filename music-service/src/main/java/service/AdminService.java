package service;

import mapper.AdminMapper;
import org.springframework.stereotype.Service;
import pojo.Admin;
import util.Md5Util;
import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @author ZAY
 */
@Service
public class AdminService {
    @Resource
    private AdminMapper adminMapper;
    public String adminLoginService(String adminAccount,String adminPassword) {
        ArrayList<Admin> list=adminMapper.selectAdminByAccount(adminAccount);
        if(list.size() == 0){
            //数据库中没有查到该管理员
            return "";
        }
        if(list.get(0).getAdminPassword().equals(Md5Util.getMd5Str(adminPassword))){
            //查到了且密码正确
            return list.get(0).getAdminName();
        }
        //查到了但密码错误
        return "";
    }
}
