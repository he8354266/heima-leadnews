package com.heima.login.service;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.user.dtos.UserRelationDto;

/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2020/12/115:40
 */
public interface AppUserRelationService {
    /**
     * 用户的关注或取消关注
     *
     * @param dto
     * @return
     */
    ResponseResult follow(UserRelationDto dto);
}
