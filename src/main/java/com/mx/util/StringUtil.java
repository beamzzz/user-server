package com.mx.util;

import org.apache.commons.lang.StringUtils;

/**
 * @aother zcl
 * @date 2017/8/15
 */
public class StringUtil {

    public static boolean checkLength(String str,int minlength,int maxLength){
        if(StringUtils.isBlank(str)){
            return false;
        }
        if(str.length() < minlength){
            return false;
        }
        if(str.length() > maxLength){
            return false;
        }
        return true;
    }
}
