package com.gupaoedu.activity.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 刘海飞

 */


@RestController
public class IndexController extends BaseController{


    @PostMapping("/doDraw")
    public String doDraw(){
        return "success";
    }

}
