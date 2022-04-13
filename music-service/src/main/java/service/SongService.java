package service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import mapper.SongMapper;
import org.springframework.stereotype.Service;
import pojo.Song;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author ZAY
 */
@Service
public class SongService {
    @Resource
    private SongMapper songMapper;
    public List<Song> selectAllSong(int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Song> list=songMapper.selectAllSong();
        PageInfo<Song> pageInfo=new PageInfo<>(list);
        return pageInfo.getList();
    }
}
