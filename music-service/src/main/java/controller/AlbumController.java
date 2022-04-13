package controller;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pojo.Album;
import service.AlbumService;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author ZAY
 */
@RestController
public class AlbumController {
    @Resource
    private AlbumService albumService;
    @GetMapping(value = "/selectAllAlbum")
    public String selectAllAlbumController(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize){
        List<Album> list= albumService.selectAllAlbum(pageNum,pageSize);
        return JSON.toJSONString(list);
    }
}
