package com.edwin.myshop.commons.utils;


import com.edwin.myshop.commons.dto.Result;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

public class ResultUtil<T> {

    /**
     * 返回失败信息并附带数据
     * @param msg
     * @param data
     * @return
     */
    public  static Result errResultReturnMsgAndData(String msg, Object data) {
        return new Result(ResultCode.ERROR,msg,data);
    }

    /**
     * 返回失败信息
     * @param msg
     * @return
     */
    public static Result errResultReturnMsg(String msg) {
        return new Result(ResultCode.ERROR,msg,"");
    }

    /**
     * 返回异常信息
     * @param e
     * @return
     */
    public static Result exceptionResultReturnMsg(Exception e) {
        return new Result(ResultCode.EXCEPTION,e.getMessage(),"");
    }

    /**
     * 返回成功信息并附带数据
     * @param msg
     * @param data
     * @return
     */
    public static Result successResultReturnMsgAndData(String msg,Object data) {
        return new Result(ResultCode.SUCCESS,msg,data);
    }

    /**
     *
     * @param code 返回码
     * @param msg 消息
     * @param data 实体
     * @return
     */
    public static Result customResult(Integer code ,String msg,Object data) {
        return new Result(code,msg,data);
    }
    public static Result customResult(Integer code ,String msg) {
        return new Result(code,msg,"");
    }
    public static Result customResult(Integer code ,Object data) {
        return new Result(code,"",data);
    }
    /**
     * 返回成功数据
     * @param data
     * @return
     */
    public static Result successResultReturnData(Object data) {
        return new Result(ResultCode.SUCCESS,"",data);
    }

    /**
     * 返回成功信息
     * @param msg
     * @return
     */
    public static Result successResultMsg(String msg){
        return new Result(ResultCode.SUCCESS,msg,"");
    }

    /**
     * 返回异常信息并附带异常code
     * @param e
     * @param data
     * @return
     */
    public static Result exceptionResultMsg(Exception e,Object data){
        String msg=e.getMessage();
        if(msg.indexOf("content:")>-1){
            msg=msg.split("content:")[1].trim();
        }
        /*if(e instanceof FeignException){
           return (Result)JSONObject.toBean(JSONObject.fromObject(msg),Result.class);

        }*/
        return new Result(ResultCode.EXCEPTION,msg,data);
    }

    public static Result myErrorMsgAndData(String msg, Object data, Map<String,Object> errorMap){
        return new Result(ResultCode.SUCCESS,msg,data,errorMap);
    }

    /**
     * 对象转数组
     * @param obj
     * @return
     */
    public static byte[] toByteArray (Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray ();
            oos.close();
            bos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return bytes;
    }
    /**
     * byte转对象
     * @param bytes
     * @return
     */
    private static Object ByteToObject(byte[] bytes) {
        Object obj = null;
        try {
            // bytearray to object
            ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
            ObjectInputStream oi = new ObjectInputStream(bi);

            obj = oi.readObject();
            bi.close();
            oi.close();
        } catch (Exception e) {
            System.out.println("translation" + e.getMessage());
            e.printStackTrace();
        }
        return obj;
    }

}