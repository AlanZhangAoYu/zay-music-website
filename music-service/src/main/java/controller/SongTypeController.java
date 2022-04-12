package controller;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import service.SongTypeService;
import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author ZAY
 */
@RestController
public class SongTypeController {
    @Resource
    SongTypeService songTypeService;
    @GetMapping(value = "/selectAllSongType")
    public String selectAllSongTypeController(){
        return JSON.toJSONString(songTypeService.selectAllSongType());
    }
}
