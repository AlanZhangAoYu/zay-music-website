package mapper;

import org.apache.ibatis.annotations.Mapper;
import pojo.Song;
import java.util.List;
import java.util.Map;

/**
 * @author ZAY
 */
@Mapper
public interface SongMapper {
    /**
     * 查询所有歌曲
     * @return 返回查询到的所有歌曲
     */
    List<Song> selectAllSong();

    /**
     * 插入歌曲信息
     * @param map 要插入的歌曲信息
     * @return 数据库中受影响的行数
     */
    int insertSong(Map<Object,Object> map);
}
