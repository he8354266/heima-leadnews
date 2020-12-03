package com.heima.media.controller.v1;

import com.heima.media.apis.AdChannelControllerApi;
import com.heima.media.service.AdChannelService;
import com.heima.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2020/12/39:53
 */
@RestController
@RequestMapping("/api/v1/channel")
public class AdChannelController implements AdChannelControllerApi {
    @Autowired
    private AdChannelService adChannelService = null;

    @Override
    @RequestMapping("/channels")
    public ResponseResult selectAll() {
        return ResponseResult.okResult(adChannelService.selectAll());
    }
}