package com.heima.login.apis;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.user.pojos.ApUser;

/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2020/12/111:04
 */
public interface LoginControllerApi {
    ResponseResult login(ApUser user);
}
