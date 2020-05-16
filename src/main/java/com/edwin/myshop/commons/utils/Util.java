package com.edwin.myshop.commons.utils;


import net.sf.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import org.apache.commons.lang3.MyStringUtils;

public class Util {


   public static Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
    /**
     * 判断对象是否为空
     *
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        } else if (obj instanceof CharSequence) {
            return ((CharSequence) obj).length() == 0;
        } else if (obj instanceof Collection) {
            return ((Collection) obj).isEmpty();
        } else if (obj instanceof Map) {
            return ((Map) obj).isEmpty();
        } else if (obj.getClass().isArray()) {
            return Array.getLength(obj) == 0;
        } else {
            return false;
        }
    }

    public static String[] initFilterField = {"creator", "createtime", "creatorid", "status", "id"};
    public static String[] initFilterIncludeField = {"editorId", "editor", "edittime"};

    /**
     * 判断对象是否不为空
     *
     * @param obj
     * @return
     */
    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    /**
     * 对比更新两个实体类，默认不更新"creator","createtime","creatorId","status","id"
     *
     * @param oldmodel
     * @param newmodel
     * @param field    需要排除不需要更新的字段
     * @return
     */
    public static Object updateEntityFilterFiled(Object oldmodel, Object newmodel, String... field) {


        if (oldmodel.getClass() == newmodel.getClass()) {
            JSONObject oldObject = JSONObject.fromObject(oldmodel);
            JSONObject newObject = JSONObject.fromObject(newmodel);
            if (isEmpty(field)) {
                field = initFilterField;
            } else {
                int fieldlength = field.length;
                field = Arrays.copyOf(field, field.length + initFilterField.length);
                /**
                 * arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
                 * Object src : 原数组
                 *    int srcPos : 从元数据的起始位置开始
                 * 　　Object dest : 目标数组
                 * 　　int destPos : 目标数组的开始起始位置
                 * 　　int length  : 要copy的数组的长度
                 */
                System.arraycopy(initFilterField, 0, field, fieldlength, initFilterField.length);
            }
            String[] finalField = field;
            oldObject.keySet().stream().filter(k -> !Arrays.asList(finalField).contains(k)).forEach(k -> {
                oldObject.put(k, newObject.get(k));
            });
            oldmodel = JSONObject.toBean(oldObject, oldmodel.getClass());
        } else {
            throw new RuntimeException("更新对象不匹配");
        }
        return oldmodel;
    }


    /**
     * @param oldmodel 查询出得原始对象  ,
     * @param newmodel 前端传递修改的新对象
     * @param field    需要修改的字段值
     * @return
     */
    public static Object updateEntityFilterIncludeFiled(Object oldmodel, Object newmodel, String... field) {
        if (oldmodel.getClass() == newmodel.getClass()) {
            JSONObject oldObject = JSONObject.fromObject(oldmodel);
            JSONObject newObject = JSONObject.fromObject(newmodel);

            if (isEmpty(field)) {
                field = initFilterIncludeField;
            } else {
                int fieldlength = field.length;
                field = Arrays.copyOf(field, field.length + initFilterIncludeField.length);
                System.arraycopy(initFilterIncludeField, 0, field, fieldlength, initFilterIncludeField.length);
            }
            String[] finalField = field;
            oldObject.keySet().stream().filter(k -> Arrays.asList(finalField).contains(k)).forEach(k -> {
                oldObject.put(k, newObject.get(k));
            });
            oldmodel = JSONObject.toBean(oldObject, oldmodel.getClass());
        } else {
            throw new RuntimeException("更新对象不匹配");
        }
        return oldmodel;
    }

    /**
     * @param list String 集合
     * @return 逗号分隔的 String
     */
    public static String list2string(List<String> list) {

        StringBuffer sb = new StringBuffer("");
        if (Util.isNotEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                if (i == (list.size() - 1)) {
                    sb = sb.append(list.get(i));
                } else {
                    sb = sb.append(list.get(i)).append(",");
                }
            }
        }
        return sb.toString();
    }
    /**
     * 将目标源中不为空的字段过滤，将数据库中查出的数据源复制到提交的目标源中
     *
     * @param source 用id从数据库中查出来的数据源
     * @param target 提交的实体，目标源
     */
    public static void copyNullProperties(Object source, Object target) throws RuntimeException {
        if(source==null){
            throw new RuntimeException("修改的资源不存在");
        }

        BeanUtils.copyProperties(source, target, getNoNullProperties(target));
    }

    /**
     * @param target 目标源数据
     * @return 将目标源中不为空的字段取出
     */
    private static String[] getNoNullProperties(Object target) {
        BeanWrapper srcBean = new BeanWrapperImpl(target);
        PropertyDescriptor[] pds = srcBean.getPropertyDescriptors();
        Set<String> noEmptyName = new HashSet<>();
        for (PropertyDescriptor p : pds) {
            Object value = srcBean.getPropertyValue(p.getName());
            if (value != null) noEmptyName.add(p.getName());
        }
        String[] result = new String[noEmptyName.size()];
        return noEmptyName.toArray(result);
    }


    public static boolean checkChinese(String string) {
        Matcher m = p.matcher(string);
        return m.find();
    }

    public static void main(String[] args) {



    }


}
