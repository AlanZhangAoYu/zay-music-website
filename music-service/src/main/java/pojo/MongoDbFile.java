package pojo;

import org.bson.types.Binary;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

/**
 * @author ZAY
 */
public class MongoDbFile{
    /**
     * id 主键
     * name 文件名称
     * size 文件大小
     * uploadDate 上传时间
     * md5 文件md5值
     * content 文件内容
     * contentType 文件类型
     * suffix 文件后缀名
     * description 文件描述
     * gridfsId 大文件管理GridFS的id
     */
    private String id;
    private String name;
    private String contentType;
    private long size;
    private Date uploadDate;
    private String md5;
    private byte[] content;
    private String suffix;
    private String description;
    private String gridfsId;
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
    public byte[] getContent() {
        return content;
    }
    public void setContent(byte[] content) {
        this.content = content;
    }
    public String getSuffix() {
        return suffix;
    }
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getGridfsId() {
        return gridfsId;
    }
    public void setGridfsId(String gridfsId) {
        this.gridfsId = gridfsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MongoDbFile that = (MongoDbFile) o;
        return size == that.size && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(contentType, that.contentType) && Objects.equals(uploadDate, that.uploadDate) && Objects.equals(md5, that.md5) && Arrays.equals(content, that.content) && Objects.equals(suffix, that.suffix) && Objects.equals(description, that.description) && Objects.equals(gridfsId, that.gridfsId);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, contentType, size, uploadDate, md5, suffix, description, gridfsId);
        result = 31 * result + Arrays.hashCode(content);
        return result;
    }
}
