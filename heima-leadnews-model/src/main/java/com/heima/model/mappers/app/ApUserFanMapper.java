package com.heima.model.mappers.app;

import com.heima.model.user.pojos.ApUserFan;
import org.apache.ibatis.annotations.Param;

/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2020/11/2711:59
 */
public interface ApUserFanMapper {
    int insert(ApUserFan recoed);

    ApUserFan selectByFansId(@Param("burst") String burst, @Param("userId") Integer userId, @Param("fansId") Long fansId);

    int deleteByFansId(@Param("burst") String burst, @Param("userId") Integer userId, @Param("fansId") Long fansId);
}
