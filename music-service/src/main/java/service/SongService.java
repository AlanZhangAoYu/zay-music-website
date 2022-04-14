package service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import mapper.SongMapper;
import org.springframework.stereotype.Service;
import pojo.Song;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ZAY
 */
@Service
public class SongService {
    @Resource
    private SongMapper songMapper;
    @Resource
    private SingerService singerService;
    @Resource
    private AlbumService albumService;
    public List<Song> selectAllSong(int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Song> list=songMapper.selectAllSong();
        PageInfo<Song> pageInfo=new PageInfo<>(list);
        return pageInfo.getList();
    }
    public int insertSong(HashMap<String,String> songMap,int songTypeId,String fileId){
        String singerName= songMap.get("artist");
        String albumName= songMap.get("album");
        int singerId=0;
        int albumId=0;

        Map<Object,Object> map=new HashMap<>(7);
        map.put("songName",songMap.get("songName"));
        map.put("songLength",songMap.get("length"));
        map.put("songTypeId",songTypeId);
        map.put("fileId",fileId);
        if(!singerService.selectSingerByName(singerName)){
            singerId = singerService.insertSinger(singerName);
        }else {

        }
        if(!albumService.selectAlbumByName(albumName)){
            albumId = albumService.insertAlbum(albumName,singerId);
        }else {

        }
        map.put("singerId",singerId);
        map.put("albumId",albumId);
        map.put("createTime",System.currentTimeMillis());
        return songMapper.insertSong(map);
    }
}
