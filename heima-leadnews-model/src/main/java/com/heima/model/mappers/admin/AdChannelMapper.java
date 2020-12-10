package com.heima.model.mappers.admin;

import com.heima.model.admin.pojos.AdChannel;

import java.util.List;

/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2020/12/39:41
 */
public interface AdChannelMapper {
    List<AdChannel> selectAll();

    AdChannel selectByPrimaryKey(int id);
}
