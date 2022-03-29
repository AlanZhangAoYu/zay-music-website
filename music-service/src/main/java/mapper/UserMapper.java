package mapper;

import org.apache.ibatis.annotations.Mapper;
import pojo.User;

import java.util.ArrayList;

/**
 * @author ZAY
 */
@Mapper
public interface UserMapper {
    /**
     * 返回所有注册用户数据(分页)
     * @return 所有注册用户数据
     */
    ArrayList<User> selectAllUser();

    /**
     * 根据用户账号查询用户信息(用于用户登录)
     * @param userAccount 输入的用户账号
     * @return 返回用户信息
     */
    ArrayList<User> selectUserByAccount(String userAccount);

    /**
     * 查询注册用户总数
     * @return 返回注册用户总数
     */
    int selectAllUserCount();

    /**
     * 注册用户，向数据库添加数据
     * @param user 输入的用户数据
     * @return 返回作用的表行数(一般为 1)
     */
    int insertUser(User user);
}
