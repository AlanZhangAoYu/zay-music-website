package controller;

import com.alibaba.fastjson.JSON;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pojo.MongoDbFile;
import pojo.Song;
import service.AlbumService;
import service.MongoDbFileService;
import service.SongService;
import util.Md5Util;
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
            String fileId= mongoDbFileService.uploadFile(file);
            songService.insertSong(songMap,songTypeId,fileId);
            return JSON.toJSONString(songMap);
        } catch (Exception e) {
            e.printStackTrace();
            return JSON.toJSONString(map);
        }
        /*try{

            MongoDbFile mongoDbFile=new MongoDbFile(file.getOriginalFilename(), file.getContentType(), file.getSize(),
                    new Binary(file.getBytes()));
            mongoDbFile.setMd5(Md5Util.getMd5InputStream(file.getInputStream()));
            songService.uploadSongFile(mongoDbFile);
            map.put("code","0");
            map.put("msg","上传成功!");
            return JSON.toJSONString(map);
        }catch (Exception e){
            e.printStackTrace();
            map.put("code","-1");
            map.put("msg","上传失败!");
            return JSON.toJSONString(map);
        }*/
    }
}
