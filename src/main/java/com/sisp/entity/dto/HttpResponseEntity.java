package com.sisp.entity.dto;

import lombok.Data;

/**
 * @Author: lucy
 * @Date: 2023-06-01-19:51
 */
@Data
public class HttpResponseEntity{
    private String code;
    private Object data;
    private String message;

    public <T> T getData(Class<T> t){
        try {
            return t.cast(this.data) ;
        }catch (Exception e){
            return null;
        }
    }

}
