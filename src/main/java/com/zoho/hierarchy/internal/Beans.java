package com.zoho.hierarchy.internal;

import com.adventnet.mfw.bean.BeanUtil;

public class Beans {
    public static final String USER_REPOSITORY = "UserRepository";

    public static <T> T lookup(String beanName) {
        try {
            return (T) BeanUtil.lookup(beanName);
        } catch (Exception e) {
            throw new RuntimeException("bean lookup failed for target bean {" + beanName + "}", e);
        }
    }
}
