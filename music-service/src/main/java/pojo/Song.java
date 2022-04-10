package pojo;

import java.util.Date;

/**
 * @author ZAY
 */
public class Song {
    private int songId;
    private String songName;
    private Singer singer;
    private Album album;
    private String songLength;
    private SongType songType;
    private Date createTime;
    private String fileId;
    public int getSongId() {
        return songId;
    }
    public void setSongId(int songId) {
        this.songId = songId;
    }
    public String getSongName() {
        return songName;
    }
    public void setSongName(String songName) {
        this.songName = songName;
    }
    public Singer getSinger() {
        return singer;
    }
    public void setSinger(Singer singer) {
        this.singer = singer;
    }
    public Album getAlbum() {
        return album;
    }
    public void setAlbum(Album album) {
        this.album = album;
    }
    public String getSongLength() {
        return songLength;
    }
    public void setSongLength(String songLength) {
        this.songLength = songLength;
    }
    public SongType getSongType() {
        return songType;
    }
    public void setSongType(SongType songType) {
        this.songType = songType;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public String getFileId() {
        return fileId;
    }
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
}
