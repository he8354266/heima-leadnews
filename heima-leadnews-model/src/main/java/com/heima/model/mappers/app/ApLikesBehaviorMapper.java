package com.heima.model.mappers.app;

import com.heima.model.behavior.pojos.ApLikesBehavior;
import org.apache.ibatis.annotations.Param;

/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2020/11/2711:48
 */
public interface ApLikesBehaviorMapper {
    /**
     * 选择最后一条喜欢按钮
     *
     * @return
     */
    ApLikesBehavior selectLastLike( @Param("objectId") Integer objectId, @Param("entryId") Integer entryId, @Param("type") Short type);

    /**
     * 保存
     *
     * @param record
     * @return
     */
    int insert(ApLikesBehavior record);
}
