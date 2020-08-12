package com.aisino.entity.system;

/**
 * JSON模型
 * <p>
 * 用户后台向前台返回的JSON对象
 *
 * @author
 */
public class Json implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private boolean success = false;

    // 0表示成功，1表示失败
    private int status = 0;

    private String msg = "";


    private Object obj = null;


    public Json() {
        super();
    }

    // 这种情况表示成功以及相应状态
    public Json(boolean success, int status, String msg) {
        this.success = success;
        this.status = status;
        this.msg = msg;
    }

    public Json(boolean success, String msg, Object obj) {
        super();
        this.success = success;
        this.msg = msg;
        this.obj = obj;
    }

    public Json(boolean success, int status, String msg, Object obj) {
        this.success = success;
        this.status = status;
        this.msg = msg;
        this.obj = obj;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    @Override
    public String toString() {
        return "Json [success=" + success + ", msg=" + msg + ", obj=" + obj
                + "]";
    }


}
