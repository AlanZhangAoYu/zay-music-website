package mapper;

import org.apache.ibatis.annotations.Mapper;
import pojo.Singer;
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

    /**
     * 根据歌手名查询歌手信息
     * @param singerName 传入的歌手名
     * @return 返回的歌手信息
     */
    List<Singer> selectSingerByName(String singerName);
}
