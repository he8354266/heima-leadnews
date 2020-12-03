package com.heima.model.mappers.app;

import com.heima.model.article.pojos.ApAssociateWords;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2020/12/210:20
 */
public interface ApAssociateWordsMapper {
    /**
     * 根据关键词查询联想词
     *
     * @param searchWords
     * @return
     */
    List<ApAssociateWords> selectByAssociateWords(@Param("searchWords") String searchWords, @Param("limit") int limit);
}
