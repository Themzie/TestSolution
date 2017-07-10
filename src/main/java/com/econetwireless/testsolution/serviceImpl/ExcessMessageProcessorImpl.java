package com.econetwireless.testsolution.serviceImpl;

import com.econetwireless.testsolution.model.Msg;
import com.econetwireless.testsolution.service.ExcessMessageProcessor;
import com.econetwireless.testsolution.service.MsgService;
import com.econetwireless.testsolution.utils.Constants;
import com.econetwireless.testsolution.utils.MessageStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;


/**
 * Created by thembelani on 7/10/17.
 */
@Slf4j
public class ExcessMessageProcessorImpl implements ExcessMessageProcessor {

    private MsgService msgService;

    public ExcessMessageProcessorImpl(MsgService msgService) {
        this.msgService = msgService;
    }

    @Override
    @JmsListener(destination = "messages.exchange")
    public Msg forwardToDestination(Msg msg) {
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> MESSAGE Has been added to queue  {}",msg);
        msg.setStatus(MessageStatus.RECEIVED_AT_SINK);
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Updating message status {}",msg);
        Constants.RATE_LIMITER.acquire(Constants.SINK_RATE);
        msgService.sendToSink(msg);
        return msg;
    }
}
