package com.itheima.admin.aliyun.test;

import com.heima.common.aliyun.AliyunTextScanRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2020/12/99:14
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AliTest {
    @Autowired
    private AliyunTextScanRequest aliyunTextScanRequest =null;

    @Test
    public void test(){
        String content = "阿里云，阿里巴巴集团旗下云计算品牌冰毒买卖，全球卓越的云计算技术和服务提供商。创立于2009年，在杭州、北京、硅谷等地设有研发中心和运营机构。";
        try {
            String response = aliyunTextScanRequest.textScanRequest(content);
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
