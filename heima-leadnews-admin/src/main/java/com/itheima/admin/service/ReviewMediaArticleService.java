package com.itheima.admin.service;

/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2020/12/910:13
 */
public interface ReviewMediaArticleService {
    /**
     * 自媒体端发布文章自动审核
     *
     * @param newsId
     */
    void autoReviewArticleByMedia(int newsId);
}
