package com.heima.media.service;

import com.heima.model.common.dtos.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2020/12/415:58
 */
public interface MonggoInfo {
    ResponseResult saveInfo(MultipartFile multipartFile);
}
