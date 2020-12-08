package com.heima.media.controller.v1;

import com.heima.media.service.MonggoInfo;
import com.heima.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2020/12/415:55
 */
@RestController
@RequestMapping("/znyg/dz")
public class MongoInfoController {
    @Autowired
    private MonggoInfo monggoInfo;

    @PostMapping("/upload")
    public ResponseResult upload(@RequestParam("file") MultipartFile file) {
         monggoInfo.saveInfo(file);
         return null;
    }
}
