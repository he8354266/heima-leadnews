package com.heima.model.mappers.app;

import com.heima.model.article.pojos.ApHotWords;

import java.util.List;

/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2020/12/210:19
 */
public interface ApHotWordsMapper {
    /**
     * 查询今日热词
     *
     * @param hotDate
     * @return
     */
    List<ApHotWords> queryByHotDate(String hotDate);
}
