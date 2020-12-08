package com.itheima.admin.service;

import com.heima.model.admin.pojos.AdUser;
import com.heima.model.common.dtos.ResponseResult;

/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2020/12/710:06
 */
public interface UserLoginService {
    /**
     * 登录
     * @param user
     * @return
     */
    public ResponseResult login(AdUser user);
}
