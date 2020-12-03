package com.heima.user.apis;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.user.dtos.UserRelationDto;

/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2020/12/115:06
 */
public interface UserRelationControllerApi {
    /**
     * 关注或取消关注
     */
    ResponseResult follow(UserRelationDto dto);
}
