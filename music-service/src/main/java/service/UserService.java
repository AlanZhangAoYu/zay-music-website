package service;

import com.github.pagehelper.PageHelper;
import mapper.UserMapper;
import org.springframework.stereotype.Service;
import pojo.User;
import util.Md5Util;
import util.RandomSaltUtil;
import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

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
