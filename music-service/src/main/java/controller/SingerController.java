package controller;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pojo.Singer;
import service.SingerService;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author ZAY
 */
@RestController
public class SingerController {
    @Resource
    private SingerService singerService;
    @GetMapping(value = "/selectAllSinger")
    public String selectAllSingerController(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize){
        List<Singer> list= singerService.selectAllSinger(pageNum,pageSize);
        return JSON.toJSONString(list);
    }
}
