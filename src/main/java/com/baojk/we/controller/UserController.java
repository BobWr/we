package com.baojk.we.controller;

import com.baojk.we.base.ApiException;
import com.baojk.we.base.BaseController;
import com.baojk.we.base.BaseResponse;
import com.baojk.we.base.BaseResult;
import com.baojk.we.base.SuccessConstants;
import com.baojk.we.service.UserService;
import com.baojk.we.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author baojikui (bjklwr@outlook.com)
 * @date 2018/10/17
 */
@RestController
@RequestMapping("/api/v1/user")
@Api(description = "用户接口")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 用户登陆获取token
     *
     * @param username
     * @param password
     *
     * @return
     *
     * @throws Exception
     */
    @PostMapping(value = "")
    @ApiOperation(value = "登陆")
    public BaseResponse<String> login(@RequestParam @ApiParam(value = "用户名") String username,
                                      @RequestParam @ApiParam(value = "密码") String password) throws ApiException {
        BaseResponse<String> baseResponse = new BaseResponse<>(SuccessConstants.SUCCESS);

        BaseResult<String> baseResult = userService.login(username, password);
        if (baseResult.isSuccess()) {
            String token = baseResult.getData();
            if (token != null) {
                baseResponse.setData(token);
            }
            return baseResponse;
        }
        throw new ApiException(baseResult.getError());
    }

    /**
     * 用户登出
     *
     * @return
     *
     * @throws Exception
     */
    @DeleteMapping(value = "")
    @ApiOperation(value = "登出")
    public BaseResponse<Boolean> logout() throws ApiException {
        BaseResponse<Boolean> baseResponse = new BaseResponse<>(SuccessConstants.SUCCESS);

        BaseResult<Boolean> baseResult = userService.logout(token);
        if (baseResult.isSuccess()) {
            baseResponse.setData(baseResult.getData());
            return baseResponse;
        }
        throw new ApiException(baseResult.getError());
    }

    /**
     * 获取用户信息
     *
     * @return
     *
     * @throws Exception
     */
    @GetMapping(value = "")
    @ApiOperation(value = "获取用户信息")
    public BaseResponse<UserVO> getUser() throws ApiException {
        BaseResponse<UserVO> baseResponse = new BaseResponse<>(SuccessConstants.SUCCESS);

        BaseResult<UserVO> baseResult = userService.getUser(token);
        if (baseResult.isSuccess()) {
            baseResponse.setData(baseResult.getData());
            return baseResponse;
        }
        throw new ApiException(baseResult.getError());
    }

    /**
     * 用户注册
     *
     * @return
     *
     * @throws Exception
     */
    @PutMapping(value = "")
    @ApiOperation(value = "注册")
    public BaseResponse<Boolean> signup(@RequestParam @ApiParam(value = "用户名") String username,
                                        @RequestParam @ApiParam(value = "密码") String password) throws ApiException {
        BaseResponse<Boolean> baseResponse = new BaseResponse<>(SuccessConstants.SUCCESS);

        BaseResult<Boolean> baseResult = userService.signup(username, password);
        if (baseResult.isSuccess()) {
            baseResponse.setData(baseResult.getData());
            return baseResponse;
        }
        throw new ApiException(baseResult.getError());
    }
}
