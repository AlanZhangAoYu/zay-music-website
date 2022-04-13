package service;

import mapper.SongMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * @author ZAY
 */
@Service
public class SongService {
    @Resource
    private SongMapper songMapper;

}
