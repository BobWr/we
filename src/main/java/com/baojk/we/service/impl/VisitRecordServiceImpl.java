package com.baojk.we.service.impl;

import com.baojk.we.base.BaseResult;
import com.baojk.we.dao.mapper.VisitRecordMapper;
import com.baojk.we.dao.model.VisitRecord;
import com.baojk.we.dao.model.VisitRecordExample;
import com.baojk.we.service.VisitRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author baojikui (bjklwr@outlook.com)
 * @date 2019/01/01
 */
@Service
public class VisitRecordServiceImpl implements VisitRecordService {

    @Autowired
    private VisitRecordMapper visitRecordMapper;

    @Override
    public BaseResult<Integer> add(String ip, String requestUrl) {

        BaseResult<Integer> result = new BaseResult<>();

        VisitRecordExample example = new VisitRecordExample();
        example.createCriteria().andIpEqualTo(ip);
        example.setOrderByClause("create_time desc");
        List<VisitRecord> last = visitRecordMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(last) || last.get(0).getCreateTime().isBefore(LocalDateTime.now().minusMinutes(2))) {
            VisitRecord visitRecord = new VisitRecord();
            visitRecord.setIp(ip);
            visitRecord.setRequestUrl(requestUrl);
            result.setData(visitRecordMapper.insertSelective(visitRecord));
        }
        return result;
    }

    @Override
    public BaseResult<Integer> getTotal() {
        BaseResult<Integer> result = new BaseResult<>();

        VisitRecordExample example = new VisitRecordExample();
        result.setData(visitRecordMapper.countByExample(example));
        return result;
    }
}
