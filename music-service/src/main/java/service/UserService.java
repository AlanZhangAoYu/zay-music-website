package service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
import java.util.List;

/**
 * @author ZAY
 */
@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    /**
     * 分页查询所有用户信息
     * @param pageNum 当前页码
     * @param pageSize 每页有几行
     * @return 根据当前页码返回的分页结果
     */
    public List<User> selectAllUser(int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<User> list=userMapper.selectAllUser();
        PageInfo<User> pageInfo = new PageInfo<>(list);
        return pageInfo.getList();
    }
    public HashMap<String,String> userLoginService(String userAccount, String userPassword){
        ArrayList<User> list=userMapper.selectUserByAccount(userAccount);
        HashMap<String,String> map=new HashMap<>(3);
        if(list.size() == 0){
            map.put("code","1");
            map.put("msg","Not Found User");
            return map;
        }
        userPassword = userPassword+list.get(0).getSalt();
        if(Md5Util.getMd5Str(userPassword).equals(list.get(0).getUserPassword())){
            String token = TokenUtil.signToken(userAccount,userPassword);
            String userName = list.get(0).getUserName();
            map.put("code","0");
            map.put("userName",userName);
            map.put("token",token);
            return map;
        }
        map.put("code","2");
        map.put("msg","Password Error");
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
