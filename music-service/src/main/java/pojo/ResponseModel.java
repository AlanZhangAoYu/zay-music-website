package pojo;

import util.GlobalConstant;

/**
 * @author ZAY
 * 公用数据返回模型
 */
public class ResponseModel {
    private String code = "fail";
    private String message = "";
    private String data;
    /**
     * 私有构造函数，此类不允许手动实例化，需要调用getInstance()获取实例
     */
    private ResponseModel() {
    }
    /**
     * 返回默认的实例
     * @return 返回ResponseModel实例
     */
    public static ResponseModel getInstance() {
        ResponseModel model = new ResponseModel();
        model.setCode(GlobalConstant.FAIL);
        return model;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
}
