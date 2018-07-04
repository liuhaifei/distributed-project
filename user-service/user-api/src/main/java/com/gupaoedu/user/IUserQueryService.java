package com.gupaoedu.user;


import com.gupaoedu.user.dto.UserQueryRequest;
import com.gupaoedu.user.dto.UserQueryResponse;

/**
 * 刘海飞

 */
public interface IUserQueryService {


    /**
     * 根据用户id来查询用户信息
     * @param request
     * @return
     */
    UserQueryResponse getUserById(UserQueryRequest request);

    /**
     * 根据用户id来查询用户信息
     * @param request
     * @return
     */
    UserQueryResponse getUserByIdWithLimiter(UserQueryRequest request);
}
