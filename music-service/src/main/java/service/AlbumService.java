package service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import mapper.AlbumMapper;
import org.springframework.stereotype.Service;
import pojo.Album;
import javax.annotation.Resource;
import java.util.List;
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
}
