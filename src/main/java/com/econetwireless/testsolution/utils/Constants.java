package com.econetwireless.testsolution.utils;

import com.econetwireless.testsolution.model.Config;
import com.google.common.util.concurrent.RateLimiter;

import java.util.Map;


/**
 * Created by thembelani on 7/8/17.
 */
public final class Constants {
    public static final String BROKER_URL = "tcp://localhost:61616";
    public static final String GET_MESSAGES_URL = "http://localhost:8181/testbench/config";
    public static final String SEND_TO_DESTINATION = "http://localhost:8181/testbench/sink/msg";
    public static Config SOURCES_CONFIG = null;
    public static int TOTAL_SOURCE_RATE = 0;
    public static int SINK_RATE = 0;
    public static RateLimiter RATE_LIMITER;
    public static int TOTAL_MESSAGES_FROM_SOURCES = 0;
    public static Map<String,Integer> SENT_MESSAGES_PER_SSOURCE;
}
