package service;

import mapper.UserMapper;
import org.springframework.stereotype.Service;
import pojo.User;
import util.Md5Util;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author ZAY
 */
@Service
public class UserService {
    @Resource
    private UserMapper userMapper;
    public ArrayList<User> selectAllUser(){
        return userMapper.selectAllUser();
    }
    public String userLoginService(String userAccount,String userPassword){
        ArrayList<User> list=userMapper.selectUserByAccount(userAccount);
        if(list.size() == 0){
            return "";
        }
        userPassword = userPassword+list.get(0).getSalt();
        if(Md5Util.getMd5Str(userPassword).equals(list.get(0).getUserPassword())){
            return list.get(0).getUserName();
        }
        return "";
    }
    public int selectAllUserCount(){
        return userMapper.selectAllUserCount();
    }
    public int userRegisterService(User user){
        return userMapper.insertUser(user);
    }
}
