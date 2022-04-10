package controller;

import com.alibaba.fastjson.JSON;
import org.bson.types.Binary;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pojo.MongoDbFile;
import service.SongService;
import util.Md5Util;
import util.Mp3Util;
import util.MultipartFileToFileUtil;
import javax.annotation.Resource;
import java.io.File;
import java.util.HashMap;

/**
 * @author ZAY
 */
@RestController
public class SongController {
    @Resource
    private SongService songService;
    @PostMapping("/uploadSongFile")
    public String uploadSongFileController(@RequestParam("file") MultipartFile file){
        HashMap<String,String> map=new HashMap<>(2);
        System.out.println(file);
        return JSON.toJSONString(map);
        /*try{
            File newFile= MultipartFileToFileUtil.multipartFileToFile(file);
            HashMap<String,String> songMap= Mp3Util.getMp3Info(newFile);
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
