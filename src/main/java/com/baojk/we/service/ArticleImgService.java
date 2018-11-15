package com.baojk.we.service;

import com.baojk.we.base.BaseResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @author baojikui (bjklwr@outlook.com)
 * @date 2018/10/18
 */
public interface ArticleImgService {

    BaseResult<String> upLoadImg(MultipartFile file, Integer articleId);

    BaseResult<Map<Integer, String>> getImgBuArticleIds(List<Integer> articleIds);
}
