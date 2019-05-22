package com.ec.sticket.models.save;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(description = "Asset 저장을 위한 Request Body")
public class AssetSave {
    @ApiModelProperty(example = "에셋이름")
    private String name;
    @ApiModelProperty(example = "1")
    private int authorId;
    @ApiModelProperty(example = "EYE_RIGHT")
    private int landmarkId;
    @ApiModelProperty(example = "300")
    private int price;
}
