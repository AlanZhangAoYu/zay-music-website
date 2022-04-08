package service;

import mapper.SongMapper;
import org.springframework.stereotype.Service;
import pojo.MongoDbFile;
import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author ZAY
 */
@Service
public class SongService {
    @Resource
    private SongMapper songMapper;
    public MongoDbFile uploadSongFile(MongoDbFile file){
        return songMapper.save(file);
    }
    public void removeSongFile(String id){
        songMapper.deleteById(id);
    }
    public Optional<MongoDbFile> getSongFileById(String id){
        return songMapper.findById(id);
    }
}
