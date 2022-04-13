package service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import mapper.SingerMapper;
import org.springframework.stereotype.Service;
import pojo.Singer;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author ZAY
 */
@Service
public class SingerService {
    @Resource
    private SingerMapper singerMapper;
    public List<Singer> selectAllSinger(int pageNum,int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Singer> list=singerMapper.selectAllSinger();
        PageInfo<Singer> pageInfo = new PageInfo<>(list);
        return pageInfo.getList();
    }
}
