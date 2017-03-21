package com.jerryl.common;

import com.jerryl.Config;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuruijie on 2016/9/28.
 * 前端页面控制
 */
public class ToWeb {
    private String status; //状态码
    private String msg; //提示信息
    private String redirectUrl; //重定向的url
    private boolean back;
    private boolean refresh; //刷新页面
    private Map<String, Object> data; //数据

    public ToWeb(){
        data = new HashMap<>();
        refresh = false;
        back = false;
        status = Config.SUCCESS;
    }

    public static ToWeb buildResult(){
        return new ToWeb();
    }

    public ToWeb status(String status){
        setStatus(status);
        return this;
    }

    public ToWeb msg(String msg){
        setMsg(msg);
        return this;
    }

    public ToWeb redirectUrl(String redirectUrl){
        setRedirectUrl(redirectUrl);
        return this;
    }

    public ToWeb back(){
        setBack(true);
        return this;
    }

    public ToWeb refresh(){
        setRefresh(true);
        return this;
    }

    public ToWeb putData(String name, Object val){
        data.put(name, val);
        return this;
    }



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public boolean isRefresh() {
        return refresh;
    }

    public void setRefresh(boolean refresh) {
        this.refresh = refresh;
    }

    public boolean isBack() {
        return back;
    }

    public void setBack(boolean back) {
        this.back = back;
    }
}
