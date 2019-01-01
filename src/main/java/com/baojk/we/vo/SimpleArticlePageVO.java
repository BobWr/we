package com.baojk.we.vo;

import com.baojk.we.base.Page;
import lombok.Data;

/**
 * @author baojikui (bjklwr@outlook.com)
 * @date 2018/10/18
 */
@Data
public class SimpleArticlePageVO {

    private Page<SimpleArticleVO> page;

    private Integer visitTimes;
}
