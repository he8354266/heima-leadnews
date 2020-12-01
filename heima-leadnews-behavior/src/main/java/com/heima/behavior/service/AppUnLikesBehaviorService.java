package com.heima.behavior.service;

import com.heima.model.behavior.dtos.UnLikesBehaviorDto;
import com.heima.model.common.dtos.ResponseResult;

/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2020/11/2717:29
 */
public interface AppUnLikesBehaviorService {
    /**
     * 保存不喜欢数据
     *
     * @param dto
     * @return
     */
    ResponseResult saveUnLikesBehavior(UnLikesBehaviorDto dto);
}
