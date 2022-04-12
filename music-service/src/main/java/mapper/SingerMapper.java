package mapper;

import org.apache.ibatis.annotations.Mapper;
import pojo.Singer;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author ZAY
 */
@Mapper
public interface SingerMapper {
    /**
     * 查询所有的歌手
     * @return 返回所有歌手
     */
    ArrayList<Singer> selectAllSinger();

    /**
     * 插入歌手
     * @param singer 要插入的歌手
     * @return 返回数据库中受影响的行数
     */
    int insertSinger(Singer singer);

    /**
     * 更新一个歌手的信息
     * @param map 传入的更新信息map
     * @return 返回数据库中受影响的行数
     */
    int updateSingerInfo(HashMap map);
}
