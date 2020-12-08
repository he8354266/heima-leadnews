package com.heima.media.service.impl;

import com.heima.media.pojo.Student;
import com.heima.media.pojo.Topology;
import com.heima.media.service.MonggoInfo;


import com.heima.media.service.StudentService;
import com.heima.media.service.TopologyService;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2020/12/415:59
 */
@Service
@Slf4j
public class MonggoInfoImpl implements MonggoInfo {
    @Autowired
    private StudentService studentService;
@Autowired
private TopologyService topologyService;
    @Override
    public ResponseResult saveInfo(MultipartFile multipartFile) {
        String targetFilePath = "D:\\test";
        String fileName = multipartFile.getOriginalFilename();
        File targetFile = new File(targetFilePath + File.separator + fileName);

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(targetFile);
            IOUtils.copy(multipartFile.getInputStream(), fileOutputStream);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        File file = new File(String.valueOf(targetFile));
        if (file.isFile()) {
            String jsonStr = "";
            try {
                FileReader fileReader = new FileReader(targetFile);
                File jsonFile = new File(fileName);
                try {
                    Reader reader = new InputStreamReader(new FileInputStream(targetFile), "utf-8");
                    int ch = 0;
                    StringBuffer sb = new StringBuffer();
                    while ((ch = reader.read()) != -1) {
                        sb.append((char) ch);
                    }
                    fileReader.close();
                    reader.close();
                    jsonStr = sb.toString();
                    boolean result = this.saveMongo(jsonStr);

                    System.out.println(result);

                } catch (IOException e) {
                    e.printStackTrace();
                    return ResponseResult.okResult(AppHttpCodeEnum.PARAM_INVALID);
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return ResponseResult.okResult(AppHttpCodeEnum.PARAM_INVALID);
            }

        }

        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    private boolean saveMongo(String jsonStr) {
        try {
            Topology topology = new Topology();
            topology.set_id(1);
            topology.setName("111");
            topology.setData(jsonStr);

            topologyService.saveStu(topology);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
