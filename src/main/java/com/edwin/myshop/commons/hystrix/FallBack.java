package com.edwin.myshop.commons.hystrix;


import com.edwin.myshop.commons.constants.HttpStatusConstant;
import com.edwin.myshop.commons.dto.BaseResult;
import com.edwin.myshop.commons.utils.MapperUtils;
import com.google.common.collect.Lists;

public class FallBack {

    /**
     * 通用的熔断方法
     * @return
     */
    public static String badGetway(){
        BaseResult baseResult = BaseResult.notOk(Lists.newArrayList(
                new BaseResult.Error(String.valueOf(HttpStatusConstant.BAD_GETWAY.getStatus()),
                        HttpStatusConstant.BAD_GETWAY.getMessage())));
        try {
            return MapperUtils.obj2json(baseResult);
        }catch (Exception e){

        }
        return null;
    }
}
