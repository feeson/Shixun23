package com.sisp.entity.dto;

import lombok.Data;
import org.springframework.util.Assert;

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

//    public String getCode() {
//        return code;
//    }
//
//    public void setCode(String code) {
//        this.code = code;
//    }
//
//    public Object getData() {
//        return data;
//    }
//
//    public void setData(Object data) {
//        this.data = data;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
}
