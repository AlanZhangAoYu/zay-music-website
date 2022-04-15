package service;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Field;
import org.springframework.data.mongodb.core.query.Query;
import com.mongodb.client.gridfs.GridFSBucket;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pojo.MongoDbFile;
import util.GlobalConstant;
import util.Md5Util;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author ZAY
 */
@Service
public class MongoDbFileService {
    @Resource
    private MongoTemplate mongoTemplate;
    @Resource
    private GridFsTemplate gridFsTemplate;
    @Resource
    private GridFSBucket gridFsBucket;

    /**
     * 保存文件
     * @param file 传入的文件
     * @return 将刚才传入的文件保存后返回
     */
    public MongoDbFile saveFile(MongoDbFile file) {
        file = mongoTemplate.save(file);
        return file;
    }
    /**
     * 上传文件到Mongodb的GridFs中
     * @param in
     * @param contentType
     * @return
     */
    public String uploadFileToGridFs(InputStream in , String contentType){
        String gridfsId = IdUtil.simpleUUID();
        //将文件存储进GridFS中
        gridFsTemplate.store(in, gridfsId , contentType);
        return gridfsId;
    }
    /**
     * 删除文件
     * @param id
     */
    public void removeFile(String id) {
        //根据id查询文件
        MongoDbFile fileDocument = mongoTemplate.findById(id , MongoDbFile.class );
        if(fileDocument!=null){
            //根据文件ID删除fs.files和fs.chunks中的记录
            Query deleteFileQuery = new Query().addCriteria(Criteria.where("filename").is(fileDocument.getGridfsId()));
            gridFsTemplate.delete(deleteFileQuery);
            //删除集合中的数据
            Query deleteQuery=new Query(Criteria.where("id").is(id));
            mongoTemplate.remove(deleteQuery,MongoDbFile.class);
        }
    }
    /**
     * 根据id查看文件
     * @param id
     * @return
     */
    public Optional<MongoDbFile> getFileById(String id){
        MongoDbFile fileDocument = mongoTemplate.findById(id , MongoDbFile.class );
        if(fileDocument != null){
            Query gridQuery = new Query().addCriteria(Criteria.where("filename").is(fileDocument.getGridfsId()));
            try {
                //根据id查询文件
                GridFSFile fsFile = gridFsTemplate.findOne(gridQuery);
                //打开流下载对象
                GridFSDownloadStream in = gridFsBucket.openDownloadStream(fsFile.getObjectId());
                if(in.getGridFSFile().getLength() > 0){
                    //获取流对象
                    GridFsResource resource = new GridFsResource(fsFile, in);
                    //获取数据
                    fileDocument.setContent(IoUtil.readBytes(resource.getInputStream()));
                    return Optional.of(fileDocument);
                }else {
                    fileDocument = null;
                    return Optional.empty();
                }
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
        return Optional.empty();
    }
    /**
     * 分页列出文件
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public List<MongoDbFile> listFilesByPage(int pageIndex, int pageSize) {
        Query query = new Query().with(Sort.by(Sort.Direction.DESC, "uploadDate"));
        long skip = (long) (pageIndex - 1) * pageSize;
        query.skip(skip);
        query.limit(pageSize);
        Field field = query.fields();
        field.exclude("content");
        return mongoTemplate.find(query , MongoDbFile.class );

    }
    public String uploadFile(MultipartFile file){
        try{
            MongoDbFile mongoDbFile=new MongoDbFile();
            mongoDbFile.setName(file.getOriginalFilename());
            mongoDbFile.setSize(file.getSize());
            mongoDbFile.setContentType(file.getContentType());
            mongoDbFile.setUploadDate(new Date());
            String suffix= Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf("."));
            mongoDbFile.setSuffix(suffix);
            mongoDbFile.setMd5(Md5Util.getMd5Str(String.valueOf(file.getInputStream())));
            //将文件存入gridFs
            String gridfsId = uploadFileToGridFs(file.getInputStream() , file.getContentType());
            mongoDbFile.setGridfsId(gridfsId);
            mongoDbFile = saveFile(mongoDbFile);
            return mongoDbFile.getId();
        }catch (Exception e){
            e.printStackTrace();
            return String.valueOf(GlobalConstant.FAIL);
        }
    }
}
