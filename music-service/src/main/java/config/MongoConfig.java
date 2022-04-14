package config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ZAY
 * MongoDB的配置类
 */
@Configuration
public class MongoConfig {
    /**
     * 获取配置文件中数据库信息
     */
    @Value("${spring.data.mongodb.database}")
    private String db;
    /**
     * GridFSBucket用于打开下载流
     */
    @Bean
    public GridFSBucket getGridFSBucket(MongoClient mongoClient){
        MongoDatabase mongoDatabase = mongoClient.getDatabase(db);
        return GridFSBuckets.create(mongoDatabase);
    }
}
