package com.heima.model.user.dtos;

import com.heima.model.annotation.IdEncrypt;
import lombok.Data;

@Data
public class UserRelationDto {

    // 文章作者ID
    @IdEncrypt
    Integer authorid;

    // 用户ID
    @IdEncrypt
    Integer userid;

    // 文章
    @IdEncrypt
    Integer articleid;
    /**
     * 操作方式
     * 0  关注
     * 1  取消
     */
    Short operation;

    Integer type;
}
