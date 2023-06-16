package com.sisp.entity.dto;

public class HttpResponseEntityFactory {
    HttpResponseEntityFactory(){
        throw new IllegalStateException("Utility class");
    }
    public static HttpResponseEntity get200(){
        HttpResponseEntity httpResponseEntity=new HttpResponseEntity();
        httpResponseEntity.setCode("200");
        httpResponseEntity.setMessage("OK");
        return httpResponseEntity;
    }
    public static <T> HttpResponseEntity get200(T entity){
        HttpResponseEntity httpResponseEntity=new HttpResponseEntity();
        httpResponseEntity.setCode("200");
        httpResponseEntity.setMessage("OK");
        httpResponseEntity.setData(entity);
        return httpResponseEntity;
    }
    public static HttpResponseEntity get403(){
        HttpResponseEntity httpResponseEntity=new HttpResponseEntity();
        httpResponseEntity.setCode("403");
        httpResponseEntity.setMessage("参数不合法");
        return httpResponseEntity;
    }
    public static HttpResponseEntity get400(){
        HttpResponseEntity httpResponseEntity=new HttpResponseEntity();
        httpResponseEntity.setCode("400");
        httpResponseEntity.setMessage("请求有语法错误或无效的参数");
        return httpResponseEntity;
    }
}
