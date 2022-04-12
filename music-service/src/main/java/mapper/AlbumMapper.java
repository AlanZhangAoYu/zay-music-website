package mapper;

import org.apache.ibatis.annotations.Mapper;
import pojo.Album;
import java.util.ArrayList;

/**
 * @author ZAY
 */
@Mapper
public interface AlbumMapper {
    /**
     * 查询所有的专辑信息
     * @return 返回所有的专辑信息
     */
    ArrayList<Album> selectAllAlbum();

    /**
     * 插入一个专辑信息
     * @param album 要插入的专辑信息
     * @return 返回数据库受影响的行数
     */
    int insertAlbum(Album album);

}
