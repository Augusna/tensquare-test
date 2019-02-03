package com.tensquare.common.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)  //若字段为null则不显示
public class Result {
    /**是否成功.*/
    private boolean flag;
    /**f返回码.*/
    private Integer code;
    /**返回信息.*/
    private String message;
    /**返回数据.*/
    private Object data;

    public Result() {
    }

    public Result(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public Result(boolean flag, Integer code, String message, Object data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
