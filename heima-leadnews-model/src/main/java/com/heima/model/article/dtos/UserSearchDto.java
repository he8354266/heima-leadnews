package com.heima.model.article.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.heima.model.annotation.IdEncrypt;
import com.heima.model.user.pojos.ApUserSearch;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class UserSearchDto {

    // 设备ID
    @JsonProperty(value = "EquipmentId")
    @NotNull(message = "设备ID不能为空")
    Integer equipmentId;
    @JsonProperty(value = "SearchWords")
    String searchWords;
    //查询tag: all, article, user, author
    String tag;
    @JsonProperty(value = "HisList")
    List<ApUserSearch> hisList;
    @JsonProperty(value = "HotDate")
    String hotDate;
    @JsonProperty(value = "PageNum")
    int pageNum;
    @JsonProperty(value = "PageSize")
    int pageSize;

    public int getFromIndex(){
        if(this.pageNum<1) {
            return 0;
        }
        if(this.pageSize<1) {
            this.pageSize = 10;
        }
        return this.pageSize * (pageNum-1);
    }
}
