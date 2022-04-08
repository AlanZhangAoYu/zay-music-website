package pojo;

import org.bson.types.Binary;
import java.util.Date;
import java.util.Objects;

/**
 * @author ZAY
 */
public class MongoDbFile{
    private String id;
    private String name;
    private String contentType;
    private long size;
    private Date uploadDate;
    private String md5;
    private Binary content;
    private String path;
    protected MongoDbFile() {
    }
    public MongoDbFile(String name, String contentType, long size,  Binary content) {
        this.name = name;
        this.contentType = contentType;
        this.size = size;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public Binary getContent() {
        return content;
    }

    public void setContent(Binary content) {
        this.content = content;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (!(o instanceof MongoDbFile)){
            return false;
        }
        MongoDbFile mongoDbFile = (MongoDbFile) o;
        return size == mongoDbFile.size &&
                Objects.equals(id, mongoDbFile.id) &&
                Objects.equals(name, mongoDbFile.name) &&
                Objects.equals(contentType, mongoDbFile.contentType) &&
                Objects.equals(uploadDate, mongoDbFile.uploadDate) &&
                Objects.equals(md5, mongoDbFile.md5) &&
                Objects.equals(content, mongoDbFile.content) &&
                Objects.equals(path, mongoDbFile.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, contentType, size, uploadDate, md5, content, path);
    }
}
