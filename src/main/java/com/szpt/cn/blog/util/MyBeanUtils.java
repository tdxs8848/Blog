package com.szpt.cn.blog.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

public class MyBeanUtils {

    public static String[] getNullPropertyNames(Object source){
        BeanWrapper beanWrapper = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = beanWrapper.getPropertyDescriptors();
        List<String> nullPropertyNamews = new ArrayList<>();
        for(PropertyDescriptor pd:pds){
            String propertyName = pd.getName();
            if(beanWrapper.getPropertyValue(propertyName)==null){
                nullPropertyNamews.add(propertyName);
            }
        }
        return nullPropertyNamews.toArray(new String[nullPropertyNamews.size()]);
    }
}
