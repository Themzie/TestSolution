package com.econetwireless.testsolution.service;


import com.econetwireless.testsolution.model.Config;
import com.econetwireless.testsolution.model.Msg;

import java.util.Map;


/**
 * Created by thembelani on 7/8/17.
 */
public interface MsgService {
    Msg saveOrUpdate(Msg msg);
    Config getMessages();
    Msg sendToSink(Msg msg);
    Map<String,Integer> populate(Config config);
}
