package controller;

import com.alibaba.fastjson.JSON;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pojo.Singer;
import service.MongoDbFileService;
import service.SingerService;
import util.GlobalConstant;

import javax.annotation.Resource;
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
    @GetMapping(value = "/selectSingerByPara")
    public String selectSingerByParaController(@RequestParam("singerId")int singerId,
                                               @RequestParam("singerName")String singerName,
                                               @RequestParam("singerLocation")String singerLocation){
        HashMap<String,Object> resultMap = new HashMap<>(1);
        HashMap<String,Object> map=new HashMap<>(3);
        map.put("singerId",singerId);
        map.put("singerName",singerName);
        map.put("singerLocation",singerLocation);
        List<Singer> list = singerService.selectSingerByPara(map);
        if(list.isEmpty()){
            resultMap.put("msg",GlobalConstant.NO_DATA);
            return JSON.toJSONString(resultMap);
        }
        return JSON.toJSONString(list);
    }
    @PostMapping(value = "/updateSingerImg")
    @Transactional(rollbackFor = Exception.class)
    public String updateSingerImgController(@RequestParam("file") MultipartFile file,@RequestParam("singerId") int singerId){
        HashMap<Object,Object> map=new HashMap<>(1);
        map.put("singerId",singerId);
        HashMap<String,String> resultMap=new HashMap<>(1);
        if(!file.isEmpty()){
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
    @PostMapping(value = "/updateSingerInfo")
    @Transactional(rollbackFor = Exception.class)
    public String updateSingerInfoController(@RequestBody HashMap<Object,Object> map){
        HashMap<String,String> resultMap=new HashMap<>(1);
        int result = singerService.updateSingerInfo(map);
        if(result == 1){
            resultMap.put("msg", GlobalConstant.SUCCESS);
        }else {
            resultMap.put("msg",GlobalConstant.FAIL_STR);
        }
        return JSON.toJSONString(resultMap);
    }
}
