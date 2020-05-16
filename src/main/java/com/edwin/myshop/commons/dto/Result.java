package com.edwin.myshop.commons.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import java.io.Serializable;
import java.util.Map;

@ApiModel(value = "返回类")
@Data
public class Result<T>  implements Serializable {

    @ApiModelProperty("返回码")
    private Integer code;
    @ApiModelProperty("消息")
    private String msg;
    @ApiModelProperty("返回对象")
    public T data;
    @ApiModelProperty("消息集合")
    public Map<String,Object> errorMap;

    public Result(){

    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(Integer code, String msg, T data, Map<String,Object> errorMap) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.errorMap = errorMap;
    }
}
