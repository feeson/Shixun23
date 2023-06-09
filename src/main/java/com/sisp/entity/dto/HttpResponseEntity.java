package com.sisp.entity.dto;

import lombok.Data;

/**
 * @Author: lucy
 * @Date: 2023-06-01-19:51
 */
@Data
public class HttpResponseEntity {
    private String code;
    private Object data;
    private String message;

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
