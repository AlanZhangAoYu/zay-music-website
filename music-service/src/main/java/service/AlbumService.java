package service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import mapper.AlbumMapper;
import org.springframework.stereotype.Service;
import pojo.Album;
import util.GlobalConstant;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ZAY
 */
@Service
public class AlbumService {
    @Resource
    private AlbumMapper albumMapper;
    public List<Album> selectAllAlbum(int pageNum,int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Album> list=albumMapper.selectAllAlbum();
        PageInfo<Album> pageInfo = new PageInfo<>(list);
        return pageInfo.getList();
    }
    public int selectAlbumByName(String albumName){
        List<Album> list=albumMapper.selectAlbumByName(albumName);
        int albumId = 0;
        if(list.isEmpty()){
            return GlobalConstant.FAIL;
        }
        for (Album album : list) {
            albumId=album.getAlbumId();
        }
        return albumId;
    }
    public List<Album> selectAlbumByPara(Map<String,Object> map){
        return albumMapper.selectAlbumByPara(map);
    }
    public int selectAllAlbumCount(){
        return albumMapper.selectAllAlbumCount();
    }
    public int insertAlbum(String albumName,int singerId){
        Map<Object, Object> map=new HashMap<>(4);
        map.put("albumName",albumName);
        map.put("singerId",singerId);
        map.put("year",GlobalConstant.DEFAULT_ALBUM_YEAR);
        map.put("albumImgId", GlobalConstant.DEFAULT_IMAGE_ID);
        albumMapper.insertAlbum(map);
        List<Album> list = albumMapper.selectAlbumByName(albumName);
        int albumId=0;
        for (Album album : list) {
            albumId=album.getAlbumId();
        }
        return albumId;
    }
    public int updateAlbumInfo(HashMap<Object,Object> map){
        return albumMapper.updateAlbumInfo(map);
    }
}
