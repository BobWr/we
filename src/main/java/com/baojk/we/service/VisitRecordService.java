package com.baojk.we.service;

import com.baojk.we.base.BaseResult;
import org.springframework.stereotype.Component;

/**
 * @author baojikui (bjklwr@outlook.com)
 * @date 2019/01/01
 */
@Component
public interface VisitRecordService {

    /**
     * 插入一条记录
     *
     * @param ip
     * @param requestUrl
     *
     * @return
     */
    BaseResult<Integer> add(String ip, String requestUrl);

    /**
     * 查询总访问数
     *
     * @return
     */
    BaseResult<Integer> getTotal();
}
