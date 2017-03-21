package com.jerryl.controller;

import com.jerryl.Config;
import com.jerryl.common.ToWeb;
import com.jerryl.common.exception.BaseException;
import org.springframework.web.bind.annotation.*;

/**
 * Created by liuruijie on 2017/3/21.
 */
@RestController
@RequestMapping("test")
public class TestController {

    //get data
    @GetMapping("data")
    public Object getData(){
        return ToWeb.buildResult().putData("userName", "lrj");
    }

    //occur exception
    @PostMapping("exception/unknownException")
    public Object occurUnknownException() throws Exception {
        throw new Exception();
    }

    //occur exception explicitly
    @PostMapping("exception/businessException")
    public Object occurBusinessException(){
        throw new BaseException("some error has occurred!", Config.FAIL);
    }

    //test the actions
    @PostMapping("functions/{action}")
    public Object function(@PathVariable("action") String action
            , @RequestParam(value = "url", required = false) String url){
        if("refresh".equals(action)){
            //tell it to refresh the page
            return ToWeb.buildResult().refresh();
        }
        else if("back".equals(action)){
            //tell it to back to history
            return ToWeb.buildResult().back();
        }
        else if("msg".equals(action)){
            //tell it to show a message
            return ToWeb.buildResult().msg("this is a message");
        }
        else if("redirect".equals(action)){
            //tell it to redirect to another url
            if (url!=null&&!url.trim().equals("")){
                return ToWeb.buildResult().redirectUrl(url);
            }
            else{
                return ToWeb.buildResult().msg("url is not allowed null value");
            }
        }
        else {
            //do nothing but return a default response
            return ToWeb.buildResult();
        }
    }
}
