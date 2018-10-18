package com.baojk.we.service;

import com.baojk.we.base.BaseResult;
import com.baojk.we.vo.UserVO;

/**
 * @author baojikui (bjklwr@outlook.com)
 * @date 2018/10/18
 */
public interface UserService {

    /**
     * 用户登录
     *
     * @param username
     * @param password
     *
     * @return
     */
    BaseResult<String> login(String username, String password);

    /**
     * 用户登出
     *
     * @param token
     *
     * @return
     */
    BaseResult<Boolean> logout(String token);

    /**
     * 用户登出
     *
     * @param token
     *
     * @return
     */
    BaseResult<UserVO> getUser(String token);
}
