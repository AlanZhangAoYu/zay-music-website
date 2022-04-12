package service;

import mapper.SongTypeMapper;
import org.springframework.stereotype.Service;
import pojo.SongType;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author ZAY
 */
@Service
public class SongTypeService {
    @Resource
    SongTypeMapper songTypeMapper;
    public ArrayList<SongType> selectAllSongType(){
        return songTypeMapper.selectAllSongType();
    }
}
