package com.heima.article.apis;

import com.heima.model.article.dtos.UserSearchDto;
import com.heima.model.common.dtos.ResponseResult;

/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2020/12/28:45
 */
public interface ArticleSearchControllerApi {
    /**
     * 查询搜索历史
     *
     * @param dto
     * @return
     */
    ResponseResult findUserSearch(UserSearchDto dto);

    /**
     * 删除搜索历史
     *
     * @param dto
     * @return
     */
    ResponseResult delUserSearch(UserSearchDto dto);

    /**
     * 清空搜索历史记录
     *
     * @param dto
     * @return
     */
    ResponseResult clearUserSearch(UserSearchDto dto);

    /**
     * 今日热词
     *
     * @param dto
     * @return
     */
    ResponseResult hotkeywords(UserSearchDto dto);

    /**
     * 联想词查询
     *
     * @param dto
     * @return
     */
    ResponseResult searchassociate(UserSearchDto dto);

    /**
     * es文章分页查询
     *
     * @param dto
     * @return
     */
    ResponseResult esArticleSearch(UserSearchDto dto);
}
