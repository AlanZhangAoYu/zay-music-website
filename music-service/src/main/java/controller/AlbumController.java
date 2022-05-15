package controller;

import com.alibaba.fastjson.JSON;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pojo.Album;
import service.AlbumService;
import service.MongoDbFileService;
import util.GlobalConstant;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author ZAY
 */
@RestController
public class AlbumController {
    @Resource
    private AlbumService albumService;
    @Resource
    private MongoDbFileService mongoDbFileService;
    @GetMapping(value = "/selectAllAlbum")
    public String selectAllAlbumController(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize){
        List<Album> list= albumService.selectAllAlbum(pageNum,pageSize);
        return JSON.toJSONString(list);
    }
    @GetMapping(value = "/selectAllAlbumCount")
    public String selectAllAlbumCountController(){
        HashMap<String,Integer> map=new HashMap<>(1);
        map.put("AllAlbumCount",albumService.selectAllAlbumCount());
        return JSON.toJSONString(map);
    }
    @PostMapping(value = "/updateAlbumImg")
    @Transactional(rollbackFor = Exception.class)
    public String updateAlbumImgController(@RequestParam("file") MultipartFile file,
                                           @RequestParam("albumId") String albumId){
        HashMap<Object,Object> map=new HashMap<>(1);
        map.put("albumId",Integer.valueOf(albumId));
        HashMap<String,String> resultMap=new HashMap<>(1);
        if(!file.isEmpty()){
            String fileId= mongoDbFileService.uploadFile(file);
            map.put("albumImgId",fileId);
        }
        int result = albumService.updateAlbumInfo(map);
        if(result == 1){
            resultMap.put("msg", GlobalConstant.SUCCESS);
        }else {
            resultMap.put("msg",GlobalConstant.FAIL_STR);
        }
        return JSON.toJSONString(resultMap);
    }
    @PostMapping(value = "/updateAlbumInfo")
    @Transactional(rollbackFor = Exception.class)
    public String updateAlbumInfoController(@RequestBody HashMap<Object,Object> map){
        HashMap<String,String> resultMap=new HashMap<>(1);
        int result = albumService.updateAlbumInfo(map);
        if(result == 1){
            resultMap.put("msg", GlobalConstant.SUCCESS);
        }else {
            resultMap.put("msg",GlobalConstant.FAIL_STR);
        }
        return JSON.toJSONString(resultMap);
    }
}
