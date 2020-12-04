package com.heima.model.mappers.wemedia;

import com.heima.model.media.pojos.WmUser;

/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2020/12/310:14
 */
public interface WmUserMapper {
    WmUser selectByName(String name);

    WmUser selectById(Long id);
}
