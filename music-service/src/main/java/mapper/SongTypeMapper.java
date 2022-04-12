package mapper;

import org.apache.ibatis.annotations.Mapper;
import pojo.SongType;
import java.util.ArrayList;

/**
 * @author ZAY
 */
@Mapper
public interface SongTypeMapper {
    /**
     * 查询所有的歌曲类别信息
     * @return 返回所有的歌曲类别信息
     */
    ArrayList<SongType> selectAllSongType();
}
