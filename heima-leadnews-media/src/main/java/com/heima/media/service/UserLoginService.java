package com.heima.media.service;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.media.pojos.WmUser;

/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2020/12/39:35
 */
public interface UserLoginService {
    /**
     * 登录方法
     */
    ResponseResult login(WmUser user);
}
