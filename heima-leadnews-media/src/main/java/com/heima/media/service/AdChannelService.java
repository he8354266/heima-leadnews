package com.heima.media.service;

import com.heima.model.admin.pojos.AdChannel;

import java.util.List;

/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2020/12/39:15
 */
public interface AdChannelService {
    /**
     * 查询所有的频道
     * @return
     */
    List<AdChannel> selectAll();
}
