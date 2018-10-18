package com.baojk.we.service.impl;

import com.baojk.we.base.BaseResult;
import com.baojk.we.base.ErrorEnum;
import com.baojk.we.dao.mapper.UserMapper;
import com.baojk.we.dao.model.User;
import com.baojk.we.dao.model.UserExample;
import com.baojk.we.redis.RedisManager;
import com.baojk.we.service.UserService;
import com.baojk.we.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.List;

/**
 * @author baojikui (bjklwr@outlook.com)
 * @date 2018/10/17
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisManager redisManager;

    @Override
    public BaseResult<String> login(String username, String password) {

        BaseResult<String> baseResult = new BaseResult<>();
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andNameEqualTo(username);
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() == 0) {
            baseResult.setError(ErrorEnum.NO_USER_ERROR);
            return baseResult;
        }
        User user = users.get(0);
//        if (!user.getPwd().equals(encryption(password))) {
        if (!user.getPwd().equals(password)) {
            baseResult.setError(ErrorEnum.INCORRECT_INPUT_ERROR);
            return baseResult;
        }
        String token = encryption(username + password);
        redisManager.addToken(token, user.getId());
        baseResult.setData(token);
        return baseResult;
    }

    @Override
    public BaseResult<Boolean> logout(String token) {

        BaseResult<Boolean> result = new BaseResult<>();
        result.setData(redisManager.delToken(token));
        return result;
    }

    private String encryption(String plainText) {
        String cipherText = null;

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            cipherText = new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cipherText;
    }

    @Override
    public BaseResult<UserVO> getUser(String token) {
        BaseResult<UserVO> baseResult = new BaseResult<>();
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andIdEqualTo(redisManager.getValue(token));
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() == 0) {
            baseResult.setError(ErrorEnum.NO_USER_ERROR);
            return baseResult;
        }
        User user = users.get(0);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        baseResult.setData(userVO);
        return baseResult;
    }
}
