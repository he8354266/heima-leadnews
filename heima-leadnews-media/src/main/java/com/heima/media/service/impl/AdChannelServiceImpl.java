package com.heima.media.service.impl;

import com.heima.media.service.AdChannelService;
import com.heima.model.admin.pojos.AdChannel;
import com.heima.model.mappers.admin.AdChannelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2020/12/39:38
 */
@Service
@SuppressWarnings("all")
public class AdChannelServiceImpl implements AdChannelService {
    @Autowired
    private AdChannelMapper adChannelMapper = null;

    @Override
    public List<AdChannel> selectAll() {
        return  adChannelMapper.selectAll();
    }
}
