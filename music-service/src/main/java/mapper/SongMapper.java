package mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.mongodb.repository.MongoRepository;
import pojo.MongoDbFile;
import pojo.Song;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author ZAY
 */
@Mapper
public interface SongMapper extends MongoRepository<MongoDbFile,String> {
    /**
     * 上传音乐 mp3文件到 mongoDB
     * @param file 传入的 mp3文件
     * @return 返回信息
     */
    MongoDbFile uploadSongFile(MongoDbFile file);
    /**
     * 根据文件id删除 mp3文件
     * @param id 文件唯一的id
     */
    void removeSongFile(String id);
    /**
     * 根据id获取文件
     * @param id 文件唯一的id
     * @return 返回的文件对象
     */
    Optional<MongoDbFile> getSongFileById(String id);
    /**
     * 分页查询，按上传时间降序
     * @param pageIndex 页号
     * @param pageSize 每夜大小
     * @return 文件对象结果列表
     */
    List<MongoDbFile> listFilesByPage(int pageIndex, int pageSize);
    /**
     * 查询所有歌曲
     * @return 返回查询到的所有歌曲
     */
    ArrayList<Song> selectAllSong();
}
