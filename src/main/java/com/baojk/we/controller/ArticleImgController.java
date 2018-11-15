package com.baojk.we.controller;

import com.baojk.we.base.ApiException;
import com.baojk.we.base.BaseResponse;
import com.baojk.we.base.BaseResult;
import com.baojk.we.base.SuccessConstants;
import com.baojk.we.service.ArticleImgService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author baojikui (bjklwr@outlook.com)
 * @date 2018/10/31
 */
@RestController
@RequestMapping("api/v1/article-img")
@Api(description = "文章图片接口")
public class ArticleImgController {

    @Autowired
    private ArticleImgService articleImgService;

    @PostMapping(value = "")
    public BaseResponse<String> addArticleImg(@RequestParam MultipartFile multipartFile,
                                              @RequestParam Integer articleId) throws ApiException {

        BaseResponse<String> response = new BaseResponse<>(SuccessConstants.SUCCESS);

        BaseResult<String> result = articleImgService.upLoadImg(multipartFile, articleId);

        if (result.isSuccess()) {
            response.setData(result.getData());
            return response;
        }
        throw new ApiException(result.getError());
    }
}
