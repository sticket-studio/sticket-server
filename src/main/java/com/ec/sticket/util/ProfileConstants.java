package com.ec.sticket.util;

public class ProfileConstants {
    public static final String PROFILE_PROD = "prod";
    public static final String PROFILE_DEV = "dev";


    public static final String PROFILE_JDBC_DRIVER_PROD
            = "jdbc:mysql://sticket.cmnxncxddliv.ap-northeast-2.rds.amazonaws.com:3306/sticket" +
            "?useUnicode=yes&characterEncoding=utf-8";
    public static final String PROFILE_JDBC_USERNAME_PROD = "sticket";
    public static final String PROFILE_JDBC_PASSWORD_PROD = "1q2w3e4r!@#";

    public static final String PROFILE_JDBC_DRIVER_DEV = "jdbc:h2:mem:testdb;MODE=MySQL;DB_CLOSE_DELAY=-1";
    public static final String PROFILE_JDBC_USERNAME_DEV = "sticket";
    public static final String PROFILE_JDBC_PASSWORD_DEV = "1q2w3e4r!@#";
}
