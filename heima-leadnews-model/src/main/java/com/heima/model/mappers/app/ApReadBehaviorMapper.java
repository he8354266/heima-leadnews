package com.heima.model.mappers.app;

import com.heima.model.behavior.pojos.ApReadBehavior;
import org.apache.ibatis.annotations.Param;

/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2020/11/2711:52
 */
public interface ApReadBehaviorMapper {
    int insert(ApReadBehavior record);

    int update(ApReadBehavior record);

    ApReadBehavior selectByEntryId( @Param("entryId") Integer entryId, @Param("articleId") Integer articleId);
}
