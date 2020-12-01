package com.heima.behavior.service;

import com.heima.model.behavior.dtos.ShowBehaviorDto;
import com.heima.model.common.dtos.ResponseResult;

/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2020/11/2517:47
 */
public interface AppShowBehaviorService {
    ResponseResult saveShowBehavior(ShowBehaviorDto dto);
}
