package com.heima.behavior.service;

import com.heima.model.behavior.dtos.LikesBehaviorDto;
import com.heima.model.common.dtos.ResponseResult;

/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2020/11/2717:31
 */
public interface AppLikesBehaviorService {
    ResponseResult saveLikesBehavior(LikesBehaviorDto dto);
}
