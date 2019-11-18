package com.teluscare.android.utility;

import com.teluscare.android.BuildConfig;

/**
 * Created by SandeepY on 18112019
 **/


public class TeluscareAPI {

    private static String MW_BASE_URL = "";

    public static void setAPIEnvironment() {
        switch (APIEnvironment.valueOf(BuildConfig.ENV)) {
            case DEBUG:
                setupDebugEnvironment();
                break;

            case RELEASE:
                setupProductionEnvironment();
                break;

            default:
                setupDebugEnvironment();
                break;
        }
    }

    public static String getBaseUrl() {
        return MW_BASE_URL;
    }

    private static void setupDebugEnvironment() {
        MW_BASE_URL = "https://www.teluscare.com/";
    }

    private static void setupProductionEnvironment() {
        MW_BASE_URL = "https://demo.teluscare.com/";
    }

    public enum APIEnvironment {
        DEBUG, RELEASE
    }
}
