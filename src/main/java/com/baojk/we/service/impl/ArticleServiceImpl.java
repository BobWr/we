package com.baojk.we.service.impl;

import com.baojk.we.base.BaseResult;
import com.baojk.we.base.ErrorEnum;
import com.baojk.we.dao.mapper.ArticleMapper;
import com.baojk.we.dao.model.Article;
import com.baojk.we.dao.model.ArticleExample;
import com.baojk.we.service.ArticleService;
import com.baojk.we.vo.ArticleVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author baojikui (bjklwr@outlook.com)
 * @date 2018/10/12
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public BaseResult<Integer> addArticle(ArticleVO articleVO) {
        Article article = new Article();
        BeanUtils.copyProperties(articleVO, article);
        //        article.setArticleContent("飞禽的总称");
        //        article.setAuthorId(1);
        //        article.setClassification(1);
        //        article.setCreateIp("1");
        //        article.setIsRecommend(1);
        //        article.setIsUp(1);
        //        article.setName("1");
        //        article.setStatus(1);
        //        article.setWatchNum(1);
        BaseResult<Integer> result = new BaseResult<>();
        result.setData(articleMapper.insert(article));
        return result;
    }

    @Override
    public BaseResult<Integer> addArticle(String name, Integer classification, Integer authorId, String articleContent,
                                          Integer status, Integer isUp) {

        Article article = new Article();
        article.setArticleContent(articleContent);
        article.setAuthorId(authorId);
        article.setClassification(classification);
        article.setName(name);
        article.setStatus(status);
        article.setIsUp(isUp);

        //default
        article.setWatchNum(1);
        article.setIsRecommend(0);
        article.setCreateIp("0.0.0.0");

        BaseResult<Integer> result = new BaseResult<>();
        result.setData(articleMapper.insert(article));
        return result;
    }

    @Override
    public BaseResult<ArticleVO> getArticleById(Integer id) {

        BaseResult<ArticleVO> result = new BaseResult<>();
        ArticleExample example = new ArticleExample();
        ArticleExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        criteria.andStatusLessThan(3);

        List<Article> articles = articleMapper.selectByExample(example);

        if (articles != null && !articles.isEmpty()) {
            ArticleVO articleVO = new ArticleVO();
            BeanUtils.copyProperties(articles.get(0), articleVO);
            result.setData(articleVO);
            return result;
        }

        result.setError(ErrorEnum.NO_ARTICLE_ERROR);
        return result;
    }
}
