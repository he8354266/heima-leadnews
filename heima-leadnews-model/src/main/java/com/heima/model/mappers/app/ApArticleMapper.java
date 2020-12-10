package com.heima.model.mappers.app;

import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.article.pojos.ApArticle;
import com.heima.model.user.pojos.ApUserArticleList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2020/11/2510:44
 */
public interface ApArticleMapper {
    List<ApArticle> loadArticleListByLocation(@Param("dto") ArticleHomeDto dto, @Param("type") short type);

    List<ApArticle> loadArticleListByIdList(List<ApUserArticleList> list);

    void insert(ApArticle apArticle);
    ApArticle selectById(Long id);
}
