package com.heima.model.mappers.app;

import com.heima.model.user.pojos.ApUser;

/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2020/11/2714:08
 */
public interface ApUserMapper {
    ApUser selectById(Integer id);
}
