package com.heima.model.mappers.app;

import com.heima.model.article.pojos.ApCollection;
import org.apache.ibatis.annotations.Param;

/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2020/11/2711:46
 */
public interface ApCollectionMapper {
    /**
     * 选择一个终端的收藏数据
     *
     * @return
     */
    ApCollection selectForEntryId( @Param("objectId") Integer objectId, @Param("entryId") Integer entryId, @Param("type") Short type);
}
