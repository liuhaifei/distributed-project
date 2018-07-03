package com.gupaoedu.activity.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 腾讯课堂搜索 咕泡学院
 * 加群获取视频：608583947
 * 风骚的Michael 老师
 */


@RestController
public class IndexController extends BaseController{


    @PostMapping("/doDraw")
    public String doDraw(){
        return "success";
    }

}
