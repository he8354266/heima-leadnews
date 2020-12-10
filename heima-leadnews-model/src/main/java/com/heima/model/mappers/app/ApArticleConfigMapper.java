package com.heima.model.mappers.app;

import com.heima.model.article.pojos.ApArticleConfig;

/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2020/11/2710:36
 */
public interface ApArticleConfigMapper {
    ApArticleConfig selectByArticleId(Integer articleId);

    int insert(ApArticleConfig apArticleConfig);
}
