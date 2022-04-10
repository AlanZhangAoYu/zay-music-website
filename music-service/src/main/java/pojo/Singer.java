package pojo;

import java.util.Date;

/**
 * @author ZAY
 */
public class Singer {
    private int singerId;
    private String singerName;
    private Date singerBirth;
    private String singerIntroduction;
    private String singerLocation;
    private String singerImgId;
    public int getSingerId() {
        return singerId;
    }
    public void setSingerId(int singerId) {
        this.singerId = singerId;
    }
    public String getSingerName() {
        return singerName;
    }
    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }
    public Date getSingerBirth() {
        return singerBirth;
    }
    public void setSingerBirth(Date singerBirth) {
        this.singerBirth = singerBirth;
    }
    public String getSingerIntroduction() {
        return singerIntroduction;
    }
    public void setSingerIntroduction(String singerIntroduction) {
        this.singerIntroduction = singerIntroduction;
    }
    public String getSingerLocation() {
        return singerLocation;
    }
    public void setSingerLocation(String singerLocation) {
        this.singerLocation = singerLocation;
    }
    public String getSingerImgId() {
        return singerImgId;
    }
    public void setSingerImgId(String singerImgId) {
        this.singerImgId = singerImgId;
    }
}
