package com.baojk.we.controller;

import com.baojk.we.base.ApiException;
import com.baojk.we.base.BaseController;
import com.baojk.we.base.BaseResponse;
import com.baojk.we.base.BaseResult;
import com.baojk.we.base.SuccessConstants;
import com.baojk.we.enums.ArticleClassificationEnum;
import com.baojk.we.service.ArticleService;
import com.baojk.we.vo.ArticleVO;
import com.baojk.we.vo.SimpleArticlePageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public BaseResponse<Integer> addArticle(@RequestParam String name, @RequestParam Integer classification,
                                            @RequestParam String articleContent, @RequestParam Integer status,
                                            @RequestParam Integer isUp) throws ApiException {

        BaseResponse<Integer> response = new BaseResponse<>(SuccessConstants.SUCCESS);
        BaseResult<Integer> result = articleService
                        .addArticle(name, classification, userId, articleContent, status, isUp);
        if (result.isSuccess()) {
            response.setData(result.getData());
            return response;
        }
        throw new ApiException(result.getError());
    }

    @PatchMapping(value = "")
    public BaseResponse<Integer> updateArticle(@RequestParam Integer id, @RequestParam String name,
                                               @RequestParam Integer classification,
                                               @RequestParam String articleContent, @RequestParam Integer status,
                                               @RequestParam Integer isUp) throws ApiException {

        BaseResponse<Integer> response = new BaseResponse<>(SuccessConstants.SUCCESS);
        BaseResult<Integer> result = articleService
                        .updateArticle(id, name, classification, userId, articleContent, status, isUp);
        if (result.isSuccess()) {
            response.setData(result.getData());
            return response;
        }
        throw new ApiException(result.getError());
    }

    @GetMapping(value = "simple-page")
    public BaseResponse<SimpleArticlePageVO> getSimpleArticlePage(
                    @RequestParam(required = false) @ApiParam(value = "每页数据条数") Integer pageSize,
                    @RequestParam(required = false) @ApiParam(value = "页码") Integer currentPage,
                    @RequestParam(required = false) @ApiParam(value = "分类list") List<String> classficationList)
                    throws ApiException {

        if (pageSize == null) {
            pageSize = 8;
        }
        if (currentPage == null) {
            currentPage = 1;
        }

        List<Integer> codes = ArticleClassificationEnum.getCodes(classficationList);

        BaseResponse<SimpleArticlePageVO> response = new BaseResponse<>(SuccessConstants.SUCCESS);
        BaseResult<SimpleArticlePageVO> result = articleService.getSimpleArticlePage(pageSize, currentPage, codes);
        if (result.isSuccess()) {
            response.setData(result.getData());
            return response;
        }
        throw new ApiException(result.getError());
    }
}
