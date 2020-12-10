package com.itheima.admin.service.test;

import com.itheima.admin.service.ReviewMediaArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2020/12/911:31
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ReviewArticleTest {
    @Autowired
    private ReviewMediaArticleService reviewMediaArticleService;
@Test
    public void test(){
    reviewMediaArticleService.autoReviewArticleByMedia(1);
}
}
