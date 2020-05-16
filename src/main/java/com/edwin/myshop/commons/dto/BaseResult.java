package com.edwin.myshop.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class BaseResult implements Serializable {
    private static final String RESULT_OK = "ok";
    private static final String RESULT_NOT_OK = "not_ok";
    private static final String SUCCESS = "操作成功";

    private String result;
    private Object data;
    private String success;
    private Currsor currsor;
    private List<Error> errors;

    public static BaseResult ok(Object data){
        return createBaseResult(RESULT_OK,data,SUCCESS,null,null);
    }

    public static BaseResult ok(){
        return createBaseResult(RESULT_OK,null,SUCCESS,null,null);
    }

    public static BaseResult ok(Object data,Currsor currsor){
        return createBaseResult(RESULT_OK,data,SUCCESS,currsor,null);
    }

    public static BaseResult notOk(List<Error> errors){
        return createBaseResult(RESULT_OK,null,"",null,errors);
    }

    private static BaseResult createBaseResult(String result,Object data,String success,Currsor currsor,List<Error> errors){
        BaseResult baseResult = new BaseResult();
        baseResult.setResult(result);
        baseResult.setData(data);
        baseResult.setCurrsor(currsor);
        baseResult.setSuccess(success);
        baseResult.setErrors(errors);
        return baseResult;
    }

    @Data
    public static class Currsor {
        private int total;
        private int offset;
        private int limmit;

    }

    @Data
    @AllArgsConstructor
    public static class Error{
       private String field;
       private String massage;
    }

}
