package mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.mongodb.repository.MongoRepository;
import pojo.MongoDbFile;
/**
 * @author ZAY
 */
@Mapper
public interface MongoDbFileMapper extends MongoRepository<MongoDbFile,String> {
}
