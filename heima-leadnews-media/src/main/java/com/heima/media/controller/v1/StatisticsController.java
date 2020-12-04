package com.heima.media.controller.v1;

import com.heima.media.service.StatisticsService;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.media.dtos.StatisticDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2020/12/311:34
 */
@RestController
@RequestMapping("/api/v1/statistics")
public class StatisticsController {
    @Autowired
    private StatisticsService statisticsService = null;

    @PostMapping("/news")
    public ResponseResult newsData(@RequestBody StatisticDto dto) {
        return statisticsService.findWmNewsStatistics(dto);
    }
}
