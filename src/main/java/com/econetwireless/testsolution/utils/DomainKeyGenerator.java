package com.econetwireless.testsolution.utils;

/**
 * Created by thembelani on 7/8/17.
 */
public class DomainKeyGenerator {
    public static long getKey() {
        long l = System.currentTimeMillis();
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i< 2 ; i++){
            builder.append((int)(Math.random() * 10));
        }
        int randomNumber = (int)(Math.random() * 1000);
        String entityId = l + builder.toString() + randomNumber  ;
        try{
            l = Long.parseLong(entityId);
        }catch (Exception e) {
            return l;
        }
        return l;
    }
}
