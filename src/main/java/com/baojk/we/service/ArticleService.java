package com.baojk.we.service;

import com.baojk.we.base.BaseResult;
import com.baojk.we.vo.ArticleVO;
import com.baojk.we.vo.SimpleArticlePageVO;

import java.util.List;

/**
 * @author baojikui (bjklwr@outlook.com)
 * @date 2018/10/12
 */
public interface ArticleService {

    /**
     * 添加文章
     */
    BaseResult<Integer> addArticle(String name, Integer classification, Integer authorId, String articleContent,
                                   Integer status, Integer isUp);

    /**
     * 添加文章
     */
    BaseResult<Integer> updateArticle(Integer id, String name, Integer classification, Integer authorId,
                                      String articleContent, Integer status, Integer isUp);

    /**
     * 根据id查询文章
     */
    BaseResult<ArticleVO> getArticleById(Integer id);

    /**
     * 分页查询
     */
    BaseResult<SimpleArticlePageVO> getSimpleArticlePage(Integer pageSize, Integer currentPage,List<Integer> classes);
}
