package com.zoho.hierarchy.internal;

public class Tables {

    public static class UserTable {
        public static final String TABLE_NAME = "User";

        public static final String ID = "ID";
        public static final String NAME = "NAME";
        public static final String EMAIL = "EMAIL";
        public static final String AGE = "AGE";
        public static final String TEAM = "TEAM";
        public static final String ROLE = "ROLE";
        public static final String PHONE_NUMBER = "PHONE_NUMBER";
        public static final String HR_APPROVAL = "HR_APPROVAL";
        public static final String APPROVAL_NEEDED = "APPROVAL_NEEDED";
        public static final String UPDATED_JSON = "UPDATED_JSON";
        public static final String REPORTING_TO = "REPORTING_TO";

    }

    public static class HierarchyTable {
        public static final String TABLE_NAME = "Hierarchy";

        public static final String ID = "ID";
        public static final String USER_ID = "USER_ID";
        public static final String REPORTING_ID = "REPORTING_ID";
    }

    public static class HrApprovalTable {
        public static final String TABLE_NAME = "HrApproval";

        public static final String ID = "ID";
        public static final String USER_ID = "USER_ID";

    }

    public static class ApprovalTable {
        public static final String TABLE_NAME = "Approval";

        public static final String ID = "ID";
        public static final String USER_ID = "USER_ID";
        public static final String APPROVED_BY_ID = "APPROVED_BY_ID";
        public static final String STATUS = "STATUS";
    }

}
