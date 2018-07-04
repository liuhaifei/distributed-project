package com.gupaoedu.user;

import com.gupaoedu.user.dto.*;

/**
 * 刘海飞

 */
public interface IUserCoreService {

    /**
     * 用户登录
     * @param request
     * @return
     */
    UserLoginResponse login(UserLoginRequest request);


    /**
     * 校验过程
     * @param request
     * @return
     */
    CheckAuthResponse validToken(CheckAuthRequest request);


    /*
     * 注册
     */
    UserRegisterResponse register(UserRegisterRequest userRegisterRequest);
}
