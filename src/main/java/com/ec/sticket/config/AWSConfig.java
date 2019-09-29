package com.ec.sticket.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;

public class AWSConfig {
    public static String BUCKET_NAME = "abocado2";
    public static String FOLDER_NAME = "image";

    public static AWSCredentials getAWSCredentials() {
        String accessKey = "AKIAQCNO7SYBNIXEKIEW";
        String secretKey = "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";
        return new BasicAWSCredentials(accessKey, secretKey);
    }
}
