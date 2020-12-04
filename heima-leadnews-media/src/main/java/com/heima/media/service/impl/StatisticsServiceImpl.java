package com.heima.media.service.impl;

import com.heima.media.service.StatisticsService;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.mappers.wemedia.WmNewsStatisticsMapper;
import com.heima.model.mappers.wemedia.WmUserMapper;
import com.heima.model.media.dtos.StatisticDto;
import com.heima.model.media.pojos.WmNewsStatistics;
import com.heima.model.media.pojos.WmUser;
import com.heima.utils.threadlocal.WmThreadLocalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heima.common.constans.WmMediaConstans;

import java.util.List;

/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2020/12/310:59
 */
@Service
@SuppressWarnings("all")
public class StatisticsServiceImpl implements StatisticsService {
    @Autowired
    private WmNewsStatisticsMapper wmNewsStatisticsMapper = null;
    @Autowired
    private WmUserMapper wmUserMapper = null;

    @Override
    public ResponseResult findWmNewsStatistics(StatisticDto dto) {
        if (dto == null && dto.getType() == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        if (dto.getType() != WmMediaConstans.WM_NEWS_STATISTIC_CUR && (dto.getStime() == null || dto.getEtime() == null)) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //查询用户
        WmUser wmUser = WmThreadLocalUtils.getUser();
        WmUser user = wmUserMapper.selectById(wmUser.getId());
        List<WmNewsStatistics> wmNewsStatisticsList = wmNewsStatisticsMapper.findByTimeAndUserId(user.getId(), dto);
        return ResponseResult.okResult(wmNewsStatisticsList);
    }
}
