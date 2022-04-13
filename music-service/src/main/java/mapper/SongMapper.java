package mapper;

import org.apache.ibatis.annotations.Mapper;
import pojo.Song;
import java.util.List;

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
}
