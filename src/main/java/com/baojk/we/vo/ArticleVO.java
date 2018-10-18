package com.baojk.we.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author baojikui (bjklwr@outlook.com)
 * @date 2018/10/14
 */
@Data
public class ArticleVO {

    private String name;

    private String createIp;

    private Integer watchNum;

    private Integer classification;

    private Integer authorId;

    private Integer status;

    private String articleContent;

    private Integer isUp;

    private Integer isRecommend;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
