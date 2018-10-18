package com.baojk.we.controller;

import com.baojk.we.base.ApiException;
import com.baojk.we.base.BaseController;
import com.baojk.we.base.BaseResponse;
import com.baojk.we.base.BaseResult;
import com.baojk.we.base.SuccessConstants;
import com.baojk.we.service.ArticleService;
import com.baojk.we.vo.ArticleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author baojikui (bjklwr@outlook.com)
 * @date 2018/10/12
 */
@RestController
@RequestMapping("/api/v1/article")
@Api(description = "文章接口")
public class ArticleController extends BaseController {

    @Autowired
    private ArticleService articleService;

    //    @PostMapping(value = "")
    //    public BaseResponse<Integer> addArticle(@RequestParam ArticleVO articleVO) {
    //
    //        BaseResponse<Integer> response = new BaseResponse<>(SuccessConstants.SUCCESS);
    //        response.setData(articleService.addArticle(articleVO));
    //        return response;
    //    }

    @GetMapping(value = "")
    public BaseResponse<ArticleVO> getArticle(@RequestParam @ApiParam(value = "id") Integer id) throws ApiException {

        BaseResponse<ArticleVO> response = new BaseResponse<>(SuccessConstants.SUCCESS);
        BaseResult<ArticleVO> result = articleService.getArticleById(id);
        if (result.isSuccess()) {
            response.setData(result.getData());
            return response;
        }
        throw new ApiException(result.getError());
    }

    @PostMapping(value = "")
    public BaseResponse<Integer> testAdd(@RequestParam String name, @RequestParam Integer classification,
                                         @RequestParam Integer authorId, @RequestParam String articleContent,
                                         @RequestParam Integer status, @RequestParam Integer isUp) throws ApiException {

        BaseResponse<Integer> response = new BaseResponse<>(SuccessConstants.SUCCESS);
        BaseResult<Integer> result = articleService
                        .addArticle(name, classification, authorId, articleContent, status, isUp);
        if (result.isSuccess()) {
            response.setData(result.getData());
            return response;
        }
        throw new ApiException(result.getError());
    }
}
