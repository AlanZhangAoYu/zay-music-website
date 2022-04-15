package controller;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pojo.Song;
import service.MongoDbFileService;
import service.SongService;
import util.Mp3Util;
import util.MultipartFileToFileUtil;
import javax.annotation.Resource;
import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * @author ZAY
 */
@RestController
public class SongController {
    @Resource
    private SongService songService;
    @Resource
    private MongoDbFileService mongoDbFileService;
    @GetMapping(value = "/selectAllSong")
    public String selectAllSongController(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize){
        List<Song> list=songService.selectAllSong(pageNum,pageSize);
        return JSON.toJSONString(list);
    }
    @PostMapping("/uploadSongFile")
    public String uploadSongFileController(@RequestParam("file") MultipartFile file,@RequestParam("songTypeId") int songTypeId){
        HashMap<String,String> map=new HashMap<>(2);
        try {
            File newFile = MultipartFileToFileUtil.multipartFileToFile(file);
            HashMap<String,String> songMap= Mp3Util.getMp3Info(newFile);
            //MongoDb先存文件，返回文件Id值，再将信息存到MySQL中
            String fileId= mongoDbFileService.uploadFile(file);
            songService.insertSong(songMap,songTypeId,fileId);
            map.put("msg", "Upload Successfully");
            return JSON.toJSONString(map);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg","Upload Failed");
            return JSON.toJSONString(map);
        }
    }
}
