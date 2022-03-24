package mapper;

import org.apache.ibatis.annotations.Mapper;
import pojo.Admin;
import java.util.ArrayList;

/**
 * @author ZAY
 */
@Mapper
public interface AdminMapper {
    /**
     * 根据账号查询管理员信息
     * @param adminAccount 管理员账号
     * @return 管理员信息，若没查到，列表为空
     */
    ArrayList<Admin> selectAdminByAccount(String adminAccount);
}
