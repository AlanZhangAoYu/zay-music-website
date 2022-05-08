package util;

/**
 * @author ZAY
 * 此类为全局常量的存放和统一管理处
 * 一切都是为了解耦啊
 */
public class GlobalConstant {
    /**
     * 默认图片的文件ID
     */
    public static final String DEFAULT_IMAGE_ID = "6256ba92fc38b4c4829920ed";
    /**
     * token过期时间2小时
     */
    public static final long TOKEN_EXPIRE_TIME = 2*60*60*1000;
    /**
     * token中的盐加密字符串
     */
    public static final String TOKEN_SECRET = "ZAY_IS_HANDSOME";
    /**
     * 专辑默认发行年份
     */
    public static final int DEFAULT_ALBUM_YEAR = 0;
    /**
     * 默认日期和时间: 1970-01-01 08:00:00
     */
    public static final long DEFAULT_DATE = 0L;
    public static final String SUCCESS = "success";
    public static final int FAIL = -0xffff;
    public static final String FAIL_STR = "failed";
    public static final String NO_DATA = "No data found";
}
