package com.heima.model.mappers.app;

import com.heima.model.article.pojos.ApArticleContent;

/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2020/11/2711:28
 */
public interface ApArticleContentMapper {
    ApArticleContent selectByArticleId(Integer articleId);

    void insert(ApArticleContent apArticleContent);
}
