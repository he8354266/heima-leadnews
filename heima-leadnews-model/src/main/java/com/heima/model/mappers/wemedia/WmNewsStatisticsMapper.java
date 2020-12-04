package com.heima.model.mappers.wemedia;

import com.heima.model.media.dtos.StatisticDto;
import com.heima.model.media.pojos.WmNewsStatistics;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2020/12/311:03
 */
public interface WmNewsStatisticsMapper {
    List<WmNewsStatistics> findByTimeAndUserId(@Param("userId") Long userId,
                                               @Param("dto") StatisticDto dto);
}
