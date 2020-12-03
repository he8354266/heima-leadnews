package com.heima.login.service;

import com.heima.model.behavior.dtos.FollowBehaviorDto;
import com.heima.model.common.dtos.ResponseResult;

/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2020/12/115:38
 */
public interface AppFollowBehaviorService {
    /**
     * 存储关注行为数据
     */
    ResponseResult saveFollowBehavior(FollowBehaviorDto dto);
}
