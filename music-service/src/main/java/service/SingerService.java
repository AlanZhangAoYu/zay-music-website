package service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import mapper.SingerMapper;
import org.springframework.stereotype.Service;
import pojo.Singer;
import util.GlobalConstant;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public int selectAllSingerCount(){
        return singerMapper.selectAllSingerCount();
    }
    public int insertSinger(String singerName){
        Map<Object,Object> map = new HashMap<>(3);
        map.put("singerName",singerName);
        map.put("singerBirth",new Date(GlobalConstant.DEFAULT_DATE));
        map.put("singerImgId",GlobalConstant.DEFAULT_IMAGE_ID);
        singerMapper.insertSinger(map);
        List<Singer> list= singerMapper.selectSingerByName(singerName);
        int singerId=0;
        for (Singer singer : list) {
            singerId=singer.getSingerId();
        }
        return singerId;
    }
    public int selectSingerByName(String singerName){
        List<Singer> list=singerMapper.selectSingerByName(singerName);
        int singerId=0;
        if(list.isEmpty()){
            return GlobalConstant.FAIL;
        }
        for (Singer singer : list) {
            singerId=singer.getSingerId();
        }
        return singerId;
    }
    public int updateSingerInfo(HashMap<Object,Object> map){
        return singerMapper.updateSingerInfo(map);
    }
}
