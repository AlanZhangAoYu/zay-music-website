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
            return "";
        }
        if(list.get(0).getAdminPassword().equals(Md5Util.getMd5Str(adminPassword))){
            return list.get(0).getAdminName();
        }
        return "";
    }
}
