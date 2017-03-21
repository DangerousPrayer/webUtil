/**
 * Created by liuruijie on 2016/9/28.
 * 前端控制
 */
    //状态码
web_status = {
    SUCCESS : "000",
    FAIL : "001",
    NO_LOGIN : "003",
    NO_PRIVILEGE : "004"
};

function simpleSuccess(result) {
    //如果成功，则读取后端返回的操作指令
    if (result.status == web_status.SUCCESS) {
        if(result['msg']){
            alert(result.msg);
        }
        //刷新
        if(result['refresh']){
            window.location.reload();
            return;
        }
        //返回
        if(result['back']){
            window.location.href = document.referrer;
        }
        //跳转
        if(result['redirectUrl']!=null){
            window.location.href = result.redirectUrl;
            return;
        }
        return result.data;
    }
    //未登录
    if (result.status == web_status.NO_LOGIN) {
        alert("您还未登陆！");
        window.location.href = "/bpm/login";
    }else{
        //其他错误情况，直接弹出提示框
        if(result.msg!=null){
            alert(result.msg);
        }
    }
    return null;
}

//对jquery的ajax方法再次封装
__ajax = function(url, data, success, type ,contentType){
    success = success||function(data){};
    data = data||{};
    var config = {
        url:url,
        type:type,
        dataType:"json",
        data:data,
        success:function(result){
            success(simpleSuccess(result));
        }
    };
    if(contentType){
        config.contentType = contentType;
    }
    $.ajax(config)
};

//再再次封装
AJAX = {
  GET:function(url, data, success){
      __ajax(url, data, success, "get");
    },
    POST_JSON: function(url, data, success){
        __ajax(url, data, success, "post", "application/json");
    },
    POST:function(url, data, success){
        __ajax(url, data, success, "post");
    },
    DELETE: function(url, data, success){
        __ajax(url, data, success, "delete");
    },
    PUT:function(url, data, success){
        __ajax(url, data, success, "put", "application/json");
    },
    PATCH: function (url, data, success) {
        __ajax(url, data, success, "patch", "application/json");
    },
    INCLUDE: function (url, id) {
        $.ajax({
            url:url,
            type:"get",
            dataType:"html",
            error: function (code) {
                $("#"+id).html("加载失败");
            },
            success: function (result) {
                $("#"+id).html(result);
            }
        })
    }
};
