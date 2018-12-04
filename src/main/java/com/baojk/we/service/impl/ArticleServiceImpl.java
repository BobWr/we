package com.baojk.we.service.impl;

import com.baojk.we.base.BaseResult;
import com.baojk.we.base.Page;
import com.baojk.we.dao.mapper.ArticleMapper;
import com.baojk.we.dao.model.Article;
import com.baojk.we.dao.model.ArticleExample;
import com.baojk.we.dao.model.ArticleKey;
import com.baojk.we.enums.ArticleClassificationEnum;
import com.baojk.we.enums.ErrorEnum;
import com.baojk.we.service.ArticleImgService;
import com.baojk.we.service.ArticleService;
import com.baojk.we.service.UserService;
import com.baojk.we.vo.ArticleVO;
import com.baojk.we.vo.SimpleArticlePageVO;
import com.baojk.we.vo.SimpleArticleVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author baojikui (bjklwr@outlook.com)
 * @date 2018/10/12
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleImgService articleImgService;

    @Value("${img.nginxUrl}")
    private String nginxUrl;

    @Override
    public BaseResult<Integer> addArticle(String name, Integer classification, Integer authorId, String articleContent,
                                          Integer status, Integer isUp) {
        BaseResult<Integer> result = new BaseResult<>();
        Article article = new Article();

        article.setArticleContent(articleContent);
        article.setAuthorId(authorId);
        article.setClassification(classification);
        article.setArticleName(name);
        article.setStatus(status);
        article.setIsUp(isUp);

        //default
        article.setWatchNum(1);
        article.setIsRecommend(0);
        article.setCreateIp("0.0.0.0");
        articleMapper.insert(article);
        result.setData(article.getId());
        return result;
    }

    @Override
    public BaseResult<Integer> updateArticle(Integer id, String name, Integer classification, Integer authorId,
                                             String articleContent, Integer status, Integer isUp) {

        BaseResult<Integer> result = new BaseResult<>();
        ArticleKey articleKey = new ArticleKey();
        articleKey.setId(id);
        Article article = articleMapper.selectByPrimaryKey(articleKey);

        if (article == null) {
            return result;
        }

        article.setArticleContent(articleContent);
        article.setAuthorId(authorId);
        article.setClassification(classification);
        article.setArticleName(name);
        article.setStatus(status);
        article.setIsUp(isUp);

        //default
        article.setWatchNum(1);
        article.setIsRecommend(0);
        article.setCreateIp("0.0.0.0");

        result.setData(articleMapper.updateByPrimaryKey(article));
        return result;
    }

    @Override
    public BaseResult<ArticleVO> getArticleById(Integer id) {

        BaseResult<ArticleVO> result = new BaseResult<>();
        ArticleExample example = new ArticleExample();
        ArticleExample.Criteria criteria = example.createCriteria();

        criteria.andIdEqualTo(id);
        criteria.andStatusEqualTo(1);

        List<Article> articles = articleMapper.selectByExample(example);

        if (articles != null && !articles.isEmpty()) {
            ArticleVO articleVO = new ArticleVO();
            Article article = articles.get(0);
            BeanUtils.copyProperties(article, articleVO);
            article.setWatchNum(article.getWatchNum() + 1);
            articleMapper.updateByPrimaryKey(article);
            result.setData(articleVO);
            return result;
        }

        result.setError(ErrorEnum.NO_ARTICLE_ERROR);
        return result;
    }

    @Override
    public BaseResult<SimpleArticlePageVO> getSimpleArticlePage(Integer pageSize, Integer currentPage,
                                                                List<Integer> classes) {

        BaseResult<SimpleArticlePageVO> result = new BaseResult<>();
        ArticleExample example = new ArticleExample();
        ArticleExample.Criteria criteria = example.createCriteria();

        criteria.andStatusEqualTo(1);
        if (CollectionUtils.isEmpty(classes)) {
            result.setError(ErrorEnum.NO_ARTICLE_ERROR);
            return result;
        }
        criteria.andClassificationIn(classes);
        example.setOrderByClause("watch_num desc");

        PageHelper.startPage(currentPage, pageSize);
        List<Article> articles = articleMapper.selectByExample(example);

        if (CollectionUtils.isEmpty(articles)) {
            result.setError(ErrorEnum.NO_ARTICLE_ERROR);
            return result;
        }

        PageInfo<Article> articlePageInfo = new PageInfo<>(articles);
        long total = articlePageInfo.getTotal();

        List<SimpleArticleVO> vos = new ArrayList<>();

        //得到文章的第一个图片，放到卡片里，如果没有图片，则不添加
        List<Integer> articleIds = new ArrayList<>();
        articles.forEach(article -> articleIds.add(article.getId()));
        Map<Integer, String> articleImgMap = articleImgService.getImgBuArticleIds(articleIds).getData();

        List<Integer> authorIds = new ArrayList<>();
        articles.forEach(article -> {
            SimpleArticleVO simpleArticleVO = new SimpleArticleVO();
            simpleArticleVO.setName(article.getArticleName());
            if (articleImgMap.containsKey(article.getId())) {
                simpleArticleVO.setSimpleImgUrl(
                                "background-image: url(\"" + nginxUrl + articleImgMap.get(article.getId()) + "\");");
            } else {
                //                simpleArticleVO.setSimpleImgUrl("background-image: url(\"" + nginxUrl + "umaru
                // .jpeg" + "\");");
            }
            simpleArticleVO.setWatchNum(article.getWatchNum());
            simpleArticleVO.setSimpleArticleContent(article.getArticleContent().replaceAll("<[.[^>]]*>", ""));
            simpleArticleVO.setAuthorId(article.getAuthorId());
            simpleArticleVO.setId(article.getId());
            simpleArticleVO.setTagName(ArticleClassificationEnum.getVal(article.getClassification()));
            simpleArticleVO.setTagColor(ArticleClassificationEnum.getTagColor(article.getClassification()));
            vos.add(simpleArticleVO);
            authorIds.add(article.getAuthorId());
        });

        if (CollectionUtils.isEmpty(authorIds)) {
            result.setError(ErrorEnum.NO_ARTICLE_ERROR);
            return result;
        }
        Map<Integer, String> nameMap = userService.getUserNames(authorIds);
        vos.forEach(vo -> {
            vo.setUsername(nameMap.get(vo.getAuthorId()));
        });

        SimpleArticlePageVO simpleArticlePageVO = new SimpleArticlePageVO();
        Page<SimpleArticleVO> finalResPage = new Page<>();
        finalResPage.setContent(vos);
        finalResPage.setCurrentPage(currentPage);
        finalResPage.setPageSize(pageSize);
        finalResPage.setRecordTotal((int) total);
        simpleArticlePageVO.setPage(finalResPage);
        result.setData(simpleArticlePageVO);

        return result;
    }
}
