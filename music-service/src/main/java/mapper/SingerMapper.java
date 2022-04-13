package mapper;

import org.apache.ibatis.annotations.Mapper;
import pojo.Singer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ZAY
 */
@Mapper
public interface SingerMapper {
    /**
     * 查询所有的歌手
     * @return 返回所有歌手
     */
    List<Singer> selectAllSinger();

    /**
     * 插入歌手
     * @param map 要插入的歌手信息
     * @return 返回数据库中受影响的行数
     */
    int insertSinger(Map<Object,Object> map);

    /**
     * 更新一个歌手的信息
     * @param singer 传入的更新信息map
     * @return 返回数据库中受影响的行数
     */
    int updateSingerInfo(Singer singer);
}
