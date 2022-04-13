package service;

import mapper.MongoDbFileMapper;
import pojo.MongoDbFile;
import java.util.Optional;

/**
 * @author ZAY
 */
public class MongoDbFileService {
    private MongoDbFileMapper mongoDbFileMapper;
    /**
     * 上传音乐 mp3文件到 mongoDB
     * @param file 传入的 mp3文件
     * @return 返回信息
     */
    MongoDbFile saveFile(MongoDbFile file){
        return mongoDbFileMapper.save(file);
    }
    /**
     * 根据文件id删除 mp3文件
     * @param id 文件唯一的id
     */
    void removeFile(String id){
        mongoDbFileMapper.deleteById(id);
    }
    /**
     * 根据id获取文件
     * @param id 文件唯一的id
     * @return 返回的文件对象
     */
    Optional<MongoDbFile> getFileById(String id){
        return mongoDbFileMapper.findById(id);
    }
}
