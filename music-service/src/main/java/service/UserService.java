package service;

import com.github.pagehelper.PageHelper;
import mapper.UserMapper;
import org.springframework.stereotype.Service;
import pojo.User;
import util.Md5Util;
import util.RandomSaltUtil;
import util.TokenUtil;
import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * @author ZAY
 */
@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    /**
     * 分页查询所有用户信息
     * @param page 当前页码
     * @return 根据当前页码返回的分页结果
     */
    public ArrayList<User> selectAllUser(int page){
        PageHelper.startPage(page,10);
        ArrayList<User> list=userMapper.selectAllUser();
        return list;
    }
    public HashMap<String,String> userLoginService(String userAccount, String userPassword){
        ArrayList<User> list=userMapper.selectUserByAccount(userAccount);
        HashMap<String,String> map=new HashMap<>(2);
        map.put("userName","");
        map.put("token","");
        if(list.size() == 0){
            return map;
        }
        userPassword = userPassword+list.get(0).getSalt();
        if(Md5Util.getMd5Str(userPassword).equals(list.get(0).getUserPassword())){
            String token = TokenUtil.signToken(userAccount,userPassword);
            String userName = list.get(0).getUserName();
            map.put("userName",userName);
            map.put("token",token);
            return map;
        }
        return map;
    }
    public int selectAllUserCount(){
        return userMapper.selectAllUserCount();
    }
    public int userRegisterService(User user){
        String salt= RandomSaltUtil.generateRandomCode();
        user.setSalt(salt);
        user.setUserPassword(Md5Util.getMd5Str(user.getUserPassword()+salt));
        Date date=new Date();
        Timestamp createTime = new Timestamp(date.getTime());
        user.setCreateTime(createTime);
        user.setUpdateTime(createTime);
        return userMapper.insertUser(user);
    }
}
