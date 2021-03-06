package com.gupaoedu.sso.controller;

import com.gupaoedu.common.constants.GpmallWebConstant;
import com.gupaoedu.sso.controller.support.ResponseData;
import com.gupaoedu.sso.controller.support.ResponseEnum;
import com.gupaoedu.user.IUserCoreService;
import com.gupaoedu.user.dto.UserLoginRequest;
import com.gupaoedu.user.dto.UserLoginResponse;
import com.gupaoedu.user.dto.UserRegisterRequest;
import com.gupaoedu.user.dto.UserRegisterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * 刘海飞

 */


@RestController
public class UserController extends BaseController{

    @Autowired
    IUserCoreService userCoreService;

    @PostMapping("/login")
    @Anoymous
    public ResponseData doLogin(String username, String password,
                                     HttpServletResponse response){
        ResponseData data=new ResponseData();
        UserLoginRequest request=new UserLoginRequest();
        request.setPassword(password);
        request.setUserName(username);
        UserLoginResponse userLoginResponse=userCoreService.login(request);
        response.addHeader("Set-Cookie",
                "access_token="+userLoginResponse.getToken()+";Path=/;HttpOnly");

        data.setMessage(userLoginResponse.getMsg());
        data.setCode(userLoginResponse.getCode());
        data.setData(GpmallWebConstant.GPMALL_ACTIVITY_ACCESS_URL);
        return data;
    }

    @GetMapping("/test")
    public String test(){
        return "success:"+getUid();
    }


    @PostMapping("/register")
    @Anoymous
    public @ResponseBody
    ResponseData register(String username, String password, String mobile){
        ResponseData data=new ResponseData();

        UserRegisterRequest request=new UserRegisterRequest();
        request.setMobile(mobile);
        request.setUsername(username);
        request.setPassword(password);
        try {
            UserRegisterResponse response = userCoreService.register(request);
            data.setMessage(response.getMsg());
            data.setCode(response.getCode());
        }catch(Exception e) {
            data.setMessage(ResponseEnum.FAILED.getMsg());
            data.setCode(ResponseEnum.FAILED.getCode());
        }
        return data;
    }

}
