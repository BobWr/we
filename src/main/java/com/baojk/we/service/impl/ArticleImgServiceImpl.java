package com.baojk.we.service.impl;

import com.baojk.we.base.BaseResult;
import com.baojk.we.base.ErrorEnum;
import com.baojk.we.dao.mapper.ArticleImgMapper;
import com.baojk.we.dao.model.ArticleImg;
import com.baojk.we.dao.model.ArticleImgExample;
import com.baojk.we.service.ArticleImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author baojikui (bjklwr@outlook.com)
 * @date 2018/10/18
 */
@Service
public class ArticleImgServiceImpl implements ArticleImgService {

    @Autowired
    private ArticleImgMapper articleImgMapper;

    @Value("${img.path}")
    private String path;

    @Value("${img.nginxUrl}")
    private String nginxUrl;

    @Override
    public BaseResult<String> upLoadImg(MultipartFile multipartFile, Integer articleId) {

        BaseResult<String> imgUrl = new BaseResult<>();

        if (!multipartFile.isEmpty()) {
            String name = multipartFile.getOriginalFilename();
            String ext = "." + name.substring(name.lastIndexOf(".") + 1);

            try {
                FileInputStream fileInputStream = (FileInputStream) multipartFile.getInputStream();
                String fileName = UUID.randomUUID() + ext;
                BufferedOutputStream bos = new BufferedOutputStream(
                                new FileOutputStream(path + File.separator + fileName));
                byte[] bs = new byte[1024];
                int len;
                while ((len = fileInputStream.read(bs)) != -1) {
                    bos.write(bs, 0, len);
                }
                bos.flush();
                bos.close();

                ArticleImg articleImg = new ArticleImg();
                articleImg.setArticleId(articleId);
                articleImg.setArticleImgName(fileName);
                articleImg.setLocation(path + File.separator + fileName);
                articleImgMapper.insertSelective(articleImg);
                imgUrl.setData(nginxUrl + articleImg.getArticleImgName());
            } catch (Exception e) {
                e.printStackTrace();
                imgUrl.setError(ErrorEnum.IMG_UPLOAD_FAILED);
            }
        }
        return imgUrl;
    }

    @Override
    public BaseResult<Map<Integer, String>> getImgBuArticleIds(List<Integer> articleIds) {

        ArticleImgExample example = new ArticleImgExample();
        ArticleImgExample.Criteria criteria = example.createCriteria();
        criteria.andArticleIdIn(articleIds);
        example.setOrderByClause("id asc");
        List<ArticleImg> articleImgs = articleImgMapper.selectByExample(example);

        BaseResult<Map<Integer, String>> result = new BaseResult<>();
        Map<Integer, String> imgMap = new HashMap<>();
        for (int i = 0; i < articleImgs.size(); i++) {
            if (!imgMap.containsKey(articleImgs.get(i).getArticleId())) {
                imgMap.put(articleImgs.get(i).getArticleId(), articleImgs.get(i).getArticleImgName());
            }
        }
        result.setData(imgMap);
        return result;
    }
}
