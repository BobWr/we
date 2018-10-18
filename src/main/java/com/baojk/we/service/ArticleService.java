package com.baojk.we.service;

import com.baojk.we.base.BaseResult;
import com.baojk.we.vo.ArticleVO;

/**
 * @author baojikui (bjklwr@outlook.com)
 * @date 2018/10/12
 */
public interface ArticleService {

    /**
     * 添加文章
     */
    BaseResult<Integer> addArticle(ArticleVO articleVO);

    /**
     * 添加文章
     */
    BaseResult<Integer> addArticle(String name, Integer classification, Integer authorId, String articleContent,
                                   Integer status, Integer isUp);

    /**
     * 根据id查询文章
     */
    BaseResult<ArticleVO> getArticleById(Integer id);
}
