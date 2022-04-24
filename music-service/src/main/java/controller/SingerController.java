package controller;

import com.alibaba.fastjson.JSON;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pojo.Singer;
import service.MongoDbFileService;
import service.SingerService;
import util.GlobalConstant;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

/**
 * @author ZAY
 */
@RestController
public class SingerController {
    @Resource
    private SingerService singerService;
    @Resource
    private MongoDbFileService mongoDbFileService;
    @GetMapping(value = "/selectAllSinger")
    public String selectAllSingerController(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize){
        List<Singer> list= singerService.selectAllSinger(pageNum,pageSize);
        return JSON.toJSONString(list);
    }
    @GetMapping(value = "/selectAllSingerCount")
    public String selectAllSingerCountController(){
        HashMap<String,Integer> map = new HashMap<>(1);
        map.put("AllSingerCount", singerService.selectAllSingerCount());
        return JSON.toJSONString(map);
    }
    @PostMapping(value = "/updateSingerInfo")
    @Transactional(rollbackFor = Exception.class)
    public String updateSingerInfoController(@RequestParam("file") MultipartFile file,
                                             @RequestParam("singerId") int singerId,
                                             @RequestParam("singerName") String singerName,
                                             @RequestParam("singerBirth") Timestamp singerBirth,
                                             @RequestParam("singerIntroduction") String singerIntroduction,
                                             @RequestParam("singerLocation") String singerLocation){
        HashMap<Object,Object> map=new HashMap<>(6);
        HashMap<String,String> resultMap=new HashMap<>(1);
        map.put("singerId",singerId);
        map.put("singerName",singerName);
        map.put("singerBirth",singerBirth);
        map.put("singerIntroduction",singerIntroduction);
        map.put("singerLocation",singerLocation);
        if(file != null){
            String fileId= mongoDbFileService.uploadFile(file);
            map.put("singerImgId",fileId);
        }
        int result = singerService.updateSingerInfo(map);
        if(result == 1){
            resultMap.put("msg", GlobalConstant.SUCCESS);
        }else {
            resultMap.put("msg",GlobalConstant.FAIL_STR);
        }
        return JSON.toJSONString(resultMap);
    }
}
