package com.heima.article.apis;

import com.heima.model.article.dtos.ArticleInfoDto;
import com.heima.model.common.dtos.ResponseResult;

/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2020/11/279:34
 */
public interface ArticleInfoControllerApi {
    ResponseResult loadArticleInfo(ArticleInfoDto dto);

    ResponseResult loadArticleBehavior(ArticleInfoDto dto);
}
