package com.heima.model.mappers.app;

import com.heima.model.behavior.pojos.ApUnlikesBehavior;
import org.apache.ibatis.annotations.Param;

/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2020/11/2711:57
 */
public interface ApUnlikesBehaviorMapper {
    /**
     * 选择最后一条不喜欢数据
     * @return
     */
    ApUnlikesBehavior selectLastUnLike(@Param("entryId") Integer entryId,@Param("articleId")  Integer articleId);

    int insert(ApUnlikesBehavior record);
}
