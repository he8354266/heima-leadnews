package com.heima.behavior.service;

import com.heima.model.behavior.dtos.ReadBehaviorDto;
import com.heima.model.common.dtos.ResponseResult;

/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2020/11/2717:29
 */
public interface AppReadBehaviorService {
    /**
     * 存储阅读数据
     * @param dto
     * @return
     */
    ResponseResult saveReadBehavior(ReadBehaviorDto dto);
}
