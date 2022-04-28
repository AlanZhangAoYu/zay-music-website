package mapper;

import org.apache.ibatis.annotations.Mapper;
import pojo.Album;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ZAY
 */
@Mapper
public interface AlbumMapper {
    /**
     * 查询所有的专辑信息
     * @return 返回所有的专辑信息
     */
    List<Album> selectAllAlbum();

    /**
     * 插入一个专辑信息
     * @param map 要插入的专辑信息
     * @return 返回数据库受影响的行数
     */
    int insertAlbum(Map<Object,Object> map);

    /**
     * 根据名称查询数据库中是否有该专辑
     * @param albumName 传入的待查询的专辑名
     * @return 返回数据库中是否有该专辑
     */
    List<Album> selectAlbumByName(String albumName);

    /**
     * 查询所有的专辑数量
     * @return 返回所有的专辑数量
     */
    int selectAllAlbumCount();

    /**
     * 修改专辑信息
     * @param map 要修改的信息集合
     * @return 返回数据库中受影响的行数
     */
    int updateAlbumInfo(HashMap<Object,Object> map);
}
