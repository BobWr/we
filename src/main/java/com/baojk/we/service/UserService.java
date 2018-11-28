package com.baojk.we.service;

import com.baojk.we.base.BaseResult;
import com.baojk.we.vo.UserVO;

import java.util.List;
import java.util.Map;

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
     * 根据token获取用户信息
     *
     * @param token
     *
     * @return
     */
    BaseResult<UserVO> getUser(String token);

    /**
     * 根据id获取用户信息
     *
     * @param id
     *
     * @return
     */
    BaseResult<UserVO> getUser(Integer id);

    /**
     * 根据id获取用户名  (后端用)
     *
     * @param ids
     *
     * @return
     */
    Map<Integer, String> getUserNames(List<Integer> ids);

    /**
     * 用户注册
     *
     * @param username
     * @param password
     *
     * @return
     */
    BaseResult<Boolean> signup(String username, String password);
}
