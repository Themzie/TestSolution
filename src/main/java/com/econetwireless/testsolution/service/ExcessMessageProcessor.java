package com.econetwireless.testsolution.service;

import com.econetwireless.testsolution.model.Msg;

/**
 * Created by thembelani on 7/10/17.
 */
public interface ExcessMessageProcessor {
    Msg forwardToDestination(Msg msg);
}
