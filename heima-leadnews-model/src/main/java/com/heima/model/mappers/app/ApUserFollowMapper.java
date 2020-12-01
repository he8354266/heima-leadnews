package com.heima.model.mappers.app;

import com.heima.model.user.pojos.ApUserFollow;
import org.apache.ibatis.annotations.Param;

/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2020/11/2713:45
 */
public interface ApUserFollowMapper {
    ApUserFollow selectByFollowId( @Param("userId") Long userId, @Param("followId") Integer followId);

    int insert(ApUserFollow record);

    int deleteByFollowId(@Param("burst") String burst, @Param("userId") Long userId, @Param("followId") Integer followId);

}
