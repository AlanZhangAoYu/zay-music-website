package pojo;

/**
 * @author ZAY
 */
public class Album {
    private int albumId;
    private String albumName;
    private Singer singer;
    private int year;
    private String albumImgId;
    public int getAlbumId() {
        return albumId;
    }
    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }
    public String getAlbumName() {
        return albumName;
    }
    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }
    public Singer getSinger() {
        return singer;
    }
    public void setSinger(Singer singer) {
        this.singer = singer;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public String getAlbumImgId() {
        return albumImgId;
    }
    public void setAlbumImgId(String albumImgId) {
        this.albumImgId = albumImgId;
    }
}
