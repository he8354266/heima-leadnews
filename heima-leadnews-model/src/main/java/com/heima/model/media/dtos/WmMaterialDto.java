package com.heima.model.media.dtos;

import com.heima.model.annotation.IdEncrypt;
import com.heima.model.common.dtos.PageRequestDto;
import lombok.Data;

@Data
public class WmMaterialDto  {

    @IdEncrypt
    private Integer id;

//    private String url;
}
