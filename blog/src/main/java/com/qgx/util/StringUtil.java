package com.qgx.util;

import org.apache.shiro.crypto.hash.Md5Hash;

import java.util.ArrayList;
import java.util.List;

/**
 *@Author: goxcheer
 *@Date:17:18 2018/7/28
 *@email:604721660@qq.com
 *@decription: 字符串工具类
 */
public final class StringUtil {

    /**
     * MD5加密
     * @param str
     * @param salt
     * @return
     */
    public static String MD5Encrypt(String str, String salt) {
            return new Md5Hash(str, salt).toString();
    }

    /**
     * 判断是否是空
     * @param str
     * @return
     */
    public static boolean isEmpty(String str){
        if(str==null||"".equals(str.trim())){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 判断是否不是空
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str){
        if((str!=null)&&!"".equals(str.trim())){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 过滤掉集合里的空格
     * @param list
     * @return
     */
    public static List<String> filterWhite(List<String> list){
        List<String> resultList=new ArrayList<String>();
        for(String l:list){
            if(isNotEmpty(l)){
                resultList.add(l);
            }
        }
        return resultList;
    }

    /**
     * 格式化模糊查询
     * @param str
     * @return
     */
    public static String formatLike(String str){
        if(isNotEmpty(str)){
            return "%"+str+"%";
        }else{
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(MD5Encrypt("123456", "goxcheer"));
    }
}
