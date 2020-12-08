package com.heima.media.service.impl;

import com.heima.media.pojo.Topology;
import com.heima.media.service.TopologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2020/12/418:05
 */
@Service
public class TopologyServiceImpl implements TopologyService {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void saveStu(Topology topology) {
        mongoTemplate.save(topology);
    }
}
