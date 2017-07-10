package com.econetwireless.testsolution.utils;

import com.econetwireless.testsolution.model.Msg;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by thembelani on 7/9/17.
 */


@Slf4j
public class AppUtils {
    public static String convertToJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Msg getMessageAtExchange() {
        return new Msg("1", 1, "source1", "123ABC", "EWZ", MessageStatus.RECEIVED_AT_EXCHANGE);
    }

    public static int getTotalSourceRate() {
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> {}", Constants.SOURCES_CONFIG.getConfig().toString());
        Constants.SOURCES_CONFIG.getConfig().entrySet().stream().forEach(stringIntegerEntry -> {
            log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Integer Entry {} ", stringIntegerEntry.getValue());
            Constants.TOTAL_SOURCE_RATE += stringIntegerEntry.getValue();
        });

        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> total source rate {}", Constants.TOTAL_SOURCE_RATE);
        return Constants.TOTAL_SOURCE_RATE;
    }

    public static int getTotalMessagesReceivedFromSources(Msg msg) {
        Constants.TOTAL_MESSAGES_FROM_SOURCES++;
        log.info("**************************************************TOTAL***************************************  {} ", Constants.TOTAL_MESSAGES_FROM_SOURCES);
        return Constants.TOTAL_MESSAGES_FROM_SOURCES;
    }

    public static Boolean permitSendingFromSource(Msg msg) {
        log.info("+++++++++++++++++++++++++++++++++++ SENT MESSAGES PER SOURCE {}", Constants.SENT_MESSAGES_PER_SSOURCE.get(msg.getSource()));
        log.info("+++++++++++++++++++++++++++++++++++ TOTAL NUMBER OF MESSAGES PERMITTED {} FOR SOURCE {} ", Constants.SOURCES_CONFIG.getConfig().get(msg.getSource()), msg.getSource());
        int sentPerSource = Constants.SENT_MESSAGES_PER_SSOURCE.get(msg.getSource());
        if (Constants.SENT_MESSAGES_PER_SSOURCE.get(msg.getSource()) < Constants.SOURCES_CONFIG.getConfig().get(msg.getSource())) {
            Constants.SENT_MESSAGES_PER_SSOURCE.put(msg.getSource(), sentPerSource + 1);
            return true;
        }
        return false;

    }
}