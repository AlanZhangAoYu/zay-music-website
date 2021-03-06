package controller;

import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pojo.MongoDbFile;
import pojo.Song;
import service.MongoDbFileService;
import service.SongService;
import util.GlobalConstant;
import util.Mp3Util;
import util.MultipartFileToFileUtil;
import javax.annotation.Resource;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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
    @GetMapping(value = "/selectAllSongCount")
    public String selectAllSongCountController(){
        HashMap<String,Integer> map=new HashMap<>(1);
        int result = songService.selectAllSongCount();
        map.put("AllSongCount",result);
        return JSON.toJSONString(map);
    }
    @GetMapping(value = "/selectSongByPara")
    public String selectSongByPara(@RequestParam("songId")String songId,
                                   @RequestParam("songName")String songName,
                                   @RequestParam("singerId")String singerId,
                                   @RequestParam("albumId")String albumId,
                                   @RequestParam("songTypeId")String songTypeId){
        HashMap<String,Object> resultMap=new HashMap<>(1);
        HashMap<String,Object> map=new HashMap<>(5);
        if(!songId.isEmpty()){
            map.put("songId",Integer.valueOf(songId));
        }
        if(!songName.isEmpty()){
            map.put("songName",songName);
        }
        if(!singerId.isEmpty()){
            map.put("singerId",Integer.valueOf(singerId));
        }
        if(!albumId.isEmpty()){
            map.put("albumId",Integer.valueOf(albumId));
        }
        if(!songTypeId.isEmpty()){
            map.put("songTypeId",Integer.valueOf(songTypeId));
        }
        List<Song> list=songService.selectSongByPara(map);
        if(list.isEmpty()){
            resultMap.put("msg", GlobalConstant.NO_DATA);
            return JSON.toJSONString(resultMap);
        }
        return JSON.toJSONString(list);
    }
    @PostMapping("/uploadSongFile")
    @Transactional(rollbackFor = Exception.class)
    public String uploadSongFileController(@RequestParam("file") MultipartFile file,@RequestParam("songTypeId") int songTypeId){
        HashMap<String,String> map=new HashMap<>(2);
        try {
            File newFile = MultipartFileToFileUtil.multipartFileToFile(file);
            HashMap<String,String> songMap= Mp3Util.getMp3Info(newFile);
            //MongoDb???????????????????????????Id????????????????????????MySQL???
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
    @GetMapping(value = "/previewFile/{id}")
    public ResponseEntity<Object> previewFile(@PathVariable("id") String id){
        Optional<MongoDbFile> file=mongoDbFileService.getFileById(id);
        return file.<ResponseEntity<Object>>map(mongoDbFile -> ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "fileName=" + mongoDbFile.getName())
                .header(HttpHeaders.CONTENT_TYPE, mongoDbFile.getContentType())
                .header(HttpHeaders.CONTENT_LENGTH, mongoDbFile.getSize() + "")
                .header("Connection", "close")
                .header(HttpHeaders.CONTENT_LENGTH, mongoDbFile.getSize() + "")
                .body(mongoDbFile.getContent()))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("File was not found"));
    }
    @GetMapping(value = "/downloadFile/{id}")
    public ResponseEntity<Object> downloadFile(@PathVariable("id") String id) {
        Optional<MongoDbFile> file = mongoDbFileService.getFileById(id);
        return file.<ResponseEntity<Object>>map(mongoDbFile -> ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;fileName=" + new String(mongoDbFile.getName().getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1))
                .header(HttpHeaders.CONTENT_TYPE, "application/octet-stream")
                .header(HttpHeaders.CONTENT_LENGTH, mongoDbFile.getSize() + "")
                .header("Connection", "close")
                .body(mongoDbFile.getContent()))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("File was not fount"));
    }
}
