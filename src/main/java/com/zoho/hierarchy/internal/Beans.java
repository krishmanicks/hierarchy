package com.zoho.hierarchy.internal;

import com.adventnet.mfw.bean.BeanUtil;

public class Beans {
    public static final String USER_REPOSITORY = "UserRepository";
    public static final String CRUD_TXN_BEAN = "CrudTxnService"; //No I18N
    public static final String ORGANIZATION_REPOSITORY = "OrganizationRepository"; //No I18N
    public static final String REPOSITORY_BEAN = "DistributionRepository"; //No I18N
    public static final String DELIVERY_POINT_REPOSITORY = "DeliveryPointRepository"; //No I18N
    public static final String DFS_FILE_REPOSITORY = "DFSFileRepository"; // No I18N
    public static final String ACME_CHALLENGE_REPOSITORY = "AcmeChallengeRepository"; // No I18N
    public static final String REQUIRE_NEW_TXN_BEAN = "RequireNewTxnBean"; // No I18N

    public static <T> T lookup(String beanName) {
        try {
            return (T) BeanUtil.lookup(beanName);
        } catch (Exception e) {
            throw new RuntimeException("bean lookup failed for target bean {" + beanName + "}", e);
        }
    }
}
