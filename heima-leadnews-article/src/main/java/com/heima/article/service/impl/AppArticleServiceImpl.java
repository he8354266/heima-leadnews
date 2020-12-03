package com.heima.article.service.impl;

import com.heima.article.service.AppArticleService;
import com.heima.common.article.constans.ArticleConstans;
import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.article.pojos.ApArticle;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.mappers.app.ApArticleMapper;
import com.heima.model.mappers.app.ApUserArticleListMapper;
import com.heima.model.user.pojos.ApUser;
import com.heima.model.user.pojos.ApUserArticleList;
import com.heima.utils.threadlocal.AppThreadLocalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2020/11/2510:23
 */
@Service
@SuppressWarnings("all")
public class AppArticleServiceImpl implements AppArticleService {
    private static final int MAX_PAGE_SIZE = 50;
    @Autowired
    private ApArticleMapper apArticleMapper;
    @Autowired
    private ApUserArticleListMapper apUserArticleListMapper;

    @Override
    public ResponseResult load(ArticleHomeDto dto, Short type) {
        //参数
        if (dto == null) {
            dto = new ArticleHomeDto();
        }
        if (dto.getMaxBehotTime() == null) {
            dto.setMaxBehotTime(new Date());
        }
        if (dto.getMinBehotTime() == null) {
            dto.setMinBehotTime(new Date());
        }
        //分页参数校验
        Integer size = dto.getSize();
        if (size == null || size <= 0) {
            size = 20;
        }
        size = Math.min(20, MAX_PAGE_SIZE);
        dto.setSize(size);
        //文章频道校验
        if (StringUtils.isEmpty(dto.getTag())) {
            dto.setTag(ArticleConstans.DEFAULT_TAG);
        }
        //类型参数校验
        if (!type.equals(ArticleConstans.LOADTYPE_LOAD_MORE) && !type.equals(ArticleConstans.LOADTYPE_LOAD_NEW)) {
            type = ArticleConstans.LOADTYPE_LOAD_MORE;
        }
        //获取用户信息
        ApUser user = AppThreadLocalUtils.getUser();

        //判断用户是否存在
        if (user != null) {
            //存在 已登录 加载推荐的文章
            List<ApArticle> apArticles = getUserArticle(user, dto, type);
            return ResponseResult.okResult(apArticles);
        } else {
            //不存在 未登录，加载默认的文章
            List<ApArticle> articles = getUserArticle(dto, type);
            return ResponseResult.okResult(articles);
        }

    }

    private List<ApArticle> getUserArticle(ArticleHomeDto dto, Short type) {
        return apArticleMapper.loadArticleListByLocation(dto, type);
    }

    private List<ApArticle> getUserArticle(ApUser user, ArticleHomeDto dto, Short type) {
        List<ApUserArticleList> list = apUserArticleListMapper.loadArticleIdListByUser(user, dto, type);
        if (!list.isEmpty()) {
            return apArticleMapper.loadArticleListByIdList(list);
        } else {
            return getDefaultArticle(dto, type);
        }

    }

    private List<ApArticle> getDefaultArticle(ArticleHomeDto dto, short type) {
        return apArticleMapper.loadArticleListByLocation(dto, type);

    }
}
