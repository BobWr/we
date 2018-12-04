package com.baojk.we.vo;

import lombok.Data;

/**
 * @author baojikui (bjklwr@outlook.com)
 * @date 2018/10/18
 */
@Data
public class SimpleArticleVO {

    private String name;

    private Integer watchNum;

    private Integer id;

    private String simpleArticleContent;

    private String username;

    private Integer authorId;

    private String simpleImgUrl;

    private String tagName;

    private String tagColor;
}
